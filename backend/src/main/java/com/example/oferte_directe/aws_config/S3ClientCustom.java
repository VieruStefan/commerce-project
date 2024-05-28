package com.example.oferte_directe.aws_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class S3ClientCustom {
    private S3Client s3Client;
    private String bucketName;
    private Region region;
    @Autowired
    S3ClientCustom(S3Client s3Client){
        this.bucketName = "oferte-directe-bucket";
        this.region = Region.US_EAST_1;
        this.s3Client = s3Client;
    }
//    @Bean
//    public S3Client s3Client() {
//        return S3Client.builder()
//                .region(Region.US_EAST_1)
//                .build();
//    }

    public boolean put_object(String key, InputStream inputStream) throws IOException {
        PutObjectRequest objReq = PutObjectRequest.builder()
                .bucket(this.bucketName)
                .key(key)
                .acl("public-read")
                .build();

        s3Client.putObject(objReq, RequestBody.fromInputStream(inputStream, inputStream.available()));
        return true;
    }

    public String get_object_url(String key){
        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, key);
        return fileUrl;
    }
}
