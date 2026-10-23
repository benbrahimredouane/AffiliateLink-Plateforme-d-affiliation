package com.affiliatelink.profile;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAdvertiserReq(
        @NotBlank
        @Email
        @Size(max = 255)
        String email,


        @NotBlank
        @Size(max = 200)
        String companyName,

        @NotBlank
        @Size(max = 90)
        String sector,

        @NotBlank
        @Size(max = 100)
        String website

) { }

//@Column(name = "company_name", nullable = false)
//private String companyName;
//@Column(name = "sector", nullable = false)
//private String sector;
//@Column(name = "website", nullable = false)
//private String website;