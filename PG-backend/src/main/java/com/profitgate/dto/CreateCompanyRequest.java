package com.profitgate.dto;

import jakarta.validation.constraints.*;

public record CreateCompanyRequest(
        @NotBlank @Size(max = 150) String name,
        @NotNull Integer industryId,
        @Size(max = 150) String location,
        String description,
        @NotNull Integer ownerUserId
) {}
