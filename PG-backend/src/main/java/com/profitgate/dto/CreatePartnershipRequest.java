package com.profitgate.dto;

import jakarta.validation.constraints.*;

public record CreatePartnershipRequest(
        @NotNull Integer senderCompanyId,
        @NotNull Integer receiverCompanyId,
        String message
) {}
