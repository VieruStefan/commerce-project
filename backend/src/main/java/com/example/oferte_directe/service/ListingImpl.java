package com.example.oferte_directe.service;

import com.example.oferte_directe.entity.Listing;
import com.example.oferte_directe.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingImpl implements ListingService{
    private final ListingRepository listingRepository;

    public ListingImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Override
    public Listing create(Listing listing) {
       return listingRepository.save(listing);
    }

    @Override
    public List<Listing> getAll() {
        return listingRepository.findAll();
    }

    @Override
    public Optional<Listing> getById(Long id) {
       return listingRepository.findById(id);
    }

    @Override
    public Listing save(Listing listing) {
        return this.listingRepository.save(listing);
    }
}
