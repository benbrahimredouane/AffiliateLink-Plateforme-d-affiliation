package com.affiliatelink.offer.service;

import com.affiliatelink.common.exception.AdvertiserNotActiveException;
import com.affiliatelink.common.exception.OfferNotFoundException;
import com.affiliatelink.offer.dto.CreateCampaignReq;
import com.affiliatelink.offer.dto.CreateCampaignRes;
import com.affiliatelink.offer.mapper.CampaignMapper;
import com.affiliatelink.offer.model.Campaign;
import com.affiliatelink.offer.model.Offer;
import com.affiliatelink.offer.repository.CampaignRepository;
import com.affiliatelink.offer.repository.OfferRepository;
import com.affiliatelink.profile.ProfileApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final OfferRepository offerRepository;
    private final CampaignMapper campaignMapper;
    private final ProfileApi profileApi;

    public CampaignService(CampaignRepository campaignRepository, OfferRepository offerRepository,
                           CampaignMapper campaignMapper, ProfileApi profileApi) {
        this.campaignRepository = campaignRepository;
        this.offerRepository = offerRepository;
        this.campaignMapper = campaignMapper;
        this.profileApi = profileApi;
    }

    @Transactional
    public CreateCampaignRes createCampaign(Long offerId, CreateCampaignReq req) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("this offer not exists"));

        if (!profileApi.isProfileAdvertiserActive(offer.getAdvertiserId())) {
            throw new AdvertiserNotActiveException("Advertiser " + offer.getAdvertiserId() + " is not active");
        }

        Campaign campaign = campaignMapper.toEntity(req);
        offer.addCampaign(campaign);

        return campaignMapper.toRes(campaignRepository.save(campaign));
    }
}
