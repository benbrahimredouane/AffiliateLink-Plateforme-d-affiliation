package com.affiliatelink.profile.service;

import com.affiliatelink.common.exception.EmailExistException;
import com.affiliatelink.profile.dto.CreateAdvertiserReq;
import com.affiliatelink.profile.dto.CreateAdvertiserRes;
import com.affiliatelink.profile.dto.CreatePublisherReq;
import com.affiliatelink.profile.dto.CreatePublisherRes;
import com.affiliatelink.profile.model.AdvertiserProfile;
import com.affiliatelink.profile.model.Niche;
import com.affiliatelink.profile.model.PublisherProfile;
import com.affiliatelink.profile.model.Status;
import com.affiliatelink.profile.repository.ProfileRepository;
import com.affiliatelink.profile.repository.PublisherProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublisherProfileServiceImpTest {
    @Mock
    private PublisherProfileRepository publisherProfileRepository;
    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private PublisherProfileServiceImp publisherProfileService;

    @Test
    void shouldCreatePublisherWithPendingStatus(){
        //given
        CreatePublisherReq req = new CreatePublisherReq(
                "publisher@test.com",
                "pub","bendaoud",
                Niche.BEAUTY,"some bio",
                "morocco");

        when(profileRepository.existsByemail(req.email())).thenReturn(false);
        when(publisherProfileRepository.save(any(PublisherProfile.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //when

        CreatePublisherRes res = publisherProfileService.createPublisher(req);

        //result asset then

        assertThat(res).isNotNull();
        assertThat(res.email()).isEqualTo(req.email());
        assertThat(res.bio()).isEqualTo(req.bio());
        assertThat(res.country()).isEqualTo(req.country());
        assertThat(res.firstName()).isEqualTo(req.firstName());
        assertThat(res.lastName()).isEqualTo(req.lastName());
        assertThat(res.niche()).isEqualTo(req.niche());
        assertThat(res.status()).isEqualTo(Status.PENDING);

        verify(profileRepository).existsByemail(req.email());
        verify(publisherProfileRepository).save(any(PublisherProfile.class));



    }
    @Test
    void shouldRejectAdvertiserProfileCreationWhenEmailAlreadyExists(){
        //Given

        CreatePublisherReq req = new CreatePublisherReq(
                "publisher@test.com",
                "pub","bendaoud",
                Niche.BEAUTY,"some bio",
                "morocco");
        when(profileRepository.existsByemail(req.email())).thenReturn(true);

        //act + assert when + then
        assertThatThrownBy(()->
                publisherProfileService.createPublisher(req))
                .isInstanceOf(EmailExistException.class)
                .hasMessage("email already in use");

        verify(profileRepository).existsByemail(req.email());
        verify(publisherProfileRepository , never()).save(any(PublisherProfile.class));


    }
}
