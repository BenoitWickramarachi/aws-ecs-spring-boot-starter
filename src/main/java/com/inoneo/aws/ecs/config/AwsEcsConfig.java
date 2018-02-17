package com.inoneo.aws.ecs.config;

import com.inoneo.aws.ecs.builder.EcsContainerMetadataBuilder;
import com.inoneo.aws.ecs.model.EcsContainerMetadata;
import com.netflix.appinfo.AmazonInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "awsecs", name= "enabled", havingValue = "true")
public class AwsEcsConfig {

    private static final String ECS_CONTAINER_METADATA_FILE = "ECS_CONTAINER_METADATA_FILE";

    @Bean
    public EcsContainerMetadata ecsContainerMetadata() {
        String ecsContainerMetadataFilePath = System.getenv(ECS_CONTAINER_METADATA_FILE);
        if (ecsContainerMetadataFilePath == null) {
            throw new RuntimeException(String.format("Environment variable (%s) is not set." +
                            " Verify that container metadata file generation is enable at the ECS agent level" +
                            " (https://docs.aws.amazon.com/AmazonECS/latest/developerguide/container-metadata.html" +
                            "#enable-metadata)", ECS_CONTAINER_METADATA_FILE));
        }
        return EcsContainerMetadataBuilder.getEcsContainerMetadata(ecsContainerMetadataFilePath);
    }

    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils,
                                                         EcsContainerMetadata ecsContainerMetadata) {
        EurekaInstanceConfigBean b = new EurekaInstanceConfigBean(inetUtils);
        AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
        b.setDataCenterInfo(info);
        b.setHostname(info.get(AmazonInfo.MetaDataKey.localHostname));
        b.setIpAddress(info.get(AmazonInfo.MetaDataKey.localIpv4));
        b.setNonSecurePort(ecsContainerMetadata.getPortMappings().get(0).getHostPort());
        return b;
    }

}
