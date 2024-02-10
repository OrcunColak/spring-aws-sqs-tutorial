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

    private final SqsTemplate sqsTemplate;


    public SendResult<String> publish(String message) {
        return publish("my-queue", message);
    }

    public SendResult<String> publish(String queueName, String message) {
        return sqsTemplate.send(queueName, message);
    }

}
