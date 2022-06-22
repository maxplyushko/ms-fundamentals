package com.epam.resourceservice.config;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AwsS3Config {

  @Value("${aws.s3.endpoint-url}")
  private String awsS3EndpointUrl;

  @Value("${aws.s3.bucket-name}")
  private String bucketName;

  @Bean
  AmazonS3 amazonS3Client() {
    EndpointConfiguration endpointConfiguration = new EndpointConfiguration(awsS3EndpointUrl,
        Regions.US_EAST_1.getName());

    return AmazonS3ClientBuilder.standard()
        .withEndpointConfiguration(endpointConfiguration)
        .withPathStyleAccessEnabled(true).build();
  }
}
