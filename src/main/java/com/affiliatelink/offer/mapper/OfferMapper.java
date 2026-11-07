package com.affiliatelink.offer.mapper;

import com.affiliatelink.offer.dto.CreateOfferReq;
import com.affiliatelink.offer.dto.CreateOfferRes;
import com.affiliatelink.offer.dto.FindOfferRes;
import com.affiliatelink.offer.model.Offer;
import com.affiliatelink.offer.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    @Mapping(target = "tags",ignore = true)
    @Mapping(target = "campaigns",ignore = true)

    Offer toEntity(CreateOfferReq req);
    CreateOfferRes toRes(Offer offer);

    default String map(Tag tag){
        return tag.getName();

    }
    FindOfferRes toFindOfferRes(Offer offer);


}
