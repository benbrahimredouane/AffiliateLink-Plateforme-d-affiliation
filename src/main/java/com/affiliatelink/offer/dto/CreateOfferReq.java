package com.affiliatelink.offer.dto;

import com.affiliatelink.offer.model.Niche;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CreateOfferReq (
        @NotNull(message = "advertider id is required")
        Long advertiserId,

        @NotBlank(message = "title is required")
        @Size(max = 255)
        String title,

        @NotBlank(message = "description must be not blank")
        String description,

        @NotBlank(message = "landing is required")
        @Size(max = 2048)
        @Pattern(regexp = "^https?://\\S+$", message = "url must start with http:// or https://")
        String landingUrl,

        @NotNull(message = "niche is required ")
        Niche niche,

        Set<@NotBlank @Size(max = 50) String> tags


) { }
