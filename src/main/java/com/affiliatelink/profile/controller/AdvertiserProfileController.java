package com.affiliatelink.profile.controller;

import com.affiliatelink.profile.model.AdvertiserProfile;
import com.affiliatelink.profile.service.AdvertiserProfileService;
import com.affiliatelink.profile.dto.CreateAdvertiserReq;
import com.affiliatelink.profile.dto.CreateAdvertiserRes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class AdvertiserProfileController {
    private AdvertiserProfileService advertiserProfileService;
    AdvertiserProfileController(AdvertiserProfileService advertiserProfileService){
        this.advertiserProfileService = advertiserProfileService;
    }
    @GetMapping("/oui")
    String oui(){
        return "oui";
    }

    @PostMapping("/advertisers")
    CreateAdvertiserRes createAdvertiser(@Valid @RequestBody CreateAdvertiserReq req){
         CreateAdvertiserRes createAdvertiserRes = advertiserProfileService.createAdvertiser(req);
         return createAdvertiserRes;
    }

    @GetMapping("/advertisers")
    public ResponseEntity<List<AdvertiserProfile>> findalladvertisers(){
        List<AdvertiserProfile> adList = advertiserProfileService.findalladvertisers();
        return ResponseEntity.status(HttpStatus.OK).body(adList);
    }

    @GetMapping("/advertisers/{id}")
    AdvertiserProfile findAdById(@PathVariable("id") Long id){
       AdvertiserProfile advertiserProfile = advertiserProfileService.findAdvertiserById(id);

            return advertiserProfile;

    }
}
