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
 * The NetworkProfileUnion is a POJO class extends AviRestResource that used for creating
 * NetworkProfileUnion.
 *
 * @version 1.0
 * @since 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NetworkProfileUnion  {
    @JsonProperty("sctp_fast_path_profile")
    private SCTPFastPathProfile sctpFastPathProfile = null;

    @JsonProperty("sctp_proxy_profile")
    private SCTPProxyProfile sctpProxyProfile = null;

    @JsonProperty("tcp_fast_path_profile")
    private TCPFastPathProfile tcpFastPathProfile = null;

    @JsonProperty("tcp_proxy_profile")
    private TCPProxyProfile tcpProxyProfile = null;

    @JsonProperty("type")
    private String type = "PROTOCOL_TYPE_TCP_PROXY";

    @JsonProperty("udp_fast_path_profile")
    private UDPFastPathProfile udpFastPathProfile = null;

    @JsonProperty("udp_proxy_profile")
    private UDPProxyProfile udpProxyProfile = null;



    /**
     * This is the getter method this will return the attribute value.
     * Configure sctp fastpath network profile.
     * Field introduced in 22.1.3.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return sctpFastPathProfile
     */
    public SCTPFastPathProfile getSctpFastPathProfile() {
        return sctpFastPathProfile;
    }

    /**
     * This is the setter method to the attribute.
     * Configure sctp fastpath network profile.
     * Field introduced in 22.1.3.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param sctpFastPathProfile set the sctpFastPathProfile.
     */
    public void setSctpFastPathProfile(SCTPFastPathProfile sctpFastPathProfile) {
        this.sctpFastPathProfile = sctpFastPathProfile;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Configure sctp proxy network profile.
     * Field introduced in 22.1.3.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return sctpProxyProfile
     */
    public SCTPProxyProfile getSctpProxyProfile() {
        return sctpProxyProfile;
    }

    /**
     * This is the setter method to the attribute.
     * Configure sctp proxy network profile.
     * Field introduced in 22.1.3.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param sctpProxyProfile set the sctpProxyProfile.
     */
    public void setSctpProxyProfile(SCTPProxyProfile sctpProxyProfile) {
        this.sctpProxyProfile = sctpProxyProfile;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Allowed in enterprise edition with any value, essentials, basic, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return tcpFastPathProfile
     */
    public TCPFastPathProfile getTcpFastPathProfile() {
        return tcpFastPathProfile;
    }

    /**
     * This is the setter method to the attribute.
     * Allowed in enterprise edition with any value, essentials, basic, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param tcpFastPathProfile set the tcpFastPathProfile.
     */
    public void setTcpFastPathProfile(TCPFastPathProfile tcpFastPathProfile) {
        this.tcpFastPathProfile = tcpFastPathProfile;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Allowed in enterprise edition with any value, basic, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return tcpProxyProfile
     */
    public TCPProxyProfile getTcpProxyProfile() {
        return tcpProxyProfile;
    }

    /**
     * This is the setter method to the attribute.
     * Allowed in enterprise edition with any value, basic, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param tcpProxyProfile set the tcpProxyProfile.
     */
    public void setTcpProxyProfile(TCPProxyProfile tcpProxyProfile) {
        this.tcpProxyProfile = tcpProxyProfile;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Configure one of either proxy or fast path profiles.
     * Enum options - PROTOCOL_TYPE_TCP_PROXY, PROTOCOL_TYPE_TCP_FAST_PATH, PROTOCOL_TYPE_UDP_FAST_PATH, PROTOCOL_TYPE_UDP_PROXY,
     * PROTOCOL_TYPE_SCTP_PROXY, PROTOCOL_TYPE_SCTP_FAST_PATH.
     * Allowed in enterprise edition with any value, essentials edition(allowed values- protocol_type_tcp_fast_path,protocol_type_udp_fast_path), basic
     * edition(allowed values- protocol_type_tcp_proxy,protocol_type_tcp_fast_path,protocol_type_udp_fast_path), enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as "PROTOCOL_TYPE_TCP_PROXY".
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * This is the setter method to the attribute.
     * Configure one of either proxy or fast path profiles.
     * Enum options - PROTOCOL_TYPE_TCP_PROXY, PROTOCOL_TYPE_TCP_FAST_PATH, PROTOCOL_TYPE_UDP_FAST_PATH, PROTOCOL_TYPE_UDP_PROXY,
     * PROTOCOL_TYPE_SCTP_PROXY, PROTOCOL_TYPE_SCTP_FAST_PATH.
     * Allowed in enterprise edition with any value, essentials edition(allowed values- protocol_type_tcp_fast_path,protocol_type_udp_fast_path), basic
     * edition(allowed values- protocol_type_tcp_proxy,protocol_type_tcp_fast_path,protocol_type_udp_fast_path), enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as "PROTOCOL_TYPE_TCP_PROXY".
     * @param type set the type.
     */
    public void setType(String  type) {
        this.type = type;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Allowed in enterprise edition with any value, essentials, basic, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return udpFastPathProfile
     */
    public UDPFastPathProfile getUdpFastPathProfile() {
        return udpFastPathProfile;
    }

    /**
     * This is the setter method to the attribute.
     * Allowed in enterprise edition with any value, essentials, basic, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param udpFastPathProfile set the udpFastPathProfile.
     */
    public void setUdpFastPathProfile(UDPFastPathProfile udpFastPathProfile) {
        this.udpFastPathProfile = udpFastPathProfile;
    }

    /**
     * This is the getter method this will return the attribute value.
     * Configure udp proxy network profile.
     * Field introduced in 17.2.8, 18.1.3, 18.2.1.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @return udpProxyProfile
     */
    public UDPProxyProfile getUdpProxyProfile() {
        return udpProxyProfile;
    }

    /**
     * This is the setter method to the attribute.
     * Configure udp proxy network profile.
     * Field introduced in 17.2.8, 18.1.3, 18.2.1.
     * Allowed in enterprise edition with any value, enterprise with cloud services edition.
     * Default value when not specified in API or module is interpreted by Avi Controller as null.
     * @param udpProxyProfile set the udpProxyProfile.
     */
    public void setUdpProxyProfile(UDPProxyProfile udpProxyProfile) {
        this.udpProxyProfile = udpProxyProfile;
    }


    @Override
    public boolean equals(java.lang.Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
      NetworkProfileUnion objNetworkProfileUnion = (NetworkProfileUnion) o;
      return   Objects.equals(this.type, objNetworkProfileUnion.type)&&
  Objects.equals(this.tcpProxyProfile, objNetworkProfileUnion.tcpProxyProfile)&&
  Objects.equals(this.tcpFastPathProfile, objNetworkProfileUnion.tcpFastPathProfile)&&
  Objects.equals(this.udpFastPathProfile, objNetworkProfileUnion.udpFastPathProfile)&&
  Objects.equals(this.udpProxyProfile, objNetworkProfileUnion.udpProxyProfile)&&
  Objects.equals(this.sctpProxyProfile, objNetworkProfileUnion.sctpProxyProfile)&&
  Objects.equals(this.sctpFastPathProfile, objNetworkProfileUnion.sctpFastPathProfile);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("class NetworkProfileUnion {\n");
                  sb.append("    sctpFastPathProfile: ").append(toIndentedString(sctpFastPathProfile)).append("\n");
                        sb.append("    sctpProxyProfile: ").append(toIndentedString(sctpProxyProfile)).append("\n");
                        sb.append("    tcpFastPathProfile: ").append(toIndentedString(tcpFastPathProfile)).append("\n");
                        sb.append("    tcpProxyProfile: ").append(toIndentedString(tcpProxyProfile)).append("\n");
                        sb.append("    type: ").append(toIndentedString(type)).append("\n");
                        sb.append("    udpFastPathProfile: ").append(toIndentedString(udpFastPathProfile)).append("\n");
                        sb.append("    udpProxyProfile: ").append(toIndentedString(udpProxyProfile)).append("\n");
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
