package com.affiliatelink.offer.repository;

import com.affiliatelink.offer.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}