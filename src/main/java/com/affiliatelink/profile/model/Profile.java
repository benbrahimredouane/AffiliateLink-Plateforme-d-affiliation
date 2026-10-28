package com.affiliatelink.profile.model;

import com.affiliatelink.common.exception.StatusInvalidException;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "profile_profiles")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
public abstract class Profile {
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

    public void changeStatus(Status newStatus){
        boolean allowed = (this.status == Status.PENDING && newStatus == Status.ACTIVE)
                          || (this.status == Status.ACTIVE && newStatus ==Status.SUSPENDED)
                          || (this.status == Status.SUSPENDED && newStatus == Status.ACTIVE);

        if(!allowed) {
            throw  new StatusInvalidException("cannot change from: "+ this.status + " to : "+newStatus);
        }

        this.status = newStatus;
    }




}
