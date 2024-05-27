package com.example.olx.controller;

import com.example.olx.aws_config.SendEmail;
import com.example.olx.aws_config.SnsConfig;
import com.example.olx.entity.Listing;
import com.example.olx.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping
public class ListingController {
    private final ListingService listingService;
    private final S3Client s3Client;
    private final SendEmail sns;
    @Autowired

    public ListingController(ListingService listingService, S3Client s3Client, SendEmail sns) {
        this.listingService = listingService;
        this.s3Client = s3Client;
        this.sns = sns;
    }
    @PostMapping("/")
    public Listing create(@RequestBody Listing listing){
        //s3
        listingService.create(listing);
        PutObjectRequest objReq = PutObjectRequest.builder()
                .bucket("")
                .key()
                .build();
        s3Client.putObject(objReq, software.amazon.awssdk.core.sync.RequestBody.fromInputStream(cv.getInputStream(), cv.getInputStream().available()));

    }
}
