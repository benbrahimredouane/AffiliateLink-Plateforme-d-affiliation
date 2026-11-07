package com.affiliatelink.offer.controller;

import com.affiliatelink.offer.dto.CreateOfferReq;
import com.affiliatelink.offer.dto.CreateOfferRes;
import com.affiliatelink.offer.dto.FindOfferRes;
import com.affiliatelink.offer.model.Offer;
import com.affiliatelink.offer.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OfferController {
    private final OfferService offerService;
    public OfferController(OfferService offerService){
        this.offerService = offerService;
    }
    @GetMapping("/offer/test")
    public String test(){
        return "test controller working";
    }

    @PostMapping("/offers")
    public ResponseEntity<CreateOfferRes> create(@Valid @RequestBody CreateOfferReq req){
        CreateOfferRes res = offerService.createOffer(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("offers/{id}")
        public ResponseEntity<FindOfferRes> findById(@PathVariable("id") Long offerId){

        FindOfferRes offer = offerService.findbyId(offerId);

        return ResponseEntity.status(HttpStatus.OK).body(offer);
    }
}
