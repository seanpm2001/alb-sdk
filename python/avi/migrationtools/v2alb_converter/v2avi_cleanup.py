#!/usr/bin/env python3

############################################################################
# ========================================================================
# Copyright 2021 VMware, Inc.  All rights reserved. VMware Confidential
# ========================================================================
###

# Copyright 2023 VMware, Inc.
# SPDX-License-Identifier: Apache License 2.0


import logging
import os
import argparse
import sys
from datetime import datetime
from avi.migrationtools.avi_converter import AviConverter
from avi.migrationtools.avi_migration_utils import get_count
from v2avi_utils import NsxvUtil


LOG = logging.getLogger(__name__)


class V2AviCleanup(AviConverter):
    def __init__(self, args):
        """
        :param args:
        """

        self.nsxv_ip = args.nsxv_ip
        self.nsxv_user = args.nsxv_user
        self.nsxv_password = args.nsxv_password
        self.nsxv_port = args.nsxv_port

        self.edge = args.edge
        self.vs_filter = None
        if args.vs_filter:
            self.vs_filter = \
                (set(args.vs_filter) if type(args.vs_filter) == list
                 else set(args.vs_filter.split(',')))
        self.output_file_path = args.output_file_path if args.output_file_path \
            else 'output'

        output_dir = os.path.normpath(self.output_file_path)
        self.output_path = output_dir + os.path.sep + self.nsxv_ip + os.path.sep + "output"

        self.input_path = output_dir + os.path.sep + self.nsxv_ip + os.path.sep + "input"
        if not os.path.exists(self.input_path):
            os.makedirs(self.input_path)

    def initiate_cleanup(self):

        try:
            if not os.path.exists(self.output_file_path):
                os.mkdir(self.output_file_path)
            self.init_logger_path()

            nsxv_util = NsxvUtil(self.nsxv_ip, self.nsxv_user, self.nsxv_password, self.input_path, None, None, None, None,
                                 c_ip=None, c_user=None, c_pw=None, c_vr=None)
            vedge_lb_config = nsxv_util.get_nsxv_Edges()

            nsxv_vs_not_found = nsxv_util.nsx_cleanup(vedge_lb_config, self.edge, self.vs_filter)
            if nsxv_vs_not_found:
                print_msg = "\033[93m" + "Warning: Following virtual service/s could not be found" + "\033[0m"
                print(print_msg)
                print(nsxv_vs_not_found)
                LOG.warning(print_msg)
                LOG.warning(nsxv_vs_not_found)

            print("Total Warning: ", get_count('warning'))
            print("Total Errors: ", get_count('error'))
            LOG.info("Total Warning: {}".format(get_count('warning')))
            LOG.info("Total Errors: {}".format(get_count('error')))
        except Exception as e:
            msg = f"Error while performing cleanup. Message: {e}"
            print(msg)
            LOG.error(msg)
            sys.exit(1)


if __name__ == "__main__":
    HELP_STR = """
    Usage:
    python v2avi_cleanup.py -n 192.168.100.101 -u admin -p password --edge edge-1 --vs-filter vs-1
    """

    parser = argparse.ArgumentParser(
        formatter_class=argparse.RawTextHelpFormatter, description=HELP_STR)

    # NSX-V credentials
    parser.add_argument('-n', '--nsxv_ip',
                        help='Ip of NSX-V Manager', required=True)
    parser.add_argument('-u', '--nsxv_user',
                        help='NSX-V User name', required=True)
    parser.add_argument('-p', '--nsxv_password',
                        help='NSX-V Password')
    parser.add_argument('-port', '--nsxv_port', default=443,
                        help='NSX-V Port')

    parser.add_argument('--edge',
                        help='NSX-V edge for which cleanup is to be executed.\n',
                        required=True)
    parser.add_argument('--vs_filter', help='comma separated names of virtual services within the specified edge '
                                            'for performing cleanup.\n', required=True)
    parser.add_argument('-o', '--output_file_path', help='Folder path for output files to be created in')

    start = datetime.now()
    args = parser.parse_args()

    cleanup = V2AviCleanup(args)
    cleanup.initiate_cleanup()

    end = datetime.now()
    print("The time of execution of above program is :",
          str(end - start))
