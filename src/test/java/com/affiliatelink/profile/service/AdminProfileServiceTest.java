package com.affiliatelink.profile.service;


import com.affiliatelink.common.exception.ProfileNotFoundException;
import com.affiliatelink.common.exception.StatusInvalidException;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
    void shouldCreateProfileWithPendingStatus(){
        //arrange
        Long id = 6L;
        TestProfile profile = new TestProfile(id,"status@test.com");


        //act assert
        assertThat(profile.getStatus()).isEqualTo(Status.PENDING);


    }

    @Test
    void shouldThrowExceptionWhenProfileDoesNotExist(){
        //arrange

        Long id = 99L;

        ChangeStatusReq req = new ChangeStatusReq(Status.ACTIVE);

        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        //act & assert

        assertThatThrownBy(()-> adminProfileService.changeStatus(id,req)).isInstanceOf(ProfileNotFoundException.class).hasMessage("Profile not found with id 99");

        verify(profileRepository).findById(id);

        verify(profileRepository , never()).save(any(Profile.class));

        verify(applicationEventPublisher,never()).publishEvent(any(ProfileSuspended.class));

    }

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

    @Test
    void shouldRejectInvalidStatusTransition() {

        // arrange
        Long id = 4L;

        TestProfile profile = new TestProfile(id, "newuser@example.com");

        ChangeStatusReq req = new ChangeStatusReq(Status.SUSPENDED);

        when(profileRepository.findById(id)).thenReturn(Optional.of(profile));

        // act & assert
        assertThatThrownBy(() ->
                adminProfileService.changeStatus(id, req)
        ).isInstanceOf(StatusInvalidException.class);

        verify(profileRepository).findById(id);

        verify(profileRepository, never()).save(any(Profile.class));

        verify(applicationEventPublisher, never()).publishEvent(any(ProfileSuspended.class));
    }

    //profile de test
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