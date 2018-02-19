package com.inoneo.eureka.aws.ecs.builder;

import com.inoneo.eureka.aws.ecs.model.EcsContainerMetadata;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;

public class EcsContainerMetadataBuilderTests {

    public static final String INVALID_FILE_JSON = "invalidFile.json";

    @Test(expected = IllegalArgumentException.class)
    public void builderShouldThrowIllegalArgumentIfNoParam() {
        EcsContainerMetadataBuilder.getEcsContainerMetadata(null);
    }

    @Test(expected = RuntimeException.class)
    public void builderShouldThrowRuntimeIfNoFile() {
        EcsContainerMetadataBuilder.getEcsContainerMetadata("/invalid/path");
    }

    @Test(expected = RuntimeException.class)
    public void builderShouldThrowRuntimeIfInvalidFile() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("ecs-container-metadata-invalid.json");
        EcsContainerMetadataBuilder.getEcsContainerMetadata(resource.getPath());
    }

    @Test(expected = RuntimeException.class)
    public void builderShouldThrowRuntimeIfEcsFileNotReady() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("ecs-container-metadata-not-ready.json");
        EcsContainerMetadataBuilder.getEcsContainerMetadata(resource.getPath());
    }

    @Test
    public void builderShouldReturnABean() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("ecs-container-metadata.json");
        EcsContainerMetadata ecsContainerMetadata =
                EcsContainerMetadataBuilder.getEcsContainerMetadata(resource.getPath());
        Assert.assertNotNull(ecsContainerMetadata);
    }

}
