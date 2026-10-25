package com.affiliatelink.profile;

import jakarta.persistence.*;

record ChangeStatusRes(
        Long id,
        String email,
        Status status

) { }
