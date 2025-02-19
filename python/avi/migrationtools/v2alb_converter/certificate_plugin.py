"""

"""
import ast
import base64
import json
import logging
import os
from Crypto.Cipher import AES
from Crypto.PublicKey import RSA

LOG = logging.getLogger()


PRIVATE_KEY = "privateKey.pem"
PUBLIC_KEY = "publicKey.pem"
PADDING = "{"


class CertificatePlugin:
    def __init__(self, cert_dir, tenant="admin"):

        self.cert_dir = cert_dir
        self.tenant = tenant

    def _decode_aes(self, c, e):
        return str(c.decrypt(base64.b64decode(e)), "utf-8").rstrip(PADDING)

    def name(self):
        return "Certificate"

    def _get_description(self, ipset_dict):
        return ipset_dict["description"] if "description" in ipset_dict else ""

    def vertical(self):
        return "Certificate"

    def subvertical(self):
        return "Certificate"

    def _load_private_key(self):

        v_folder = self.cert_dir + "/v-config/" + "secrets"
        LOG.info("Loading private key from config folder %s", v_folder)
        with open(v_folder + "/" + PRIVATE_KEY, encoding="utf-8") as private_file:
            private_key = private_file.read()
            return private_key

    def _decrypt_session_key(self, session_key):
        LOG.info("Decrypting session key")

        private_key = self._load_private_key()

        private = RSA.importKey(private_key)

        private = RSA.importKey(private_key.encode("utf-8"))
        secret = private.decrypt(ast.literal_eval(session_key))
        return secret

    def _decrypt_cert_private_key(self, enc_private_key, secret):
        LOG.info("Decrypting certificate private key")
        cipher = AES.new(secret)
        private_key = self._decode_aes(cipher, enc_private_key)
        return private_key

    def _create_api(self, cert_trust_obj, secret, edge_id):
        cert_pem = cert_trust_obj["pemEncoding"]
        cert_objectId = cert_trust_obj["objectId"]
        cert_name = cert_trust_obj.get("name")
        if not cert_name:
            cert_name = cert_objectId
        cert_name = f"{edge_id}-{cert_objectId}-{cert_name}"
        cert_private_key = None
        if "privateKey" in cert_trust_obj:
            enc_private_key = cert_trust_obj["privateKey"]
            base_decoded_private_key = base64.b64decode(
                enc_private_key.encode("utf-8"),
            )
            # print(base_decoded_private_key)
            cert_private_key = self._decrypt_cert_private_key(
                base_decoded_private_key, secret
            )

        body = {"certificate": cert_pem}

        cert_data = {
            "name": cert_name,
            "tenant_ref": "/api/tenant/?name=%s" % self.tenant,
            "certificate": body,
        }
        if cert_private_key is not None:
            cert_data["key"] = cert_private_key

        return cert_data

    # Delete private/public generated for certificate migration
    def _delete_pub_private_keys(self):

        v_folder = self.cert_dir + "/v-config/" + "secrets"
        private_key = v_folder + "/" + PRIVATE_KEY
        public_key = v_folder + "/" + PUBLIC_KEY
        LOG.info("Removing private/public key generated for\
            certificate migration.")
        try:
            os.remove(private_key)
            os.remove(public_key)
            LOG.info(
                "Successfully removed private/public key generated for"
                + " certificate migration."
            )
        except OSError as err:
            err_msg = (
                "Error while deleting private/public key generated for"
                + " certificate migration."
            )
            LOG.error(err_msg + " : %s", err)
            raise self.exception(err_msg)

    def xlate(self):
        
        try:
            cert_v_file = "%s/v-config/cert.json" % self.cert_dir
            if not os.path.exists(cert_v_file):
                return {}, {}
            with open(cert_v_file) as j:
                cert_v = json.loads(j.read())

            LOG.info("Translating Certificate configuration..")
            certificate = {}
            certificate["SSLKeyAndCertificate"] = []

            mig_cert = dict()
            cert_trust_obj_dict = cert_v["trustObjects"]
            if not cert_trust_obj_dict:
                LOG.info("No certificates found for migration.")
                return {}, {}
            session_key = cert_v["sessionKey"]

            secret = self._decrypt_session_key(session_key)
            for cert in cert_trust_obj_dict:
                edge_id = cert.get("scope").get("id")
                obj_id = cert.get("objectId")
                if edge_id not in mig_cert.keys():
                    mig_cert[edge_id] = {}
                cert_api = self._create_api(cert, secret, edge_id)
                certificate["SSLKeyAndCertificate"].append(cert_api)
                mig_cert[edge_id][obj_id] = cert_api

            return certificate, mig_cert

        except Exception as e:
            msg = f"Error while migrating nsx-v certificates . Message: {e}"
            LOG.error(msg)