package com.example.oferte_directe.controller;

import com.example.oferte_directe.aws_config.S3ClientCustom;
import com.example.oferte_directe.aws_config.SNSClientCustom;
import com.example.oferte_directe.entity.Listing;
import com.example.oferte_directe.service.ListingService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/listings")
//@CrossOrigin(origins = "http://54.243.193.76:4200")
public class ListingController {
    private final ListingService listingService;
    private final S3ClientCustom s3ClientCustom;
    private final SNSClientCustom snsClientCustom;
    @Autowired

    public ListingController(ListingService listingService,
                             S3ClientCustom s3ClientCustom,
                             SNSClientCustom snsClientCustom) {
        this.listingService = listingService;
        this.s3ClientCustom = s3ClientCustom;
        this.snsClientCustom = snsClientCustom;
    }

    @PostMapping(path="/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Listing> create(@RequestPart Listing listing, @RequestPart("picture") MultipartFile picture) throws IOException {
        Listing listing_db = listingService.create(listing);
        String listing_key = "listing_"+listing_db.getId()+".png";
        s3ClientCustom.put_object(listing_key, picture.getInputStream());
        listing_db.setImage_url(s3ClientCustom.get_object_url(listing_key));
        Listing response = listingService.save(listing_db);

        snsClientCustom.send_sns_message("Oferte Directe","S-a postat un nou anunț!");
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Listing>> get_listings(){
        return ResponseEntity.ok(listingService.getAll());
    }
}
