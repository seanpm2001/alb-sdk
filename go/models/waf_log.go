// Copyright 2021 VMware, Inc.
// SPDX-License-Identifier: Apache License 2.0
package models

// This file is auto-generated.

// WafLog waf log
// swagger:model WafLog
type WafLog struct {

	// Set to true if there are allowlist rules in the policy. Field introduced in 20.1.3.
	AllowlistConfigured *bool `json:"allowlist_configured,omitempty"`

	// Log Entries generated by WAF allowlist rules. Field introduced in 20.1.3.
	AllowlistLogs []*WafAllowlistLog `json:"allowlist_logs,omitempty"`

	// Set to true if allowlist rules were processed. Field introduced in 20.1.3.
	AllowlistProcessed *bool `json:"allowlist_processed,omitempty"`

	// Log Entries generated by Application Specific Signature rules. Field introduced in 20.1.1.
	ApplicationRuleLogs []*WafRuleLog `json:"application_rule_logs,omitempty"`

	// Set to true if there are Application Specific Signature rules in the policy. Field introduced in 20.1.1.
	ApplicationRulesConfigured *bool `json:"application_rules_configured,omitempty"`

	// Set to true if Application Specific Signature rules were executed. Field deprecated in 20.1.3. Field introduced in 20.1.1.
	ApplicationRulesExecuted *bool `json:"application_rules_executed,omitempty"`

	// Set to true if Application Specific Signature rules were processed. Field introduced in 20.1.3.
	ApplicationRulesProcessed *bool `json:"application_rules_processed,omitempty"`

	// Latency (in microseconds) in WAF Request Body Phase. Field introduced in 17.2.2.
	LatencyRequestBodyPhase *int64 `json:"latency_request_body_phase,omitempty"`

	// Latency (in microseconds) in WAF Request Header Phase. Field introduced in 17.2.2.
	LatencyRequestHeaderPhase *int64 `json:"latency_request_header_phase,omitempty"`

	// Latency (in microseconds) in WAF Response Body Phase. Field introduced in 17.2.2.
	LatencyResponseBodyPhase *int64 `json:"latency_response_body_phase,omitempty"`

	// Latency (in microseconds) in WAF Response Header Phase. Field introduced in 17.2.2.
	LatencyResponseHeaderPhase *int64 `json:"latency_response_header_phase,omitempty"`

	// Set to true if there are Positive Security Model rules in the policy. Field introduced in 18.2.3.
	PsmConfigured *bool `json:"psm_configured,omitempty"`

	// Set to true if Positive Security Model rules were executed. Field deprecated in 20.1.3. Field introduced in 18.2.3.
	PsmExecuted *bool `json:"psm_executed,omitempty"`

	// Log Entries generated by WAF Positive Security Model. Field introduced in 18.2.3.
	PsmLogs []*WafPSMLog `json:"psm_logs,omitempty"`

	// Set to true if Positive Security Model rules were processed. Field introduced in 20.1.3.
	PsmProcessed *bool `json:"psm_processed,omitempty"`

	//  Field introduced in 17.2.1.
	RuleLogs []*WafRuleLog `json:"rule_logs,omitempty"`

	// Set to true if there are ModSecurity rules in the policy. Field introduced in 18.2.3.
	RulesConfigured *bool `json:"rules_configured,omitempty"`

	// Set to true if ModSecurity rules were executed. Field deprecated in 20.1.3. Field introduced in 18.2.3.
	RulesExecuted *bool `json:"rules_executed,omitempty"`

	// Set to true if ModSecurity rules were processed. Field introduced in 20.1.3.
	RulesProcessed *bool `json:"rules_processed,omitempty"`

	// Denotes whether WAF is running in detection mode or enforcement mode, whether any rules matched the transaction, and whether transaction is dropped by the WAF module. Enum options - NO_WAF, FLAGGED, PASSED, REJECTED, WHITELISTED, BYPASSED. Field introduced in 17.2.2.
	Status *string `json:"status,omitempty"`

	// Set to true if there are whitelist rules in the policy. Field deprecated in 20.1.3. Field introduced in 18.2.3.
	WhitelistConfigured *bool `json:"whitelist_configured,omitempty"`

	// Set to true if whitelist rules were executed. Field deprecated in 20.1.3. Field introduced in 18.2.3.
	WhitelistExecuted *bool `json:"whitelist_executed,omitempty"`

	// Log Entries generated by WAF whitelist rules. Field deprecated in 20.1.3. Field introduced in 18.2.3.
	WhitelistLogs []*WafWhitelistLog `json:"whitelist_logs,omitempty"`
}
