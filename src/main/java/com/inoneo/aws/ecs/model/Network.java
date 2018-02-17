package com.inoneo.aws.ecs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Network {

     @JsonProperty("NetworkMode")
     private String networkMode;

     @JsonProperty("IPv4Addresses")
     private List<String> iPv4Addresses;

     public String getNetworkMode() {
          return networkMode;
     }

     public List<String> getiPv4Addresses() {
          return iPv4Addresses;
     }
}
