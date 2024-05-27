package com.example.olx.aws_config;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Configuration
public class SendEmail {
    private final SnsClient snsClient;

    public SendEmail(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void sendEmail(String subject, String bodyText) {
        PublishRequest request = PublishRequest.builder()
                .topicArn("arn:aws:sns:us-east-1:814615723430:cc-sns")
                .subject(subject)
                .message(bodyText)
                .build();
        snsClient.publish(request);
    }
}
