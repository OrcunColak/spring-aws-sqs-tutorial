package com.colak.springawssqstutorial.service.producer;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SqsConsumer {

    @SqsListener(value = "my-queue", pollTimeoutSeconds = "5")
    public void sqlListener(String message) {
        log.info("message received : {}", message);
    }
}
