package com.affiliatelink.profile.dto;

import com.affiliatelink.profile.model.Status;

public record ChangeStatusRes(
        Long id,
        String email,
        Status status

) { }
