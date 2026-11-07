package com.affiliatelink.offer.repository;

import com.affiliatelink.offer.model.Niche;
import com.affiliatelink.offer.model.Offer;
import org.springframework.data.jpa.domain.Specification;

public class OfferSpecifications {

    public static Specification<Offer> hasNiche(Niche niche) {
        return (root, query, cb) -> niche == null
                ? cb.conjunction()
                : cb.equal(root.get("niche"), niche);
    }

    public static Specification<Offer> hasAdvertiser(Long advertiserId) {
        return (root, query, cb) -> advertiserId == null
                ? cb.conjunction()
                : cb.equal(root.get("advertiserId"), advertiserId);
    }

    public static Specification<Offer> hasTag(String tag) {
        return (root, query, cb) -> (tag == null || tag.isBlank())
                ? cb.conjunction()
                : cb.equal(root.join("tags").get("name"), tag.trim().toLowerCase());
    }

    public static Specification<Offer> titleContains(String keyword) {
        return (root, query, cb) -> (keyword == null || keyword.isBlank())
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("title")), "%" + keyword.trim().toLowerCase() + "%");
    }
}