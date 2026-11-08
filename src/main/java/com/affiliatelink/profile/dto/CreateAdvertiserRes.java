package com.affiliatelink.profile.dto;

import com.affiliatelink.profile.model.Status;

import java.time.LocalDateTime;

public record CreateAdvertiserRes(
        Long id,
        String email,
        Status status,
        LocalDateTime createdAt,
        String companyName,
        String sector,
        String website


) { }
