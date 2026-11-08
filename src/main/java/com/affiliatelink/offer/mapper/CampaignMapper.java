package com.affiliatelink.offer.mapper;

import com.affiliatelink.offer.dto.CreateCampaignReq;
import com.affiliatelink.offer.dto.CreateCampaignRes;
import com.affiliatelink.offer.model.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    Campaign toEntity(CreateCampaignReq req);

    @Mapping(source = "offer.id", target = "offerId")
    @Mapping(source = "campaignStatus", target = "status")
    CreateCampaignRes toRes(Campaign campaign);
}