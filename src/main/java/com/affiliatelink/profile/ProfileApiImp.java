package com.affiliatelink.profile;

import com.affiliatelink.profile.service.AdvertiserProfileService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProfileApiImp implements ProfileApi {
    private final AdvertiserProfileService advertiserProfileService;
    public ProfileApiImp(AdvertiserProfileService advertiserProfileService){
        this.advertiserProfileService = advertiserProfileService;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isProfileAdvertiserActive(Long AdvertiserId){
        return advertiserProfileService.isExisitsAndActive(AdvertiserId);
    }
}
