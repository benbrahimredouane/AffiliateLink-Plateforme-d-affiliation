package com.affiliatelink.profile;

import com.affiliatelink.profile.service.AdvertiserProfileServiceImp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProfileApiImp implements ProfileApi {
    private final AdvertiserProfileServiceImp advertiserProfileService;
    public ProfileApiImp(AdvertiserProfileServiceImp advertiserProfileService){
        this.advertiserProfileService = advertiserProfileService;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isProfileAdvertiserActive(Long AdvertiserId){
        return advertiserProfileService.isExisitsAndActive(AdvertiserId);
    }
}
