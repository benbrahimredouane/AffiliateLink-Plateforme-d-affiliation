package com.affiliatelink.profile;

import com.affiliatelink.common.exception.EmailExistException;
import com.affiliatelink.common.exception.ProfileNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
class PublisherProfileService {
      private final PublisherProfileRepository publisherProfileRepository;
      private final ProfileRepository profileRepository;

      PublisherProfileService(
              PublisherProfileRepository publisherProfileRepository ,
              ProfileRepository profileRepository

      ){
          this.publisherProfileRepository = publisherProfileRepository;
          this.profileRepository = profileRepository;
      }

      CreatePublisherRes createPublisher(CreatePublisherReq req){
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
      List<PublisherProfile> findpublishers(){
          List<PublisherProfile> resList = publisherProfileRepository.findAll();
          return resList;
      }

      PublisherProfile findpublisherbyid(Long id){
      PublisherProfile publisherProfile = publisherProfileRepository.findById(id).orElseThrow( ()->
              new ProfileNotFoundException(
                      "profile with id : "+ id +" not found"
      ));
          return publisherProfile;
      }

}
