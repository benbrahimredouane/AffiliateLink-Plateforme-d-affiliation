package com.affiliatelink.profile;

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
