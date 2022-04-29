/*
 * Copyright 2021 VMware, Inc.
 * SPDX-License-Identifier: Apache License 2.0
 */

package com.vmware.avi.sdk.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The SeGroupAnalyticsPolicy is a POJO class extends AviRestResource that used for creating
 * SeGroupAnalyticsPolicy.
 *
 * @version 1.0
 * @since 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeGroupAnalyticsPolicy  {
    @JsonProperty("metrics_event_thresholds")
    private List<MetricsEventThreshold> metricsEventThresholds = null;


    /**
     * This is the getter method this will return the attribute value.
     * Thresholds for various events generated by metrics system.
     * Field introduced in 20.1.3.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return metricsEventThresholds
     */
    public List<MetricsEventThreshold> getMetricsEventThresholds() {
        return metricsEventThresholds;
    }

    /**
     * This is the setter method. this will set the metricsEventThresholds
     * Thresholds for various events generated by metrics system.
     * Field introduced in 20.1.3.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return metricsEventThresholds
     */
    public void setMetricsEventThresholds(List<MetricsEventThreshold>  metricsEventThresholds) {
        this.metricsEventThresholds = metricsEventThresholds;
    }

    /**
     * This is the setter method this will set the metricsEventThresholds
     * Thresholds for various events generated by metrics system.
     * Field introduced in 20.1.3.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return metricsEventThresholds
     */
    public SeGroupAnalyticsPolicy addMetricsEventThresholdsItem(MetricsEventThreshold metricsEventThresholdsItem) {
      if (this.metricsEventThresholds == null) {
        this.metricsEventThresholds = new ArrayList<MetricsEventThreshold>();
      }
      this.metricsEventThresholds.add(metricsEventThresholdsItem);
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
      SeGroupAnalyticsPolicy objSeGroupAnalyticsPolicy = (SeGroupAnalyticsPolicy) o;
      return   Objects.equals(this.metricsEventThresholds, objSeGroupAnalyticsPolicy.metricsEventThresholds);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class SeGroupAnalyticsPolicy {\n");
                  sb.append("    metricsEventThresholds: ").append(toIndentedString(metricsEventThresholds)).append("\n");
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
