package com.affiliatelink.profile.dto;

import com.affiliatelink.profile.model.Niche;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePublisherReq(    @NotBlank(message = "email must not be blank")
                                     @Email
                                     @Size(max = 255 )
                                     String email,

                                     @NotBlank(message = "first name must not be blank")
                                     @Size(max = 100)
                                     String firstName,

                                     @NotBlank(message = "last name must not be blank")
                                     @Size(max = 100 , message = "try less than 100 chars")
                                     String lastName,

                                     @NotNull(message = "niche must not be null")
                                     Niche niche,

                                     @Size(max = 1000 ,message = "try less than 1000 chars")
                                     String bio,

                                     @NotBlank(message = "country must not be null")
                                     @Size(max = 50 , message = "try less than 50 chars")
                                     String country) { }
