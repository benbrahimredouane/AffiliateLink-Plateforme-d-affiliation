package com.affiliatelink.profile.repository;

import com.affiliatelink.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmineProfileRepository extends JpaRepository<Profile, Long>{}