package com.profitgate.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CreateProjectRequest(
        @NotNull Integer companyId,
        @NotBlank @Size(max = 200) String title,
        @NotBlank String description,
        BigDecimal budget
) {}
