package com.vmware.avi.sdk.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The WafLog is a POJO class extends AviRestResource that used for creating
 * WafLog.
 *
 * @version 1.0
 * @since 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WafLog  {
    @JsonProperty("application_rule_logs")
    private List<WafRuleLog> applicationRuleLogs = null;

    @JsonProperty("application_rules_configured")
    private Boolean applicationRulesConfigured = false;

    @JsonProperty("application_rules_executed")
    private Boolean applicationRulesExecuted = false;

    @JsonProperty("latency_request_body_phase")
    private Integer latencyRequestBodyPhase = null;

    @JsonProperty("latency_request_header_phase")
    private Integer latencyRequestHeaderPhase = null;

    @JsonProperty("latency_response_body_phase")
    private Integer latencyResponseBodyPhase = null;

    @JsonProperty("latency_response_header_phase")
    private Integer latencyResponseHeaderPhase = null;

    @JsonProperty("psm_configured")
    private Boolean psmConfigured = false;

    @JsonProperty("psm_executed")
    private Boolean psmExecuted = false;

    @JsonProperty("psm_logs")
    private List<WafPSMLog> psmLogs = null;

    @JsonProperty("rule_logs")
    private List<WafRuleLog> ruleLogs = null;

    @JsonProperty("rules_configured")
    private Boolean rulesConfigured = false;

    @JsonProperty("rules_executed")
    private Boolean rulesExecuted = false;

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("whitelist_configured")
    private Boolean whitelistConfigured = false;

    @JsonProperty("whitelist_executed")
    private Boolean whitelistExecuted = false;

    @JsonProperty("whitelist_logs")
    private List<WafWhitelistLog> whitelistLogs = null;


    /**
     * This is the getter method this will return the attribute value.
     * Log entries generated by application specific signature rules.
     * Field introduced in 20.1.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return applicationRuleLogs
     */
    public List<WafRuleLog> getApplicationRuleLogs() {
        return applicationRuleLogs;
    }

    /**
     * This is the setter method. this will set the applicationRuleLogs
     * Log entries generated by application specific signature rules.
     * Field introduced in 20.1.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return applicationRuleLogs
     */
    public void setApplicationRuleLogs(List<WafRuleLog>  applicationRuleLogs) {
        this.applicationRuleLogs = applicationRuleLogs;
    }

    /**
     * This is the setter method this will set the applicationRuleLogs
     * Log entries generated by application specific signature rules.
     * Field introduced in 20.1.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return applicationRuleLogs
     */
    public WafLog addApplicationRuleLogsItem(WafRuleLog applicationRuleLogsItem) {
      if (this.applicationRuleLogs == null) {
        this.applicationRuleLogs = new ArrayList<WafRuleLog>();
      }
      this.applicationRuleLogs.add(applicationRuleLogsItem);
      return this;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if there are application specific signature rules in the policy.
     * Field introduced in 20.1.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return applicationRulesConfigured
     */
    public Boolean getApplicationRulesConfigured() {
        return applicationRulesConfigured;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if there are application specific signature rules in the policy.
     * Field introduced in 20.1.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param applicationRulesConfigured set the applicationRulesConfigured.
     */
    public void setApplicationRulesConfigured(Boolean  applicationRulesConfigured) {
        this.applicationRulesConfigured = applicationRulesConfigured;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if application specific signature rules were executed.
     * Field introduced in 20.1.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return applicationRulesExecuted
     */
    public Boolean getApplicationRulesExecuted() {
        return applicationRulesExecuted;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if application specific signature rules were executed.
     * Field introduced in 20.1.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param applicationRulesExecuted set the applicationRulesExecuted.
     */
    public void setApplicationRulesExecuted(Boolean  applicationRulesExecuted) {
        this.applicationRulesExecuted = applicationRulesExecuted;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Latency (in microseconds) in waf request body phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return latencyRequestBodyPhase
     */
    public Integer getLatencyRequestBodyPhase() {
        return latencyRequestBodyPhase;
    }

    /**
     * This is the setter method to the attribute.
     * Latency (in microseconds) in waf request body phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param latencyRequestBodyPhase set the latencyRequestBodyPhase.
     */
    public void setLatencyRequestBodyPhase(Integer  latencyRequestBodyPhase) {
        this.latencyRequestBodyPhase = latencyRequestBodyPhase;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Latency (in microseconds) in waf request header phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return latencyRequestHeaderPhase
     */
    public Integer getLatencyRequestHeaderPhase() {
        return latencyRequestHeaderPhase;
    }

    /**
     * This is the setter method to the attribute.
     * Latency (in microseconds) in waf request header phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param latencyRequestHeaderPhase set the latencyRequestHeaderPhase.
     */
    public void setLatencyRequestHeaderPhase(Integer  latencyRequestHeaderPhase) {
        this.latencyRequestHeaderPhase = latencyRequestHeaderPhase;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Latency (in microseconds) in waf response body phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return latencyResponseBodyPhase
     */
    public Integer getLatencyResponseBodyPhase() {
        return latencyResponseBodyPhase;
    }

    /**
     * This is the setter method to the attribute.
     * Latency (in microseconds) in waf response body phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param latencyResponseBodyPhase set the latencyResponseBodyPhase.
     */
    public void setLatencyResponseBodyPhase(Integer  latencyResponseBodyPhase) {
        this.latencyResponseBodyPhase = latencyResponseBodyPhase;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Latency (in microseconds) in waf response header phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return latencyResponseHeaderPhase
     */
    public Integer getLatencyResponseHeaderPhase() {
        return latencyResponseHeaderPhase;
    }

    /**
     * This is the setter method to the attribute.
     * Latency (in microseconds) in waf response header phase.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param latencyResponseHeaderPhase set the latencyResponseHeaderPhase.
     */
    public void setLatencyResponseHeaderPhase(Integer  latencyResponseHeaderPhase) {
        this.latencyResponseHeaderPhase = latencyResponseHeaderPhase;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if there are positive security model rules in the policy.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return psmConfigured
     */
    public Boolean getPsmConfigured() {
        return psmConfigured;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if there are positive security model rules in the policy.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param psmConfigured set the psmConfigured.
     */
    public void setPsmConfigured(Boolean  psmConfigured) {
        this.psmConfigured = psmConfigured;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if positive security model rules were executed.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return psmExecuted
     */
    public Boolean getPsmExecuted() {
        return psmExecuted;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if positive security model rules were executed.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param psmExecuted set the psmExecuted.
     */
    public void setPsmExecuted(Boolean  psmExecuted) {
        this.psmExecuted = psmExecuted;
    }
    /**
     * This is the getter method this will return the attribute value.
     * Log entries generated by waf positive security model.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return psmLogs
     */
    public List<WafPSMLog> getPsmLogs() {
        return psmLogs;
    }

    /**
     * This is the setter method. this will set the psmLogs
     * Log entries generated by waf positive security model.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return psmLogs
     */
    public void setPsmLogs(List<WafPSMLog>  psmLogs) {
        this.psmLogs = psmLogs;
    }

    /**
     * This is the setter method this will set the psmLogs
     * Log entries generated by waf positive security model.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return psmLogs
     */
    public WafLog addPsmLogsItem(WafPSMLog psmLogsItem) {
      if (this.psmLogs == null) {
        this.psmLogs = new ArrayList<WafPSMLog>();
      }
      this.psmLogs.add(psmLogsItem);
      return this;
    }
    /**
     * This is the getter method this will return the attribute value.
     * Field introduced in 17.2.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return ruleLogs
     */
    public List<WafRuleLog> getRuleLogs() {
        return ruleLogs;
    }

    /**
     * This is the setter method. this will set the ruleLogs
     * Field introduced in 17.2.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return ruleLogs
     */
    public void setRuleLogs(List<WafRuleLog>  ruleLogs) {
        this.ruleLogs = ruleLogs;
    }

    /**
     * This is the setter method this will set the ruleLogs
     * Field introduced in 17.2.1.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return ruleLogs
     */
    public WafLog addRuleLogsItem(WafRuleLog ruleLogsItem) {
      if (this.ruleLogs == null) {
        this.ruleLogs = new ArrayList<WafRuleLog>();
      }
      this.ruleLogs.add(ruleLogsItem);
      return this;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if there are modsecurity rules in the policy.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return rulesConfigured
     */
    public Boolean getRulesConfigured() {
        return rulesConfigured;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if there are modsecurity rules in the policy.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param rulesConfigured set the rulesConfigured.
     */
    public void setRulesConfigured(Boolean  rulesConfigured) {
        this.rulesConfigured = rulesConfigured;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if modsecurity rules were executed.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return rulesExecuted
     */
    public Boolean getRulesExecuted() {
        return rulesExecuted;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if modsecurity rules were executed.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param rulesExecuted set the rulesExecuted.
     */
    public void setRulesExecuted(Boolean  rulesExecuted) {
        this.rulesExecuted = rulesExecuted;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Denotes whether waf is running in detection mode or enforcement mode, whether any rules matched the transaction, and whether transaction is
     * dropped by the waf module.
     * Enum options - NO_WAF, FLAGGED, PASSED, REJECTED, WHITELISTED.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * This is the setter method to the attribute.
     * Denotes whether waf is running in detection mode or enforcement mode, whether any rules matched the transaction, and whether transaction is
     * dropped by the waf module.
     * Enum options - NO_WAF, FLAGGED, PASSED, REJECTED, WHITELISTED.
     * Field introduced in 17.2.2.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param status set the status.
     */
    public void setStatus(String  status) {
        this.status = status;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if there are whitelist rules in the policy.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return whitelistConfigured
     */
    public Boolean getWhitelistConfigured() {
        return whitelistConfigured;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if there are whitelist rules in the policy.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param whitelistConfigured set the whitelistConfigured.
     */
    public void setWhitelistConfigured(Boolean  whitelistConfigured) {
        this.whitelistConfigured = whitelistConfigured;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Set to true if whitelist rules were executed.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @return whitelistExecuted
     */
    public Boolean getWhitelistExecuted() {
        return whitelistExecuted;
    }

    /**
     * This is the setter method to the attribute.
     * Set to true if whitelist rules were executed.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as false.
     * @param whitelistExecuted set the whitelistExecuted.
     */
    public void setWhitelistExecuted(Boolean  whitelistExecuted) {
        this.whitelistExecuted = whitelistExecuted;
    }
    /**
     * This is the getter method this will return the attribute value.
     * Log entries generated by waf whitelist rules.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return whitelistLogs
     */
    public List<WafWhitelistLog> getWhitelistLogs() {
        return whitelistLogs;
    }

    /**
     * This is the setter method. this will set the whitelistLogs
     * Log entries generated by waf whitelist rules.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return whitelistLogs
     */
    public void setWhitelistLogs(List<WafWhitelistLog>  whitelistLogs) {
        this.whitelistLogs = whitelistLogs;
    }

    /**
     * This is the setter method this will set the whitelistLogs
     * Log entries generated by waf whitelist rules.
     * Field introduced in 18.2.3.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return whitelistLogs
     */
    public WafLog addWhitelistLogsItem(WafWhitelistLog whitelistLogsItem) {
      if (this.whitelistLogs == null) {
        this.whitelistLogs = new ArrayList<WafWhitelistLog>();
      }
      this.whitelistLogs.add(whitelistLogsItem);
      return this;
    }


    @Override
    public boolean equals(java.lang.Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
      WafLog objWafLog = (WafLog) o;
      return   Objects.equals(this.ruleLogs, objWafLog.ruleLogs)&&
  Objects.equals(this.status, objWafLog.status)&&
  Objects.equals(this.latencyRequestHeaderPhase, objWafLog.latencyRequestHeaderPhase)&&
  Objects.equals(this.latencyRequestBodyPhase, objWafLog.latencyRequestBodyPhase)&&
  Objects.equals(this.latencyResponseHeaderPhase, objWafLog.latencyResponseHeaderPhase)&&
  Objects.equals(this.latencyResponseBodyPhase, objWafLog.latencyResponseBodyPhase)&&
  Objects.equals(this.rulesConfigured, objWafLog.rulesConfigured)&&
  Objects.equals(this.rulesExecuted, objWafLog.rulesExecuted)&&
  Objects.equals(this.whitelistLogs, objWafLog.whitelistLogs)&&
  Objects.equals(this.whitelistConfigured, objWafLog.whitelistConfigured)&&
  Objects.equals(this.whitelistExecuted, objWafLog.whitelistExecuted)&&
  Objects.equals(this.psmLogs, objWafLog.psmLogs)&&
  Objects.equals(this.psmConfigured, objWafLog.psmConfigured)&&
  Objects.equals(this.psmExecuted, objWafLog.psmExecuted)&&
  Objects.equals(this.applicationRuleLogs, objWafLog.applicationRuleLogs)&&
  Objects.equals(this.applicationRulesConfigured, objWafLog.applicationRulesConfigured)&&
  Objects.equals(this.applicationRulesExecuted, objWafLog.applicationRulesExecuted);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class WafLog {\n");
                  sb.append("    applicationRuleLogs: ").append(toIndentedString(applicationRuleLogs)).append("\n");
                        sb.append("    applicationRulesConfigured: ").append(toIndentedString(applicationRulesConfigured)).append("\n");
                        sb.append("    applicationRulesExecuted: ").append(toIndentedString(applicationRulesExecuted)).append("\n");
                        sb.append("    latencyRequestBodyPhase: ").append(toIndentedString(latencyRequestBodyPhase)).append("\n");
                        sb.append("    latencyRequestHeaderPhase: ").append(toIndentedString(latencyRequestHeaderPhase)).append("\n");
                        sb.append("    latencyResponseBodyPhase: ").append(toIndentedString(latencyResponseBodyPhase)).append("\n");
                        sb.append("    latencyResponseHeaderPhase: ").append(toIndentedString(latencyResponseHeaderPhase)).append("\n");
                        sb.append("    psmConfigured: ").append(toIndentedString(psmConfigured)).append("\n");
                        sb.append("    psmExecuted: ").append(toIndentedString(psmExecuted)).append("\n");
                        sb.append("    psmLogs: ").append(toIndentedString(psmLogs)).append("\n");
                        sb.append("    ruleLogs: ").append(toIndentedString(ruleLogs)).append("\n");
                        sb.append("    rulesConfigured: ").append(toIndentedString(rulesConfigured)).append("\n");
                        sb.append("    rulesExecuted: ").append(toIndentedString(rulesExecuted)).append("\n");
                        sb.append("    status: ").append(toIndentedString(status)).append("\n");
                        sb.append("    whitelistConfigured: ").append(toIndentedString(whitelistConfigured)).append("\n");
                        sb.append("    whitelistExecuted: ").append(toIndentedString(whitelistExecuted)).append("\n");
                        sb.append("    whitelistLogs: ").append(toIndentedString(whitelistLogs)).append("\n");
                  sb.append("}");
      return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
      if (o == null) {
          return "null";
      }
      return o.toString().replace("\n", "\n    ");
    }
}
