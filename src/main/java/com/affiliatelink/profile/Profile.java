package com.affiliatelink.profile;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "profile_profiles")
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email" , unique = true , nullable = false)
    private String email;

    @Column(name = "status" , nullable = false )
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at" , nullable = false)
    private OffsetDateTime createAt;

    public Profile(Long id, String email, OffsetDateTime createAt) {
        this.id = id;
        this.email = email;
        this.status = Status.PENDING;
        this.createAt = createAt;
    }

    public Profile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createAt;
    }

    public void setCreatedAt(OffsetDateTime createAt) {
        this.createAt = createAt;
    }
}
