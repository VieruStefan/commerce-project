package com.example.olx.service;

import com.example.olx.entity.Listing;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    Listing create(Listing listing);
    List<Listing> getAll();
    Optional<Listing> getById(Long id);
}
