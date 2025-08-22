package com.profitgate.dto;

import jakarta.validation.constraints.*;

public record SendMessageRequest(
        @NotNull Integer senderUserId,
        @NotNull Integer receiverUserId,
        @NotBlank String message
) {}
