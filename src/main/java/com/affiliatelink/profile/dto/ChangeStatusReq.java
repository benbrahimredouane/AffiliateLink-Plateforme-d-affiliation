package com.affiliatelink.profile.dto;


import com.affiliatelink.profile.model.Status;
import jakarta.validation.constraints.NotNull;

public record ChangeStatusReq(
        @NotNull
        Status status

) { }
