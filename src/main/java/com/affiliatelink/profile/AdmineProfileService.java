package com.affiliatelink.profile;


import com.affiliatelink.common.exception.ProfileNotFoundException;
import org.springframework.stereotype.Service;


@Service
class AdmineProfileService {
    private final ProfileRepository profileRepository;
    AdmineProfileService( ProfileRepository profileRepository){
        this.profileRepository = profileRepository;

    }
    ChangeStatusRes changeStatus(Long id , ChangeStatusReq req){

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
