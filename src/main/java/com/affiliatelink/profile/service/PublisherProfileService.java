package com.affiliatelink.profile.service;

import com.affiliatelink.common.EmailExistException;
import com.affiliatelink.common.ProfileNotFoundException;
import com.affiliatelink.profile.dto.CreatePublisherReq;
import com.affiliatelink.profile.dto.CreatePublisherRes;
import com.affiliatelink.profile.repository.ProfileRepository;
import com.affiliatelink.profile.repository.PublisherProfileRepository;
import com.affiliatelink.profile.model.PublisherProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherProfileService {
      private final PublisherProfileRepository publisherProfileRepository;
      private final ProfileRepository profileRepository;

      PublisherProfileService(
              PublisherProfileRepository publisherProfileRepository ,
              ProfileRepository profileRepository

      ){
          this.publisherProfileRepository = publisherProfileRepository;
          this.profileRepository = profileRepository;
      }

      public CreatePublisherRes createPublisher(CreatePublisherReq req){
          if(profileRepository.existsByemail(req.email())){
              throw new EmailExistException("Email already exist !");
          }

          PublisherProfile publisherProfile = new PublisherProfile();

          publisherProfile.setFirstName(req.firstName());
          publisherProfile.setLastName(req.lastName());
          publisherProfile.setEmail(req.email());
          publisherProfile.setBio(req.bio());
          publisherProfile.setCountry(req.country());
          publisherProfile.setNiche(req.niche());

          PublisherProfile savedpublisher = publisherProfileRepository.save(publisherProfile);

          return new CreatePublisherRes(
                  savedpublisher.getId()
                  , savedpublisher.getEmail()
                  , savedpublisher.getStatus()
                  ,savedpublisher.getCreatedAt()
                  ,savedpublisher.getFirstName()
                  , savedpublisher.getLastName()
                  , savedpublisher.getNiche()
                  , savedpublisher.getBio()
                  , savedpublisher.getCountry());


      }
      public List<PublisherProfile> findpublishers(){
          List<PublisherProfile> resList = publisherProfileRepository.findAll();
          return resList;
      }

     public  PublisherProfile findpublisherbyid(Long id){
      PublisherProfile publisherProfile = publisherProfileRepository.findById(id).orElseThrow( ()->
              new ProfileNotFoundException(
                      "profile with id : "+ id +" not found"
      ));
          return publisherProfile;
      }

}
