'''
Created on Sep 4, 2015

@author: Gaurav Rastogi (grastogi@avinetworks.com)
This has examples of creating VS
'''
import argparse
from copy import deepcopy
import json
import math
import requests
from string import Template
import logging
from avi.sdk.avi_api import ApiSession

log = logging.getLogger(__name__)



ANALYTICS_POLICY = {
    'metrics_realtime_update': {
        'enabled': True,
        'duration': 0,
        },
    'full_client_logs': {
        'enabled': True,
        'duration': 0,        
        }
    }

VS_CFG = {
    "virtualservice": {'analytics_policy': ANALYTICS_POLICY,
                       'application_profile_ref': '/api/applicationprofile?name=System-HTTP'}
}

APP_CPU = 0.2

DEFAULT_APP = {
    "id": "bridged-webapp",
    "cmd": "service nginx start && /usr/sbin/sshd -D",
    #"cmd": "service nginx start",
    #"cmd": "service nginx start",
    "cpus": APP_CPU,
    "mem": 32.0,
    "instances": 2,
    "labels": {"avi_proxy": json.dumps(VS_CFG)},
    "container": {
        "type": "DOCKER",
        "docker": {
            "image": "avinetworks/server",
            #"image": "nginx",
            "network": "BRIDGE",
            "portMappings": [
                {"containerPort": 80, "hostPort": 0, "servicePort": 0,
                 "protocol": "tcp"}
                ]
        }
    },
    "healthChecks": [
        {"protocol": "HTTP", "portIndex": 0, "path": "/",
         "gracePeriodSeconds": 10, "intervalSeconds": 20,
         "maxConsecutiveFailures": 3}
    ]
}

AVI_SERVER = {
    "id": "bridged-webapp",
    "cmd": "service nginx start && /usr/sbin/sshd -D",
    #"cmd": "service nginx start",
    #"cmd": "service nginx start",
    "cpus": APP_CPU,
    "mem": 64.0,
    "instances": 2,
    "labels": {"avi_proxy": json.dumps(VS_CFG)},
    "container": {
        "type": "DOCKER",
        "docker": {
            #"image": "avinetworks/server",
            "image": "avinetworks/server",
            "network": "BRIDGE",
            "portMappings": [
                {"containerPort": 80, "hostPort": 0, "servicePort": 0,
                 "protocol": "tcp"}
                ]
        }
    },
    "healthChecks": [
        {"protocol": "HTTP", "portIndex": 0, "path": "/",
         "gracePeriodSeconds": 10, "intervalSeconds": 20,
         "maxConsecutiveFailures": 3}
    ]
}


DEFAULT_CLIENT = {
    "id": "test-client",
    #"cmd": "service nginx start; service ssh start; sleep 30000000",
    "cmd": "service nginx start && /usr/sbin/sshd -D",
    "cpus": APP_CPU,
    "mem": 64.0,
    "instances": 1,
    "labels": {"avi_proxy": json.dumps(VS_CFG)},
    "container": {
        "type": "DOCKER",
        "docker": {
            "image": "avinetworks/server",
            "network": "BRIDGE",
            "portMappings": [
                {"containerPort": 80, "hostPort": 0, "servicePort": 19994,
                 "protocol": "tcp"}
                ]
        }
    },
    "healthChecks": [
        {"protocol": "HTTP", "portIndex": 0, "path": "/",
         "gracePeriodSeconds": 5, "intervalSeconds": 20,
         "maxConsecutiveFailures": 3}
    ]
}


