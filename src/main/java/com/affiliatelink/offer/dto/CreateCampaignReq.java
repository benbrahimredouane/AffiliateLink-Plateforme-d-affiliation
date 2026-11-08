package com.affiliatelink.offer.dto;

import com.affiliatelink.offer.model.PayoutModel;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateCampaignReq(
        @NotBlank @Size(max = 255) String name,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal budget,
        @NotNull PayoutModel payoutModel,
        @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal payoutValue
) {

    @AssertTrue(message = "endDate must be after startDate")
    public boolean isEndDateAfterStartDate() {
        return startDate == null || endDate == null || endDate.isAfter(startDate);
    }

    @AssertTrue(message = "for CPA_PERCENT, payoutValue must be between 0 and 100")
    public boolean isPayoutValueValid() {
        if (payoutModel != PayoutModel.CPA_PERCENT || payoutValue == null) {
            return true;
        }
        return payoutValue.compareTo(BigDecimal.valueOf(100)) <= 0;
    }
}
