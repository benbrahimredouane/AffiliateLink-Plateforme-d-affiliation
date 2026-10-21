package com.affiliatelink.profile;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profile_advertisers")
@Getter
@Setter
@NoArgsConstructor
class AdvertiserProfile extends Profile {

    @Column(name = "company_name", nullable = false)
    private String companyName;
    @Column(name = "sector", nullable = false)
    private String sector;
    @Column(name = "website", nullable = false)
    private String website;


}

