package com.affiliatelink.profile;

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
