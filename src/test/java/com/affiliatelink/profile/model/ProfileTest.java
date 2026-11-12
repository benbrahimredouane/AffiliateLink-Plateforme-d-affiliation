package com.affiliatelink.profile.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @ParameterizedTest
    @CsvSource({
            "PENDING,ACTIVE",
            "ACTIVE,SUSPENDED",
            "SUSPENDED,ACTIVE"
    })
    void shouldChangeStatusOnlyAllowed( Status current , Status newStatus) {
        //arrange
        TestProfile profile = createProfile(current);

        //act
        profile.changeStatus(newStatus);

        //assert

        assertThat(profile.getStatus()).isEqualTo(newStatus);
        assertEquals(newStatus , profile.getStatus());


    }
    private TestProfile createProfile(Status st){
        TestProfile profile = new TestProfile();
        if(st == Status.ACTIVE){
            profile.changeStatus(Status.ACTIVE);

        }
        if(st == Status.SUSPENDED){
            profile.changeStatus(Status.ACTIVE);
            profile.changeStatus(Status.SUSPENDED);
        }
        return profile;
    }
    private static class TestProfile extends Profile{}
}