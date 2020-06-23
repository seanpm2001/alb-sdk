/*
 * Avi avi_global_spec Object API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 20.1.1
 * Contact: support@avinetworks.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.vmware.avi.sdk.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * AzureNetworkInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2020-03-12T12:27:26.755+05:30[Asia/Kolkata]")
public class AzureNetworkInfo {
  @JsonProperty("management_network_id")
  private String managementNetworkId = null;

  @JsonProperty("se_network_id")
  private String seNetworkId = null;

  @JsonProperty("virtual_network_id")
  private String virtualNetworkId = null;

  public AzureNetworkInfo managementNetworkId(String managementNetworkId) {
    this.managementNetworkId = managementNetworkId;
    return this;
  }

   /**
   * Id of the Azure subnet used as management network for the Service Engines. If set, Service Engines will have a dedicated management NIC, otherwise, they operate in inband mode. Field introduced in 18.2.3.
   * @return managementNetworkId
  **/
  @Schema(description = "Id of the Azure subnet used as management network for the Service Engines. If set, Service Engines will have a dedicated management NIC, otherwise, they operate in inband mode. Field introduced in 18.2.3.")
  public String getManagementNetworkId() {
    return managementNetworkId;
  }

  public void setManagementNetworkId(String managementNetworkId) {
    this.managementNetworkId = managementNetworkId;
  }

  public AzureNetworkInfo seNetworkId(String seNetworkId) {
    this.seNetworkId = seNetworkId;
    return this;
  }

   /**
   * Id of the Azure subnet where Avi Controller will create the Service Engines. . Field introduced in 17.2.1.
   * @return seNetworkId
  **/
  @Schema(description = "Id of the Azure subnet where Avi Controller will create the Service Engines. . Field introduced in 17.2.1.")
  public String getSeNetworkId() {
    return seNetworkId;
  }

  public void setSeNetworkId(String seNetworkId) {
    this.seNetworkId = seNetworkId;
  }

  public AzureNetworkInfo virtualNetworkId(String virtualNetworkId) {
    this.virtualNetworkId = virtualNetworkId;
    return this;
  }

   /**
   * Virtual network where Virtual IPs will belong. Field introduced in 17.2.1.
   * @return virtualNetworkId
  **/
  @Schema(description = "Virtual network where Virtual IPs will belong. Field introduced in 17.2.1.")
  public String getVirtualNetworkId() {
    return virtualNetworkId;
  }

  public void setVirtualNetworkId(String virtualNetworkId) {
    this.virtualNetworkId = virtualNetworkId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AzureNetworkInfo azureNetworkInfo = (AzureNetworkInfo) o;
    return Objects.equals(this.managementNetworkId, azureNetworkInfo.managementNetworkId) &&
        Objects.equals(this.seNetworkId, azureNetworkInfo.seNetworkId) &&
        Objects.equals(this.virtualNetworkId, azureNetworkInfo.virtualNetworkId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(managementNetworkId, seNetworkId, virtualNetworkId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AzureNetworkInfo {\n");
    
    sb.append("    managementNetworkId: ").append(toIndentedString(managementNetworkId)).append("\n");
    sb.append("    seNetworkId: ").append(toIndentedString(seNetworkId)).append("\n");
    sb.append("    virtualNetworkId: ").append(toIndentedString(virtualNetworkId)).append("\n");
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
