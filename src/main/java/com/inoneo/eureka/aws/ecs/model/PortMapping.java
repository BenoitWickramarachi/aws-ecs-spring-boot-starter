package com.inoneo.eureka.aws.ecs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortMapping {

    @JsonProperty("ContainerPort")
    private Integer containerPort;

    @JsonProperty("HostPort")
    private Integer hostPort;

    @JsonProperty("BindIp")
    private String bindIp;

    @JsonProperty("Protocol")
    private String protocol;

    public Integer getContainerPort() {
        return containerPort;
    }

    public Integer getHostPort() {
        return hostPort;
    }

    public String getBindIp() {
        return bindIp;
    }

    public String getProtocol() {
        return protocol;
    }
}
