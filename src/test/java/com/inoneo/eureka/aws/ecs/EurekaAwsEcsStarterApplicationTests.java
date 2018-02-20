package com.inoneo.eureka.aws.ecs;

import com.inoneo.eureka.aws.ecs.model.EcsContainerMetadata;
import com.inoneo.eureka.aws.ecs.model.Network;
import com.inoneo.eureka.aws.ecs.model.PortMapping;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaAwsEcsStarterApplicationTests {

	@Inject
	private EcsContainerMetadata ecsContainerMetadata;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(ecsContainerMetadata);
		Assert.assertEquals("poc", ecsContainerMetadata.getCluster());
		Assert.assertEquals("arn:aws:ecs:us-east-2:775836073838:container-instance/c2e54429-1970-4fe4-9013-1dfb1cf35cb5", ecsContainerMetadata.getContainerInstanceARN());
		Assert.assertEquals("arn:aws:ecs:us-east-2:775836073838:task/bee66728-bee9-4644-bc62-6497a1b26f17", ecsContainerMetadata.getTaskARN());
		Assert.assertEquals("51eba18851e9838c1703c4d94cdd179df2890575728ef3f41d44fcbf9dcd48a7", ecsContainerMetadata.getContainerID());
		Assert.assertEquals("eureka", ecsContainerMetadata.getContainerName());
		Assert.assertEquals("/ecs-eureka-6-eureka-e09dfaa7a2fbf3b86200", ecsContainerMetadata.getDockerContainerName());
		Assert.assertEquals("sha256:9f5b863b28488870554df8c5ee115dc001bb2221b54d141e7c7a76e5dcf258af", ecsContainerMetadata.getImageID());
		Assert.assertEquals("775836073838.dkr.ecr.us-east-2.amazonaws.com/eureka:latest", ecsContainerMetadata.getImageName());

		PortMapping portMapping = ecsContainerMetadata.getPortMappings().get(0);
		Assert.assertEquals((Integer) 8761, portMapping.getContainerPort());
		Assert.assertEquals((Integer) 32781, portMapping.getHostPort());
		Assert.assertEquals("0.0.0.0", portMapping.getBindIp());
		Assert.assertEquals("tcp", portMapping.getProtocol());

		Network network = ecsContainerMetadata.getNetworks().get(0);
		Assert.assertEquals("bridge", network.getNetworkMode());
		Assert.assertEquals("172.17.0.2", network.getiPv4Addresses().get(0));

		Assert.assertEquals("READY", ecsContainerMetadata.getMetadataFileStatus());
	}

}
