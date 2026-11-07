package com.affiliatelink.offer.controller;

import com.affiliatelink.offer.dto.CreateCampaignReq;
import com.affiliatelink.offer.dto.CreateCampaignRes;
import com.affiliatelink.offer.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/offres/{id}/campaigns")
    public ResponseEntity<CreateCampaignRes> create(@PathVariable("id") Long offerId,
                                                    @Valid @RequestBody CreateCampaignReq req) {
        CreateCampaignRes res = campaignService.createCampaign(offerId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}