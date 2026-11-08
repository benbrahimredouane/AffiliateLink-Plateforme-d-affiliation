package com.affiliatelink.offer.listener;

import com.affiliatelink.offer.model.Campaign;
import com.affiliatelink.offer.model.CampaignStatus;
import com.affiliatelink.offer.repository.CampaignRepository;
import com.affiliatelink.profile.ProfileSuspended;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileSuspendedListener {

    private final CampaignRepository campaignRepository;

    public ProfileSuspendedListener(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @ApplicationModuleListener
    public void on(ProfileSuspended event) {
        List<Campaign> activeCampaigns = campaignRepository
                .findByOfferAdvertiserIdAndCampaignStatus(event.profileId(), CampaignStatus.ACTIVE);

        for (Campaign campaign : activeCampaigns) {
            campaign.changeStatus(CampaignStatus.PAUSED);
        }
    }
}