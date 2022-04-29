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
 * The ServiceengineFaults is a POJO class extends AviRestResource that used for creating
 * ServiceengineFaults.
 *
 * @version 1.0
 * @since 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceengineFaults  {
    @JsonProperty("debug_faults")
    private Boolean debugFaults = true;



    /**
     * This is the getter method this will return the attribute value.
     * Enable debug faults.
     * Field introduced in 20.1.6.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as true.
     * @return debugFaults
     */
    public Boolean getDebugFaults() {
        return debugFaults;
    }

    /**
     * This is the setter method to the attribute.
     * Enable debug faults.
     * Field introduced in 20.1.6.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as true.
     * @param debugFaults set the debugFaults.
     */
    public void setDebugFaults(Boolean  debugFaults) {
        this.debugFaults = debugFaults;
    }


    @Override
    public boolean equals(java.lang.Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
      ServiceengineFaults objServiceengineFaults = (ServiceengineFaults) o;
      return   Objects.equals(this.debugFaults, objServiceengineFaults.debugFaults);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class ServiceengineFaults {\n");
                  sb.append("    debugFaults: ").append(toIndentedString(debugFaults)).append("\n");
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
