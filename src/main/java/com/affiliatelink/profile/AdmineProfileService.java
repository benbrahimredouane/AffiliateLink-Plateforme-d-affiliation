package com.affiliatelink.profile;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
 class AdmineProfileService {
    private final ProfileRepository profileRepository;
    AdmineProfileService( ProfileRepository profileRepository){
        this.profileRepository = profileRepository;

    }
    ChangeStatusRes changestatus(Long id , ChangeStatusReq req){
       Optional<Profile> Optionalprofile = profileRepository.findById(id);


           Profile profile  = Optionalprofile.get();
           profile.setStatus(req.status());

           Profile savedProfile = profileRepository.save(profile);

          return new ChangeStatusRes(savedProfile.getId() ,savedProfile.getEmail() , savedProfile.getStatus());



    }


}
