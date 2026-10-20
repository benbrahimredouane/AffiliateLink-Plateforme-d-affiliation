package com.affiliatelink.profile.model;

import java.time.LocalDateTime;

class AdvertiserProfile extends Profile{
    private String companyName;
    private String sector;
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

