package com.affiliatelink.offer.dto;

import com.affiliatelink.offer.model.Niche;
import java.time.LocalDateTime;
import java.util.Set;

public record FindOfferRes(
        Long id,
        Long advertiserId,
        String title,
        String description,
        String landingUrl,
        Niche niche,
        LocalDateTime createdAt,
        Set<String> tags
) {}