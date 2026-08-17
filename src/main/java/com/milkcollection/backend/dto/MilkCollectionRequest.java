package com.milkcollection.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record MilkCollectionRequest(
        @NotNull(message = "Farmer ID is required")
        Long farmerId,

        @NotBlank(message = "Date is required")
        String date,

        @NotBlank(message = "Session is required")
        String session,

        @NotBlank(message = "Time is required")
        String time,

        @Size(max = 30, message = "Milk type must be at most 30 characters")
        String milkType,

        @NotNull(message = "Quantity is required")
        @DecimalMin(value = "0.001", message = "Quantity must be greater than 0")
        BigDecimal quantity,

        @NotNull(message = "Fat is required")
        @DecimalMin(value = "0.0", message = "Fat cannot be negative")
        BigDecimal fat,

        @NotNull(message = "Rate is required")
        @DecimalMin(value = "0.0", message = "Rate cannot be negative")
        BigDecimal rate,

        BigDecimal amount
) {}
