package com.affiliatelink.profile;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "profile_profiles")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( unique = true , nullable = false)
    private String email;

    @Column( nullable = false )
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Column( nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        if(createdAt == null){
            createdAt = OffsetDateTime.now();
        }
    }



}
