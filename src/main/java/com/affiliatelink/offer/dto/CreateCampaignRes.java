package com.affiliatelink.offer.dto;

import com.affiliatelink.offer.model.CampaignStatus;
import com.affiliatelink.offer.model.PayoutModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateCampaignRes(
        Long id,
        Long offerId,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal budget,
        PayoutModel payoutModel,
        BigDecimal payoutValue,
        CampaignStatus status
) { }