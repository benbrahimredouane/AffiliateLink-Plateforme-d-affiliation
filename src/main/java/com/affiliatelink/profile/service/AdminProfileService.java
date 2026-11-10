package com.affiliatelink.profile.service;

import com.affiliatelink.profile.dto.ChangeStatusReq;
import com.affiliatelink.profile.dto.ChangeStatusRes;

public interface AdminProfileService {
    public ChangeStatusRes changeStatus(Long id , ChangeStatusReq req);

}
