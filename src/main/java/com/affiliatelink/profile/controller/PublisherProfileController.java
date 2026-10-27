package com.affiliatelink.profile.controller;

import com.affiliatelink.profile.dto.CreatePublisherReq;
import com.affiliatelink.profile.dto.CreatePublisherRes;
import com.affiliatelink.profile.model.PublisherProfile;
import com.affiliatelink.profile.service.PublisherProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class PublisherProfileController {
    private final PublisherProfileService publisherProfileService;

    PublisherProfileController( PublisherProfileService publisherProfileService){
        this.publisherProfileService = publisherProfileService;

    }
    @PostMapping("/publishers")
    ResponseEntity<CreatePublisherRes> createPublisher(
            @Valid @RequestBody CreatePublisherReq req
    ){
        CreatePublisherRes res = publisherProfileService.createPublisher(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/publishers")
    ResponseEntity<List<PublisherProfile>> findPublishers(){
        List<PublisherProfile> res = publisherProfileService.findpublishers();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/publishers/{id}")
    ResponseEntity<?> findPublisher(@PathVariable("id") Long id){
        PublisherProfile publisherProfile = publisherProfileService.findpublisherbyid(id);

        return ResponseEntity.status(HttpStatus.OK).body(publisherProfile);


    }

}
