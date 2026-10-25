package com.affiliatelink.profile;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class AdvertiserProfileService {

      AdvertiserProfileRepository advertiserProfileRepository;
      ProfileRepository profileRepository;
    AdvertiserProfileService( AdvertiserProfileRepository advertiserProfileRepository , ProfileRepository profileRepository){
        this.advertiserProfileRepository = advertiserProfileRepository;
        this.profileRepository = profileRepository;

    }
    CreateAdvertiserRes createAdvertiser(CreateAdvertiserReq req){
        if(profileRepository.existsByemail(req.email())){
            throw new IllegalArgumentException("email allready in use");
        }
        AdvertiserProfile advertiserProfile = new AdvertiserProfile();

        advertiserProfile.setEmail(req.email());
        advertiserProfile.setCompanyName(req.companyName());
        advertiserProfile.setSector(req.sector());
        advertiserProfile.setWebsite(req.website());

        AdvertiserProfile savedAdvertiser = advertiserProfileRepository.save(advertiserProfile);

        return new CreateAdvertiserRes(
                savedAdvertiser.getId()
                , savedAdvertiser.getEmail()
                ,savedAdvertiser.getStatus()
                ,savedAdvertiser.getCreatedAt()
                , savedAdvertiser.getCompanyName()
                ,savedAdvertiser.getSector()
                , savedAdvertiser.getWebsite());
    }

    List<AdvertiserProfile> findalladvertisers(){
        List<AdvertiserProfile> advertiserProfiles = advertiserProfileRepository.findAll();
        return advertiserProfiles;
    }

    Optional<AdvertiserProfile> findAdvertiserById(Long id){
        Optional<AdvertiserProfile> optionalAdvertiserProfile = advertiserProfileRepository.findById(id);
        return optionalAdvertiserProfile;
    }


}
