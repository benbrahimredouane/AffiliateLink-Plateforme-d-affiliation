package com.affiliatelink.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Entity
@Table(name = "profile_advrtisers")
class AdvertiserProfile extends Profile {

    @Column(name = "company_name" ,nullable = false)
    private String companyName;
    @Column(name = "sector" ,nullable = false)
    private String sector;
    @Column(name = "website" ,nullable = false)
    private String website;

    public AdvertiserProfile(Long id, String email, Status status, LocalDateTime ceatedAt, String companyName, String sector, String website) {
        super(id, email, status, ceatedAt);
        this.companyName = companyName;
        this.sector = sector;
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}

