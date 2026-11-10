package com.affiliatelink.profile.service;


import com.affiliatelink.profile.ProfileSuspended;
import com.affiliatelink.profile.dto.ChangeStatusReq;
import com.affiliatelink.profile.dto.ChangeStatusRes;
import com.affiliatelink.profile.model.Profile;
import com.affiliatelink.profile.model.Status;
import com.affiliatelink.profile.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AdminProfileServiceTest {

    @Mock
    ProfileRepository profileRepository;

    @Mock
    ApplicationEventPublisher applicationEventPublisher;


    @InjectMocks
    AdminProfileServiceImp adminProfileService;

    @Test
    void shouldChangeProfileStatusWithSuccess(){
        //arrange
        Long id = 1L;
        TestProfile profile = new TestProfile(id,"test@gmail.com");

        ChangeStatusReq req = new ChangeStatusReq(Status.ACTIVE);

        when(profileRepository.findById(id)).thenReturn(Optional.of(profile));

        when(profileRepository.save(profile)).thenReturn(profile);

        //act

        ChangeStatusRes result = adminProfileService.changeStatus(id,req);

        //assert
        assertThat(profile.getStatus()).isEqualTo(Status.ACTIVE);

        assertThat(result).isNotNull();

        verify(profileRepository).findById(id);

        verify(profileRepository).save(profile);

        verify(applicationEventPublisher,never()).publishEvent(any(ProfileSuspended.class));
    }

    @Test
    void ShouldSuspendActiveProfileAndPublishEvent(){
        //arrange
        Long id = 2L;

        TestProfile profile = createActiveProfile(id,"test1@gmail.com");

        ChangeStatusReq req = new ChangeStatusReq(Status.SUSPENDED);

        when(profileRepository.findById(id)).thenReturn(Optional.of(profile));

        when(profileRepository.save(profile)).thenReturn(profile);

        //act

        ChangeStatusRes res = adminProfileService.changeStatus(id,req);

        //assert

        assertThat(profile.getStatus()).isEqualTo(Status.SUSPENDED);

        assertThat(res).isNotNull();

        verify(profileRepository).save(profile);

        verify(applicationEventPublisher, times(1)).publishEvent(any(ProfileSuspended.class));

    }

    @Test
    void shouldReactivateSuspendedProfileWithoutPublishingEvent() {

        //  arrange
        Long id = 3L;

        TestProfile profile = createSuspendedProfile(id, "Profile@example.com");

        ChangeStatusReq req = new ChangeStatusReq(Status.ACTIVE);

        when(profileRepository.findById(id)).thenReturn(Optional.of(profile));

        when(profileRepository.save(profile)).thenReturn(profile);

        // act
        ChangeStatusRes result = adminProfileService.changeStatus(id, req);

        // assert
        assertThat(profile.getStatus()).isEqualTo(Status.ACTIVE);

        assertThat(result).isNotNull();

        verify(profileRepository).save(profile);

        verify(applicationEventPublisher, never()).publishEvent(any(ProfileSuspended.class));
    }

    private static class TestProfile extends Profile {

        TestProfile(Long id, String email) {
            setId(id);
            setEmail(email);
        }
    }


    private TestProfile createActiveProfile(Long id, String email) {

        TestProfile profile = new TestProfile(id, email);

        profile.changeStatus(Status.ACTIVE);

        return profile;
    }

    private TestProfile createSuspendedProfile(Long id, String email) {

        TestProfile profile = new TestProfile(id, email);

        profile.changeStatus(Status.ACTIVE);
        profile.changeStatus(Status.SUSPENDED);

        return profile;
    }


}