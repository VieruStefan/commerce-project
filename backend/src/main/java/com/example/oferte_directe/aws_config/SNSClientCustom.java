package com.example.oferte_directe.aws_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Configuration
public class SNSClientCustom {
    private final SnsClient snsClient;
    private final String topic;
    @Autowired
    SNSClientCustom(SnsClient snsClient){
        this.snsClient = snsClient;
        this.topic = "oferte-directe-topic";
    }

    public void send_sns_message(String subject, String message){
        PublishRequest publishRequest = PublishRequest.builder()
                .subject(subject)
                .message(message)
                .topicArn(this.topic)
                .build();
        snsClient.publish(publishRequest);
    }
}