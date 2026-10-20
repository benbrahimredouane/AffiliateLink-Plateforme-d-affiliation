package com.affiliatelink.profile.model;

import java.time.LocalDateTime;

abstract class Profile {
    private Long id;
   private String email;
    private Status status;
   private LocalDateTime ceatedAt;

    public Profile(Long id, String email, Status status, LocalDateTime ceatedAt) {
        this.id = id;
        this.email = email;
        this.status = status;
        this.ceatedAt = ceatedAt;
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

    public LocalDateTime getCeatedAt() {
        return ceatedAt;
    }

    public void setCeatedAt(LocalDateTime ceatedAt) {
        this.ceatedAt = ceatedAt;
    }
}
