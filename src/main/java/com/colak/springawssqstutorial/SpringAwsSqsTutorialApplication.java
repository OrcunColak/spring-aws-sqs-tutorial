package com.colak.springawssqstutorial;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAwsSqsTutorialApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringAwsSqsTutorialApplication.class, args);
	}

}
