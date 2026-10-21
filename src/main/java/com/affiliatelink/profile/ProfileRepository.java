package com.affiliatelink.profile;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProfileRepository extends JpaRepository<Profile, Long> {
     boolean existsByemail(String email);
}
