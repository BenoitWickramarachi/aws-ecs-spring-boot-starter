package com.inoneo.aws.ecs.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inoneo.aws.ecs.model.EcsContainerMetadata;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class EcsContainerMetadataBuilder {

    public static final String ECS_METADATA_READY = "READY";

    public static EcsContainerMetadata getEcsContainerMetadata(String ecsContainerMetadataFilePath) {
        if (StringUtils.isBlank(ecsContainerMetadataFilePath)) {
            throw new IllegalArgumentException("ecsContainerMetadataFilePath is blank");
        }
        EcsContainerMetadata ecsContainerMetadata = null;
        InputStream is = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            is = new FileInputStream(ecsContainerMetadataFilePath);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to open the ECS container metadata file at path: %s",
                    ecsContainerMetadataFilePath), e);
        }

        try {
            ecsContainerMetadata = mapper.readValue(is, EcsContainerMetadata.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse the ECS container metadata file", e);
        }

        if(ecsContainerMetadata.getMetadataFileStatus().equals(ECS_METADATA_READY)) {
            return ecsContainerMetadata;
        }

        throw new RuntimeException(String.format("ECS container metadata file status has not been set to '%s' so " +
                "host port information are missing, per the ECS agent documentation it can take up to 1 sec to " +
                "complete the file generation (application might boot too quickly)", ECS_METADATA_READY));
    }
}