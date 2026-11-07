package com.affiliatelink.offer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Setter;

@Entity
@Table(name = "offer_offers")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "advertiser_id" ,nullable = false, updatable = false)
    private Long advertiserId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "landing_url",nullable = false,length = 2048)
    private String landingUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 30)
    private Niche niche;

    @Column(name = "created_at",nullable = false ,updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "offer")
    private List<Campaign> campaigns = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "offer_offer_tags",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();



    @PrePersist
    protected void onCreate(){
        if(createdAt == null){
            createdAt = LocalDateTime.now();
        }
    }
    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void update(String title, String description, String landingUrl, Niche niche) {
        this.title = title;
        this.description = description;
        this.landingUrl = landingUrl;
        this.niche = niche;
    }

    public void replaceTags(Set<Tag> newTags) {
        tags.retainAll(newTags);   // remove the tags that are no longer wanted
        tags.addAll(newTags);      // add the new ones
    }






    }




