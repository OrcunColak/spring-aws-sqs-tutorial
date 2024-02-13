package com.colak.springawssqstutorial.service.producer;

import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SQSEventPublisherService {

    public static final String DEFAULT_QUEUE_NAME = "my-queue";

    private final SqsTemplate sqsTemplate;


    public SendResult<String> publish(String message) {
        return publish(DEFAULT_QUEUE_NAME, message);
    }

    public SendResult<String> publish(String queueName, String message) {
        return sqsTemplate.send(queueName, message);
    }

}
