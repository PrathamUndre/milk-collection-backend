package com.milkcollection.backend.dto;

import java.math.BigDecimal;

public record MilkCollectionResponse(
        Long id,
        Long farmerId,
        String farmerName,
        String date,
        String session,
        String time,
        String milkType,
        BigDecimal quantity,
        BigDecimal fat,
        BigDecimal rate,
        BigDecimal amount
) {}
