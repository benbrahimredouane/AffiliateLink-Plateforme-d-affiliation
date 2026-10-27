package com.affiliatelink.profile.dto;

import com.affiliatelink.profile.model.Status;

import java.time.OffsetDateTime;

public record CreateAdvertiserRes(
        Long id,
        String email,
        Status status,
        OffsetDateTime createdAt,
        String companyName,
        String sector,
        String website


) { }
