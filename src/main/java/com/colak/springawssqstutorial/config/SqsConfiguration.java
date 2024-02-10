package com.colak.springawssqstutorial.config;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
@Slf4j
public class SqsConfiguration {

    @Bean
    SqsAsyncClient sqsAsyncClient() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create("test", "test");
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);

        return SqsAsyncClient
                .builder()
                .region(Region.EU_CENTRAL_1)
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public SqsTemplate testSqsTemplate(SqsAsyncClient sqsAsyncClient) {
        return SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .build();
    }
}
