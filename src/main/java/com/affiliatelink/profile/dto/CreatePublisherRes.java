package com.affiliatelink.profile.dto;

import com.affiliatelink.profile.model.Niche;
import com.affiliatelink.profile.model.Status;

import java.time.OffsetDateTime;

public record CreatePublisherRes(
        Long id,
        String email,
        Status status,
        OffsetDateTime createdAt,
        String firstName,
        String lastName,
        Niche niche,
        String bio,
        String country
)
{}
