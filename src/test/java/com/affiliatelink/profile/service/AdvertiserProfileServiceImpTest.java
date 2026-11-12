package com.affiliatelink.profile.service;

import com.affiliatelink.common.exception.EmailExistException;
import com.affiliatelink.profile.dto.CreateAdvertiserReq;
import com.affiliatelink.profile.dto.CreateAdvertiserRes;
import com.affiliatelink.profile.model.AdvertiserProfile;
import com.affiliatelink.profile.model.Status;
import com.affiliatelink.profile.repository.AdvertiserProfileRepository;
import com.affiliatelink.profile.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdvertiserProfileServiceImpTest {

    @Mock
    ProfileRepository profileRepository;

    @Mock
    AdvertiserProfileRepository advertiserProfileRepository;

    @InjectMocks
    AdvertiserProfileServiceImp advertiserProfileService;


    @Test
    void shouldCreateAdvertiserWithPendingStatus(){
        //given
        CreateAdvertiserReq req = new CreateAdvertiserReq(
                "allready@gmail.com",
                "Sofrecom",
                "TECH",
                "https://sofre.com");
        when(profileRepository.existsByemail(req.email())).thenReturn(false);
        when(advertiserProfileRepository.save(any(AdvertiserProfile.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when

        CreateAdvertiserRes res = advertiserProfileService.createAdvertiser(req);

        //result asset then

        assertThat(res).isNotNull();
        assertThat(res.email()).isEqualTo(req.email());
        assertThat(res.companyName()).isEqualTo(req.companyName());
        assertThat(res.sector()).isEqualTo(req.sector());
        assertThat(res.website()).isEqualTo(req.website());
        assertThat(res.status()).isEqualTo(Status.PENDING);

        verify(profileRepository).existsByemail(req.email());
        verify(advertiserProfileRepository).save(any(AdvertiserProfile.class));



    }
    @Test
    void shouldRejectAdvertiserProfileCreationWhenEmailAlreadyExists(){
        //Given

        CreateAdvertiserReq req = new CreateAdvertiserReq(
                "advertiser@gmail.com",
                "company",
                "TECH",
                "https://technologines.com");
        when(profileRepository.existsByemail(req.email())).thenReturn(true);

        //act + assert when + then
        assertThatThrownBy(()->
                advertiserProfileService.createAdvertiser(req))
        .isInstanceOf(EmailExistException.class)
                .hasMessage("email already in use");

        verify(profileRepository).existsByemail(req.email());
        verify(advertiserProfileRepository , never()).save(any(AdvertiserProfile.class));


    }
}