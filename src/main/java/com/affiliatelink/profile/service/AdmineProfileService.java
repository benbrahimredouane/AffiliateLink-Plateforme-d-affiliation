package com.affiliatelink.profile.service;


import com.affiliatelink.common.ProfileNotFoundException;
import com.affiliatelink.profile.dto.ChangeStatusReq;
import com.affiliatelink.profile.dto.ChangeStatusRes;
import com.affiliatelink.profile.repository.ProfileRepository;
import com.affiliatelink.profile.model.Profile;
import org.springframework.stereotype.Service;


@Service
public class AdmineProfileService {
    private final ProfileRepository profileRepository;
    public AdmineProfileService( ProfileRepository profileRepository){
        this.profileRepository = profileRepository;

    }
    public ChangeStatusRes changeStatus(Long id , ChangeStatusReq req){

        Profile profile = profileRepository.findById(id)
                  .orElseThrow( ()->
                          new ProfileNotFoundException("Profile not found with id "+ id)
                  );

           profile.changeStatus(req.status());

        Profile savedProfile = profileRepository.save(profile);

          return new ChangeStatusRes(
                  savedProfile.getId()
                  ,savedProfile.getEmail()
                  , savedProfile.getStatus()
          );



    }


}
