package com.affiliatelink.offer.dto;

import com.affiliatelink.offer.model.Niche;

import java.time.LocalDateTime;
import java.util.Set;

public record SearchOfferRes(
        Long id,
        Long advertiserId,
        String title,
        String landingUrl,
        Niche niche,
        Set<String> tags,
        LocalDateTime createdAt
) { }