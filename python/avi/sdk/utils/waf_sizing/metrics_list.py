# Copyright 2021 VMware, Inc.
# SPDX-License-Identifier: Apache License 2.0

# List of all collected metrics, used by other scripts
metrics = (
    "l7_client.sum_total_responses,"
    "l7_client.sum_waf_disabled,"
    "l7_client.avg_waf_disabled,"
    "l7_client.pct_waf_disabled,"
    "l7_client.sum_http_headers_count,"
    "l7_client.avg_http_headers_count,"
    "l7_client.sum_http_headers_bytes,"
    "l7_client.avg_http_headers_bytes,"
    "l7_client.pct_get_reqs,"
    "l7_client.pct_post_reqs,"
    "l7_client.sum_http_params_count,"
    "l7_client.avg_http_params_count,"
    "l7_client.sum_uri_length,"
    "l7_client.avg_uri_length,"
    "l7_client.sum_post_bytes,"
    "l7_client.avg_post_bytes,"
    "l4_client.avg_bandwidth,"
    "l4_client.avg_complete_conns,"
    "l7_client.avg_complete_responses,"
    "l7_client.sum_get_reqs,"
    "l7_client.sum_post_reqs"
)
