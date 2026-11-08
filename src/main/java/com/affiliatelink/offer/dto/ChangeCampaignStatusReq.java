package com.affiliatelink.offer.dto;

import com.affiliatelink.offer.model.CampaignStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeCampaignStatusReq(@NotNull CampaignStatus status) { }