class MesosTestUtils(object):
    '''
    Utilities for the marathon App. currently it implements
    1. Creation
    2. Deletion
    '''
    MARATHON_URI = Template('http://$marathon_ip:8080/v2/apps')
    MARATHON_HDRS = {'Content-Type': 'application/json'}
    MARATHON_APP_TEMPLATES = {
        'default': DEFAULT_APP,
        'test-client': DEFAULT_CLIENT,
        'avi-server': AVI_SERVER}
    DOCKER_REGISTRY = '10.128.7.253'

    def __init__(self):
        pass

    def mesosCloudObj(self, marathon_ip, fleet_endpoint, sefolder, ew_subnet):
        mesos_confg_obj = {
            "mesos_url": "http://%s:5050" % marathon_ip,
            "se_volume": "/opt/avi",
            # "disable_se_repository_push": false,
            "use_bridge_ip_as_vip": True,
            # "use_marathon_se_deployment": false,
            # "disable_auto_frontend_service_sync": false,
            # "disable_auto_backend_service_sync": false,
            # "feproxy_bridge_name": "cbr1",
            'marathon_configurations': [
                {"marathon_url": "http://%s:8080" % marathon_ip}],
            # "container_port_match_http_service": true,
            "fleet_endpoint": "http://%s:4444" % fleet_endpoint,
            "docker_registry_se": {
                "registry": "%s:5000/%s" % (self.DOCKER_REGISTRY, sefolder)},
            "east_west_placement_subnet": {"ip_addr": {
                                            "type": "V4",
                                            "addr": ew_subnet},
                                           "mask": 24},
            "use_container_ip_port": True
            # "prefer_static_routes": false,
            # "mtu": 1500, "apic_mode": false,
            # "enable_vip_static_routes": false
        }
        return mesos_confg_obj

    def createApp(self, marathon_ip, app_type, app_name, num_apps,
                  num_instances=None, northsouth=0, vips=None,
                  virtualservice=None, pool=None, ns_service_port=None, ew_service_port_start_index=None):
        if virtualservice is None:
            virtualservice = {}
        if pool is None:
            pool = {}

        marathon_uri = self.MARATHON_URI.substitute(marathon_ip=marathon_ip)
        app_ids = []
        print 'type', app_type, 'name', app_name, 'ns', northsouth, 'vip', vips
        for index in range(num_apps):
            app_id = (app_name + '-' + str(index + 1)
                      if num_apps > 1 else app_name)
            app_obj = self.MARATHON_APP_TEMPLATES[app_type]
            app_obj = deepcopy(app_obj)
            if num_instances:
                if num_instances != -1:
                    app_obj['instances'] = num_instances
                else:
                    app_obj['instances'] = index % 3 + 1
            app_obj['id'] = app_id
            app_ids.append(app_id)
            avi_proxy_json = app_obj['labels']['avi_proxy']
            print ' proxy json-', avi_proxy_json
            avi_proxy = json.loads(avi_proxy_json)
            if virtualservice:
                if 'virtualservice' not in avi_proxy:
                    avi_proxy['virtualservice'] = virtualservice
                else:
                    for k, v in virtualservice.iteritems():
                        avi_proxy['virtualservice'][k] = v
            if northsouth and vips and (index % math.ceil(float(num_apps)/northsouth) == 0):
                app_obj['labels']['FE-Proxy'] = 'Yes'
                ns_index = int(index / (num_apps/northsouth))
                app_obj['labels']['FE-Proxy-VIP'] = vips[ns_index]
                # add services same as service port
                avi_proxy['virtualservice']['services'] = \
                    [{'port': int(ns_service_port)}]
            if pool:
                if 'pool' not in avi_proxy:
                    avi_proxy['pool'] = pool
                else:
                    for k, v in pool.iteritems():
                        avi_proxy['pool'][k] = v
            app_obj['labels']['avi_proxy'] = json.dumps(avi_proxy)

            #if service_port and not northsouth:
            if ew_service_port_start_index and not app_obj['labels'].get('FE-Proxy'):
                app_obj['container']['docker']['portMappings'][0]['servicePort'] = int(ew_service_port_start_index) + index

            rsp = requests.post(marathon_uri,
                                data=json.dumps(app_obj),
                                headers=self.MARATHON_HDRS)
            print 'created app', app_id, app_obj, ' response ', rsp.text
        return app_ids

    def updateAppConfig(self, marathon_ip, app_id, **kwargs):
        marathon_uri = self.MARATHON_URI.substitute(marathon_ip=marathon_ip)
        marathon_uri = marathon_uri + '/' + app_id + '?force=true'
        print 'get ', marathon_uri
        rsp = requests.get(marathon_uri)
        print 'response', rsp

        app_obj = json.loads(rsp.text)
        print ' application obj', app_obj
        app_obj = app_obj['app']
        for k, v in kwargs.iteritems():
            app_obj[k] = v
        del app_obj['version']

        rsp = requests.put(marathon_uri, data=json.dumps(app_obj),
                           headers=self.MARATHON_HDRS)
        print 'updated app', app_id, ' response ', rsp.text
        return rsp

    def updateApp(self, marathon_ip, app_id, vs_obj=None, **kwargs):
        marathon_uri = self.MARATHON_URI.substitute(marathon_ip=marathon_ip)
        marathon_uri = marathon_uri + '/' + app_id + '?force=true'
        rsp = requests.get(marathon_uri, headers=self.MARATHON_HDRS)
        print 'get ', marathon_uri, 'response', rsp
        app_obj = json.loads(rsp.text)
        print ' application obj', app_obj
        app_obj = app_obj['app']

        avi_proxy = json.loads(app_obj['labels']['avi_proxy'])
        if not vs_obj:
            vs_cfg = avi_proxy.get('virtualservice')
        else:
            vs_cfg = vs_obj
        for k, v in kwargs.iteritems():
            vs_cfg[k] = v

        if not 'labels' in app_obj:
            app_obj['labels'] = {}

        avi_proxy['virtualservice'] = vs_cfg
        app_obj['labels']['avi_proxy']= json.dumps(avi_proxy)
        del app_obj['version']
        log.info('uri %s app %s', marathon_uri, app_obj)
        rsp = requests.put(marathon_uri, data=json.dumps(app_obj),
                           headers=self.MARATHON_HDRS)

        print 'updated app', app_id, ' response ', rsp.text
        return app_obj

    def deleteApp(self, marathon_ip, app_name, num_apps):
        app_ids = []
        for index in range(num_apps):
            app_id = (app_name + '-' + str(index + 1)
                      if num_apps > 1 else app_name)
            marathon_uri = self.MARATHON_URI.substitute(marathon_ip=marathon_ip)
            marathon_uri = marathon_uri + '/' + app_id
            rsp = requests.delete(marathon_uri, headers=self.MARATHON_HDRS)
            print ' deleted app', app_id, ' rsp ', rsp.text
            app_ids.append(app_id)
        return app_ids

    def updateMesosCloud(self, api, marathon_ip, fleet_endpoint,
                         sefolder, ew_subnet):
        '''
        @param api
        '''
        cloud_data = api.get_object_by_name('cloud', 'Default-Cloud')
        print 'current cloud', cloud_data
        cloud_data['vtype'] = 'CLOUD_MESOS'
        cloud_data['mesos_configuration'] = \
            self.mesosCloudObj(marathon_ip, fleet_endpoint, sefolder,
                               ew_subnet)
        cloud_data = api.put('cloud/%s' % cloud_data['uuid'],
                                    data=json.dumps(cloud_data))
        print 'updated cloud', cloud_data

    def getAppInfo(self, marathon_ip, app_id):
        marathon_uri = self.MARATHON_URI.substitute(marathon_ip=marathon_ip)
        marathon_uri += '/' + app_id
        rsp = requests.get(marathon_uri, headers=self.MARATHON_HDRS)
        print ' app id info ', app_id, ': ', rsp.text
        rsp_dict = json.loads(rsp.text) if rsp.text else {}
        log.debug('app id %s info %s', app_id, rsp_dict)
        return rsp_dict


