# Copyright 2021 VMware, Inc.
# SPDX-License-Identifier: Apache License 2.0

import logging
from avi.sdk.avi_api import ApiSession
from requests.packages import urllib3

LOG = logging.getLogger(__name__)

# disabling warning for SSL
urllib3.disable_warnings()


def upload_config_to_controller(avi_config_dict, controller_ip, username,
                                password, tenant='admin', api_version='18.2.6'):
    """
    Method to upload migrated config to controller

    Args:
        avi_config_dict : Passed migrated avi configuration
        controller_ip : destination controller ip address
        username : destination controller username
        password : destination controller password
        tenant : tenant ref
        api_version : controller versioion . Defaults to '18.2.6'.


    """
    LOG.debug("Uploading config to controller")
    session = ApiSession.get_session(controller_ip, username, password=password,
                                     tenant=tenant, api_version=api_version)
    try:
        d = {'configuration': avi_config_dict}
        path = 'configuration/import'
        resp = session.post(path, data=d, timeout=7200)
        if resp.status_code < 300:
            LOG.info("Config uploaded to controller successfully")
        else:
            LOG.error("Upload error response: %s", resp.text)
            raise Exception("Upload error response:" + resp.text)
    except Exception as exception:
        LOG.error("Failed config upload", exc_info=True)
        print("Error")
        raise Exception(exception)


def download_gslb_from_controller(controller_ip, username, password, tenant='admin'):
    """ Function to download the gslb configuration from controller """
    LOG.debug("Downloading gslb config from the controller")
    session = ApiSession.get_session(controller_ip, username,
                                     password, tenant="admin")
    try:
        path = 'gslb'
        resp = session.get(path)
        return resp.text
    except Exception as exception:
        LOG.error("Failed gslb config download", exec_info=True)
        print("Error in Downloading gslb config")
        raise Exception(exception)


def get_object_from_controller(object_type, object_name, controller_ip, username, password, tenant):
    """
    This function defines that it get the object from controller or raise
    exception if object status code is less than 299
    :param uri: URI to get the object
    :param controller_ip: ip of controller
    :param username: usename of controller
    :param password: password of controller
    :param tenant: tenant of controller
    :return: response status_code and content
    """
    # Create new session
    session = ApiSession.get_session(controller_ip, username,
                                     password=password, tenant=tenant)
    try:
        resp = session.get_object_by_name(object_type, object_name)
        return resp
    except:
        raise Exception("Failed get %s" % object_name, exc_info=True)
