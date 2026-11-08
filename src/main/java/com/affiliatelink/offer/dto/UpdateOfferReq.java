package com.affiliatelink.offer.dto;

import com.affiliatelink.offer.model.Niche;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpdateOfferReq(
        @NotBlank @Size(max = 255) String title,
        @NotBlank String description,
        @NotBlank
        @Size(max = 2048)
        @Pattern(regexp = "https?://[^\\s]+", message = "URL HTTP ou HTTPS attendue")
        String landingUrl,
        @NotNull Niche niche,
        @NotNull @Size(max = 20) Set<@NotBlank @Size(max = 50) String> tags
) { }