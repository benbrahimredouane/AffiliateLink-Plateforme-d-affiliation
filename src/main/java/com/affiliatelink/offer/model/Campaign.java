package com.affiliatelink.offer.model;

import com.affiliatelink.common.exception.StatusInvalidException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offer_campaigns")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "payout_model", nullable = false, length = 20)
    private PayoutModel payoutModel;

    @Column(name = "payout_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal payoutValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private CampaignStatus campaignStatus = CampaignStatus.DRAFT;


    public Campaign(String name, LocalDate startDate, LocalDate endDate,
                    BigDecimal budget, PayoutModel payoutModel, BigDecimal payoutValue) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.payoutModel = payoutModel;
        this.payoutValue = payoutValue;
    }

    void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void changeStatus(CampaignStatus newStatus) {
        boolean allowed = (campaignStatus == CampaignStatus.DRAFT && newStatus == CampaignStatus.ACTIVE)
                || (campaignStatus == CampaignStatus.ACTIVE
                && (newStatus == CampaignStatus.PAUSED || newStatus == CampaignStatus.ENDED))
                || (campaignStatus == CampaignStatus.PAUSED
                && (newStatus == CampaignStatus.ACTIVE || newStatus == CampaignStatus.ENDED));

        if (!allowed) {
            throw new StatusInvalidException("cannot change from " + campaignStatus + " to " + newStatus);
        }

        if (newStatus == CampaignStatus.ACTIVE && endDate.isBefore(LocalDate.now())) {
            throw new StatusInvalidException("the end date has passed, this campaign cannot be activated");
        }

        this.campaignStatus = newStatus;
    }
}