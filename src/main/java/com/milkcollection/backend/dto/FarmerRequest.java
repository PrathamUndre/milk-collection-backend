package com.milkcollection.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FarmerRequest(
        @NotBlank(message = "Farmer name is required")
        @Size(max = 100, message = "Farmer name must be at most 100 characters")
        String name,

        @NotBlank(message = "Mobile number is required")
        @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Enter a valid 10-digit Indian mobile number")
        String mobile
) {}
