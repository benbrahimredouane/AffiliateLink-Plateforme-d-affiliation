package com.affiliatelink.profile.repository;



import com.affiliatelink.profile.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
     boolean existsByemail(String email);
}
