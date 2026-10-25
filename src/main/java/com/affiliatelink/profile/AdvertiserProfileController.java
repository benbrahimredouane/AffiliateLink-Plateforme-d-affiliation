package com.affiliatelink.profile;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    ResponseEntity<List<AdvertiserProfile>> findalladvertisers(){
        List<AdvertiserProfile> adList = advertiserProfileService.findalladvertisers();
        return ResponseEntity.status(HttpStatus.OK).body(adList);
    }

    @GetMapping("/advertisers/{id}")
    ResponseEntity<?> findAdById(@PathVariable("id") Long id){
        Optional<AdvertiserProfile> advertiserProfile = advertiserProfileService.findAdvertiserById(id);
        if(advertiserProfile.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(advertiserProfile.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }
}
