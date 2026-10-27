package com.affiliatelink.profile.service;


import com.affiliatelink.common.EmailExistException;
import com.affiliatelink.common.ProfileNotFoundException;
import com.affiliatelink.profile.repository.AdvertiserProfileRepository;
import com.affiliatelink.profile.dto.CreateAdvertiserReq;
import com.affiliatelink.profile.dto.CreateAdvertiserRes;
import com.affiliatelink.profile.repository.ProfileRepository;
import com.affiliatelink.profile.model.AdvertiserProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertiserProfileService {

      AdvertiserProfileRepository advertiserProfileRepository;
      ProfileRepository profileRepository;
    AdvertiserProfileService( AdvertiserProfileRepository advertiserProfileRepository , ProfileRepository profileRepository){
        this.advertiserProfileRepository = advertiserProfileRepository;
        this.profileRepository = profileRepository;

    }
    public CreateAdvertiserRes createAdvertiser(CreateAdvertiserReq req){
        if(profileRepository.existsByemail(req.email())){
            throw new EmailExistException("email already in use");
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

    public List<AdvertiserProfile> findalladvertisers(){
        List<AdvertiserProfile> advertiserProfiles = advertiserProfileRepository.findAll();
        return advertiserProfiles;
    }

    public AdvertiserProfile findAdvertiserById(Long id){
        AdvertiserProfile optionalAdvertiserProfile = advertiserProfileRepository.findById(id).orElseThrow(()->
                new ProfileNotFoundException("profile with id: "+id+" not exists"));
        return optionalAdvertiserProfile;
    }


}
