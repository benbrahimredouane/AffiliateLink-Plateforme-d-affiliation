package com.affiliatelink.profile;


import jakarta.validation.constraints.NotNull;

record ChangeStatusReq(
        @NotNull
        Status status

) { }
