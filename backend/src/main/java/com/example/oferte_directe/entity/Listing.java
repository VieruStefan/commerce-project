package com.example.oferte_directe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name="listing")
@Data
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int price;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String image_url;
    private LocalDateTime pub_date;
    private LocalDateTime updated_date;

}
