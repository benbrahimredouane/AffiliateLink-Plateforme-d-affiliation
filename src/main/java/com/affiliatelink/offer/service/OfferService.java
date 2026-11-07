package com.affiliatelink.offer.service;

import com.affiliatelink.common.exception.AdvertiserNotActiveException;
import com.affiliatelink.common.exception.OfferNotFoundException;
import com.affiliatelink.offer.dto.CreateOfferReq;
import com.affiliatelink.offer.dto.CreateOfferRes;
import com.affiliatelink.offer.dto.FindOfferRes;
import com.affiliatelink.offer.mapper.OfferMapper;
import com.affiliatelink.offer.model.Offer;
import com.affiliatelink.offer.model.Tag;
import com.affiliatelink.offer.repository.OfferRepository;
import com.affiliatelink.offer.repository.TagRepository;
import com.affiliatelink.profile.ProfileApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final TagRepository tagRepository;
    private final OfferMapper offerMapper;
    private final ProfileApi profileApi;

    public OfferService(OfferRepository offerRepository, TagRepository tagRepository,
                        OfferMapper offerMapper, ProfileApi profileApi) {
        this.offerRepository = offerRepository;
        this.tagRepository = tagRepository;
        this.offerMapper = offerMapper;
        this.profileApi = profileApi;
    }

    @Transactional
    public CreateOfferRes createOffer(CreateOfferReq req) {

        if (!profileApi.isProfileAdvertiserActive(req.advertiserId())) {
            throw new AdvertiserNotActiveException("Advertiser " + req.advertiserId() + " is not active");
        }

        Offer offer = offerMapper.toEntity(req);

        if (req.tags() != null) {
            for (String rawTag : req.tags()) {
                String name = rawTag.trim().toLowerCase();
                Tag tag = tagRepository.findByName(name)
                        .orElseGet(() -> tagRepository.save(new Tag(name)));
                offer.addTag(tag);
            }
        }

        return offerMapper.toRes(offerRepository.save(offer));
    }

    @Transactional(readOnly = true)
    public FindOfferRes findbyId(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("this offer not exists")
        );
        return offerMapper.toFindOfferRes(offer);
    }
}