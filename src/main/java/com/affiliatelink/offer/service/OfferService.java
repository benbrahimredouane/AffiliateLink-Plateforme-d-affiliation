package com.affiliatelink.offer.service;

import com.affiliatelink.common.exception.AdvertiserNotActiveException;
import com.affiliatelink.common.exception.OfferNotFoundException;
import com.affiliatelink.offer.dto.*;
import com.affiliatelink.offer.mapper.OfferMapper;
import com.affiliatelink.offer.model.Niche;
import com.affiliatelink.offer.model.Offer;
import com.affiliatelink.offer.model.Tag;
import com.affiliatelink.offer.repository.OfferRepository;
import com.affiliatelink.offer.repository.OfferSpecifications;
import com.affiliatelink.offer.repository.TagRepository;
import com.affiliatelink.profile.ProfileApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final ProfileApi profileApi;
    private final TagService tagService;

    public OfferService(OfferRepository offerRepository, OfferMapper offerMapper,
                        ProfileApi profileApi, TagService tagService) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;
        this.profileApi = profileApi;
        this.tagService = tagService;
    }

    @Transactional
    public CreateOfferRes createOffer(CreateOfferReq req) {
        if (!profileApi.isProfileAdvertiserActive(req.advertiserId())) {
            throw new AdvertiserNotActiveException("Advertiser " + req.advertiserId() + " is not active");
        }

        Offer offer = offerMapper.toEntity(req);

        if (req.tags() != null) {
            offer.replaceTags(tagService.resolve(req.tags()));
        }

        return offerMapper.toRes(offerRepository.save(offer));
    }

    @Transactional(readOnly = true)
    public FindOfferRes findbyId(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("this offer not exists"));
        return offerMapper.toFindOfferRes(offer);
    }

    @Transactional
    public FindOfferRes update(Long id, UpdateOfferReq req) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("this offer not exists"));

        offer.update(req.title(), req.description(), req.landingUrl(), req.niche());
        offer.replaceTags(tagService.resolve(req.tags()));

        return offerMapper.toFindOfferRes(offer);   // mapped inside the transaction, lazy data is still readable
    }
    @Transactional(readOnly = true)
    public Page<SearchOfferRes> search(Niche niche, Long advertiserId, String tag,
                                       String keyword, Pageable pageable) {

        Specification<Offer> spec = Specification.where(OfferSpecifications.hasNiche(niche))
                .and(OfferSpecifications.hasAdvertiser(advertiserId))
                .and(OfferSpecifications.hasTag(tag))
                .and(OfferSpecifications.titleContains(keyword));

        return offerRepository.findAll(spec, pageable).map(offerMapper::toSearchOfferRes);
    }

}