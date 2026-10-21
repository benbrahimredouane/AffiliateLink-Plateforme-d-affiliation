package com.affiliatelink.profile;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "profile_publishers")
@Getter
@Setter
@NoArgsConstructor
class PublisherProfile  extends Profile {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Niche niche;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String country;


}
