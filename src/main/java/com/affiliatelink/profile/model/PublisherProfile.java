package com.affiliatelink.profile.model;

import java.time.LocalDateTime;

class PublisherProfile  extends Profile{
    private String firstName;
    private String lastName;
    private Niche niche;
    private String bio;
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
