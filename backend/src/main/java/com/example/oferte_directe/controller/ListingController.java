package com.example.oferte_directe.controller;

import com.example.oferte_directe.aws_config.S3ClientCustom;
import com.example.oferte_directe.entity.Listing;
import com.example.oferte_directe.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/listings")
@CrossOrigin(origins = "http://54.243.193.76:4200")
public class ListingController {
    private final ListingService listingService;
    private final S3ClientCustom s3ClientCustom;
    @Autowired

    public ListingController(ListingService listingService, S3ClientCustom s3ClientCustom) {
        this.listingService = listingService;
        this.s3ClientCustom = s3ClientCustom;
    }

    @PostMapping(path="/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Listing create(@RequestPart Listing listing, @RequestPart("picture") MultipartFile picture) throws IOException {
        //s3
        Listing listing_db = listingService.create(listing);
        String listing_key = "listing_"+listing_db.getId()+".png";
        s3ClientCustom.put_object(listing_key, picture.getInputStream());
        listing_db.setImage_url(s3ClientCustom.get_object_url(listing_key));
        return listingService.save(listing_db);
    }

    @GetMapping(path = "/")
    public List<Listing> get_listings(){
        return listingService.getAll();
    }
}
