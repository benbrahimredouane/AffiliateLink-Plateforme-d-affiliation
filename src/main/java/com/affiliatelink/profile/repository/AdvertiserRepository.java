package com.affiliatelink.profile.repository;

import com.affiliatelink.profile.model.AdvertiserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiserRepository extends JpaRepository<AdvertiserProfile, Long> {
}
