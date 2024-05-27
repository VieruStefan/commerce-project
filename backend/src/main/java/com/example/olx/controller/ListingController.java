package com.example.olx.controller;

import com.example.olx.aws_config.SendEmail;
import com.example.olx.entity.Listing;
import com.example.olx.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

@RestController
@RequestMapping("/listings")
public class ListingController {
    private final ListingService listingService;
    private final S3Client s3Client;
    @Autowired

    public ListingController(ListingService listingService, S3Client s3Client) {
        this.listingService = listingService;
        this.s3Client = s3Client;
    }

    @PostMapping("/")
    public void create(@RequestBody Listing listing){
        //s3
        listingService.create(listing);
        PutObjectRequest objReq = PutObjectRequest.builder()
                .bucket("oferte-directe-bucket")
                .key("hello.txt")
                .acl("public-read")
                .build();
        String message = "hello";
        InputStream inputStream = new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8));
        s3Client.putObject(objReq, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(inputStream, message.length()));
    }
}
