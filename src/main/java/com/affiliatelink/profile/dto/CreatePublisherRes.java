package com.affiliatelink.profile.dto;

import com.affiliatelink.profile.model.Niche;
import com.affiliatelink.profile.model.Status;

import java.time.LocalDateTime;

public record CreatePublisherRes(
        Long id,
        String email,
        Status status,
        LocalDateTime createdAt,
        String firstName,
        String lastName,
        Niche niche,
        String bio,
        String country
)
{}
