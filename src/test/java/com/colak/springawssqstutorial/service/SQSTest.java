package com.colak.springawssqstutorial.service;

import com.colak.springawssqstutorial.service.producer.SQSEventPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageBatchRequestEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Slf4j
class SQSTest {

    @Autowired
    private SqsAsyncClient sqsAsyncClient;

    @Test
    void testCreateQueue() throws ExecutionException, InterruptedException {
        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(SQSEventPublisherService.DEFAULT_QUEUE_NAME)
                .build();

        CreateQueueResponse createQueueResponse = sqsAsyncClient.createQueue(createQueueRequest).get();
        String queueUrl = createQueueResponse.queueUrl();
        //  http://sqs.eu-central-1.localhost.localstack.cloud:4566/000000000000/myqueue
        log.info("queueUrl : {}", queueUrl);
        Assertions.assertNotNull(queueUrl);

        sendBatchMessages(queueUrl);
    }

    private void sendBatchMessages(String queueUrl) {
        List<SendMessageBatchRequestEntry> messageList = messageList();

        // Size of each sublist
        int sublistSize = 10;

        // Iterate the list with sublist of size 10
        for (int i = 0; i < messageList.size(); i += sublistSize) {
            int endIndex = Math.min(i + sublistSize, messageList.size());
            List<SendMessageBatchRequestEntry> sublist = messageList.subList(i, endIndex);

            // Process the current sublist
            SendMessageBatchRequest sendMessageBatchRequest = SendMessageBatchRequest.builder()
                    .queueUrl(queueUrl)
                    .entries(sublist)
                    .build();
            sqsAsyncClient.sendMessageBatch(sendMessageBatchRequest);
        }
    }

    private List<SendMessageBatchRequestEntry> messageList() {
        ArrayList<SendMessageBatchRequestEntry> list = new ArrayList<>();

        // Create a list of SendMessageBatchRequestEntry
        for (int index = 0; index < 200; index++) {
            SendMessageBatchRequestEntry requestEntry = SendMessageBatchRequestEntry.builder()
                    .id("id" + index)
                    .messageBody("msg" + index)
                    .build();
            list.add(requestEntry);
        }
        return list;
    }
}
