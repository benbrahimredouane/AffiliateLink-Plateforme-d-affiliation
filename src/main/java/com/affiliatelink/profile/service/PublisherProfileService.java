package com.affiliatelink.profile.service;

import com.affiliatelink.profile.dto.CreatePublisherReq;
import com.affiliatelink.profile.dto.CreatePublisherRes;
import com.affiliatelink.profile.model.PublisherProfile;

import java.util.List;

public interface PublisherProfileService {
    public CreatePublisherRes createPublisher(CreatePublisherReq req);
    public List<PublisherProfile> findpublishers();
    public  PublisherProfile findpublisherbyid(Long id);
}
