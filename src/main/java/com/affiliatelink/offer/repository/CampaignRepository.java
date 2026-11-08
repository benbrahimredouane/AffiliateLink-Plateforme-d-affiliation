package com.affiliatelink.offer.repository;

import com.affiliatelink.offer.model.Campaign;
import com.affiliatelink.offer.model.CampaignStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @EntityGraph(attributePaths = "offer")
    List<Campaign> findByCampaignStatus(CampaignStatus status);
    List<Campaign> findByOfferIdAndCampaignStatusAndIdNot(Long offerId, CampaignStatus status, Long id);
    List<Campaign> findByOfferAdvertiserIdAndCampaignStatus(Long advertiserId, CampaignStatus status);
}