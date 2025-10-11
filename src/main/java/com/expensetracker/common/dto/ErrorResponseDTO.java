package com.expensetracker.common.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO (
        String errorMessage,
        String detailErrorMessage,
        LocalDateTime errorTime
){
}
