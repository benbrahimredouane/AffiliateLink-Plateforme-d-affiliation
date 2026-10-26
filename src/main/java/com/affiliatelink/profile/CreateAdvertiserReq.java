package com.affiliatelink.profile;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAdvertiserReq(
        @NotBlank(message = "email must not be blank")
        @Email
        @Size(max = 255)
        String email,


        @NotBlank(message = "companyname must not be blank")
        @Size(max = 200)
        String companyName,

        @NotBlank(message = "sector cant be blank")
        @Size(max = 90)
        String sector,

        @NotBlank(message = "website cant be blank")
        @Size(max = 100)
        String website

) { }

