package com.hello.ec2;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ec2Config {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonEC2 amazonEC2() {
        return AmazonEC2ClientBuilder.standard()
                .withRegion(region)
                .build();
    }
}