if __name__ == '__main__':
    mapp_utils = MesosTestUtils()
    parser = argparse.ArgumentParser()
    parser.add_argument('-c', '--command',
                        choices=['create-app', 'delete-app', 'update-cloud',
                                 'show-app'],
                        help='lastest timestamp',
                        default='create-app')
    parser.add_argument('-a', '--app_name', help='Application Name',
                        default='bridged-webapp')
    parser.add_argument('-n', '--num_apps',
                        help='Number of Applications',
                        default=1, type=int)
    parser.add_argument('-m', '--marathon_ip',
                        help='IP address of the marathon')
    parser.add_argument('-f', '--fleet_endpoint_ip',
                        help='IP address of the fleet')
    parser.add_argument('-s', '--se_folder',
                        help='SE folder for registry')
    parser.add_argument('-e', '--east_west_subnet',
                        help='east west subnet. It assumes mask 24')
    parser.add_argument('-t', '--app_type',
                        help='type of app: ' + str(mapp_utils.MARATHON_APP_TEMPLATES.keys()),
                        default='default')
    parser.add_argument('-r', '--controller_ip', help='controller_ip',
                        default='127.0.0.1')
    parser.add_argument('-i', '--instances',
                        help='number of instances of server',
                        default=2, type=int)
    parser.add_argument('--northsouth', help='north south app',
                        default=False, type=bool)
    parser.add_argument('--vip', help='north south app',
                        default='')
    parser.add_argument('--autoscalepolicy', help='enable autoscale policy to app',
                        default='')
    parser.add_argument('--service_port', help='service port for app',
                        default=0, type=int)
    args = parser.parse_args()

    print 'parsed args', args

    if args.command == 'create-app':
        if not args.marathon_ip:
            raise Exception('marathon IP is required')
        pool = None
        if args.autoscalepolicy:
            pool = {}
            pool['lb_algorithm']='LB_ALGORITHM_FEWEST_SERVERS'
            pool['autoscale_policy_ref']=\
                '/api/serverautoscalepolicy?name=%s'%args.autoscalepolicy
            pool['autoscale_launch_config_ref']=\
                '/api/autoscalelaunchconfig?name=default-autoscalelaunchconfig'
            pool['capacity_estimation'] = True
            pool['capacity_estimation_ttfb_thresh'] = 10

        mapp_utils.createApp(args.marathon_ip, args.app_type,
                             args.app_name, args.num_apps, args.instances,
                             northsouth=args.northsouth,
                             vips=args.vip.split(','), pool=pool)
    elif args.command == 'delete-app':
        if not args.marathon_ip:
            raise Exception('marathon IP is required')
        mapp_utils.deleteApp(args.marathon_ip, args.app_name, args.num_apps)
    elif args.command == 'update-cloud':
        api = ApiSession.get_session(args.controller_ip, 'admin', 'avi123', tenant='admin')
        mapp_utils.updateMesosCloud(api, args.marathon_ip,
                                    args.fleet_endpoint_ip,
                                    args.se_folder, args.east_west_subnet)
    elif args.command == 'show-app':
        if not args.marathon_ip:
            raise Exception('marathon IP is required')
        app_info = mapp_utils.getAppInfo(args.marathon_ip, args.app_name)
        print app_info

