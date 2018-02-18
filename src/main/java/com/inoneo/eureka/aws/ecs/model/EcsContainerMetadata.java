package com.inoneo.eureka.aws.ecs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EcsContainerMetadata {

    @JsonProperty("Cluster")
    private String cluster;
    @JsonProperty("ContainerInstanceARN")
    private String containerInstanceARN;
    @JsonProperty("TaskARN")
    private String taskARN;
    @JsonProperty("ContainerID")
    private String containerID;
    @JsonProperty("ContainerName")
    private String containerName;
    @JsonProperty("DockerContainerName")
    private String dockerContainerName;
    @JsonProperty("ImageID")
    private String imageID;
    @JsonProperty("ImageName")
    private String imageName;
    @JsonProperty("PortMappings")
    private List<PortMapping> portMappings;
    @JsonProperty("Networks")
    private List<Network> networks;
    @JsonProperty("MetadataFileStatus")
    private String metadataFileStatus;

    public String getCluster() {
        return cluster;
    }

    public String getContainerInstanceARN() {
        return containerInstanceARN;
    }

    public String getTaskARN() {
        return taskARN;
    }

    public String getContainerID() {
        return containerID;
    }

    public String getContainerName() {
        return containerName;
    }

    public String getDockerContainerName() {
        return dockerContainerName;
    }

    public String getImageID() {
        return imageID;
    }

    public String getImageName() {
        return imageName;
    }

    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public String getMetadataFileStatus() {
        return metadataFileStatus;
    }

}
