package com.colak.springawssqstutorial.service;

import com.colak.springawssqstutorial.service.producer.SQSEventPublisherService;
import io.awspring.cloud.sqs.operations.SendResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SQSEventPublisherServiceTest {

    @Autowired
    private SQSEventPublisherService sqsEventPublisherService;

    @Test
    void testSend() {
        SendResult<String> sendResult = sqsEventPublisherService.publish("message");
        Assertions.assertNotNull(sendResult);
    }
}
