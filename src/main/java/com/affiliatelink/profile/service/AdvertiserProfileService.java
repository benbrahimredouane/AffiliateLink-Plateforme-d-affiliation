package com.affiliatelink.profile.service;

import com.affiliatelink.profile.dto.CreateAdvertiserReq;
import com.affiliatelink.profile.dto.CreateAdvertiserRes;
import com.affiliatelink.profile.model.AdvertiserProfile;

import java.util.List;

public interface AdvertiserProfileService {
    public CreateAdvertiserRes createAdvertiser(CreateAdvertiserReq req);
    public List<AdvertiserProfile> findalladvertisers();
    public AdvertiserProfile findAdvertiserById(Long id);
    public boolean isExisitsAndActive(Long id);
}
