package com.affiliatelink.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePublisherReq(    @NotBlank
                                     @Email
                                     @Size(max = 255)
                                     String email,

                                     @NotBlank
                                     @Size(max = 100)
                                     String firstName,

                                     @NotBlank
                                     @Size(max = 100)
                                     String lastName,

                                     @NotNull
                                     Niche niche,

                                     @Size(max = 1000)
                                     String bio,

                                     @NotNull
                                     @Size(max = 50)
                                     String country) { }
