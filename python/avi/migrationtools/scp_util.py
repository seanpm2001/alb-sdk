# Copyright 2021 VMware, Inc.
# SPDX-License-Identifier: Apache License 2.0

import logging
import os
from stat import S_ISDIR
import paramiko


LOG = logging.getLogger(__name__)


class SCPUtil(object):
    def __init__(self, hostname, username, password=None, pkey=None, port=22):
        """Initialize and setup connection"""
        self.sftp = None
        self.sftp_open = False
        # open SSH Transport stream
        self.transport = paramiko.Transport((hostname, int(port)))
        self.transport.connect(username=username, password=password, pkey=pkey)

    def _openSFTPConnection(self):
        """
        Opens an SFTP connection if not already open
        """
        if not self.sftp_open:
            self.sftp = paramiko.SFTPClient.from_transport(self.transport)
            self.sftp_open = True

    def get(self, remote_path, local_path=None):
        """
        Copies a file from the remote host to the local host.
        """
        self._openSFTPConnection()
        self.sftp.get(remote_path, local_path)

    def get_all_file_names(self, remote_path):
        """
        Copies a file from the local host to the remote host
        """
        file_names = list()
        try:
            self._openSFTPConnection()
            file_names = self.sftp.listdir(remote_path)
        except IOError as e:
            LOG.error(e)
        return file_names

    def get_all_files(self, remote_path, local_path=None):
        files = self.get_all_file_names(remote_path)
        for file in files:
            try:
                self.get(remote_path + file,
                         local_path + file.replace(':Common:', ''))
            except IOError as e:
                LOG.error(e)

    def rexists(self, path):
        """os.path.exists for paramiko's SCP object
        """
        try:
            self.sftp.stat(path)
        except IOError as e:
            if 'No such file' in str(e):
                return False
            raise
        else:
            return True

    def get_all_partition_config(self, partition_path, local_path):
        if not self.rexists(partition_path):
            return
        files = self.get_all_file_names(partition_path)
        for file in files:
            remote_file = partition_path + file + '/bigip.conf'
            if self.isdir(partition_path + file) and self.rexists(remote_file):
                try:
                    self.get(remote_file, local_path + file + '_bigip.conf')
                except IOError as e:
                    LOG.error("conf file not found in partition dir : %s", file)

    def get_all_partition_certkey(self, partition_path, local_path):
        """
        This method gets all cert and key file from partition directories and
        dumps into local path
        :param partition_path:
        :param local_path:
        :return:
        """
        # partition_path = partition_path.replace('\\\\', '')
        if not self.rexists(partition_path):
            return
        files = self.get_all_file_names(partition_path)
        for filename in files:
            # filename = filename.replace('\\\\', '')
            if self.isdir(partition_path + '/' + filename):
                self.get_all_partition_certkey(partition_path + os.sep +
                                               filename, local_path)
            else:
                try:
                    local_filename = filename.replace(':Common:', '')
                    if ':' in local_filename:
                        local_filename = local_filename.split(':')[-1]
                    self.get(partition_path + os.sep + filename, local_path +
                             os.sep + local_filename)
                except IOError as e:
                    LOG.error("cert key file not found in partition dir : %s", filename)

    def isdir(self, path):
        self._openSFTPConnection()
        try:
            return S_ISDIR(self.sftp.stat(path).st_mode)
        except IOError:
            # Path does not exist, so by definition not a directory
            return False

    def close(self):
        """
        Close SFTP connection and ssh connection
        """
        if self.sftp_open:
            self.sftp.close()
            self.sftp_open = False
        self.transport.close()
