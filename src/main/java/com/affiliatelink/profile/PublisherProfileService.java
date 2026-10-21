package com.affiliatelink.profile;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

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
              throw new IllegalArgumentException("Email allready exist !");
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

}
