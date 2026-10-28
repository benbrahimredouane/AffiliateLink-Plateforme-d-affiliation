package com.affiliatelink.profile.service;


import com.affiliatelink.common.exception.ProfileNotFoundException;
import com.affiliatelink.profile.ProfileSuspended;
import com.affiliatelink.profile.dto.ChangeStatusReq;
import com.affiliatelink.profile.dto.ChangeStatusRes;
import com.affiliatelink.profile.model.Status;
import com.affiliatelink.profile.repository.ProfileRepository;
import com.affiliatelink.profile.model.Profile;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AdminProfileService {
    private final ProfileRepository profileRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    public AdminProfileService(ProfileRepository profileRepository, ApplicationEventPublisher applicationEventPublisher){
        this.profileRepository = profileRepository;
        this.applicationEventPublisher = applicationEventPublisher;


    }
    @Transactional
    public ChangeStatusRes changeStatus(Long id , ChangeStatusReq req){

        Profile profile = profileRepository.findById(id)
                  .orElseThrow( ()->
                          new ProfileNotFoundException("Profile not found with id "+ id)
                  );

           profile.changeStatus(req.status());

        Profile savedProfile = profileRepository.save(profile);

        if(savedProfile.getStatus() == Status.SUSPENDED){
            applicationEventPublisher.publishEvent(new ProfileSuspended(savedProfile.getId()));

        }

          return new ChangeStatusRes(
                  savedProfile.getId()
                  ,savedProfile.getEmail()
                  , savedProfile.getStatus()
          );



    }


}
