package com.affiliatelink.profile;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile_publishers")
class PublisherProfile  extends Profile {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "niche")
    @Enumerated(EnumType.STRING)
    private Niche niche;

    @Column(name = "bio")
    private String bio;

    @Column(name = "country")
    private String country;


    public PublisherProfile(Long id, String email, Status status, LocalDateTime ceatedAt, String firstName, String lastName, Niche niche, String bio, String country) {
        super(id, email, status, ceatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.niche = niche;
        this.bio = bio;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Niche getNiche() {
        return niche;
    }

    public void setNiche(Niche niche) {
        this.niche = niche;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
