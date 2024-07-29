package com.example.oferte_directe.service;

import com.example.oferte_directe.entity.Listing;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    Listing create(Listing listing);
    List<Listing> getAll();
    Optional<Listing> getById(Long id);
    Listing save(Listing listing);
}
