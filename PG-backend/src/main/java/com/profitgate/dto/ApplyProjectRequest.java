package com.profitgate.dto;

import jakarta.validation.constraints.*;

public record ApplyProjectRequest(
        @NotNull Integer projectId,
        @NotNull Integer companyId,
        String message
) {}
