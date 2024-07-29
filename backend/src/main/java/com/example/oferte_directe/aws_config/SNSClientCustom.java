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
    private final String topicArn;
    @Autowired
    SNSClientCustom(SnsClient snsClient){
        this.snsClient = snsClient;
        this.topicArn = "arn:aws:sns:us-east-1:137412757438:oferte-directe-topic";
    }

    public void send_sns_message(String subject, String message){
        PublishRequest publishRequest = PublishRequest.builder()
                .subject(subject)
                .message(message)
                .topicArn(this.topicArn)
                .build();
        snsClient.publish(publishRequest);
    }
}