package com.milkcollection.backend.dto;

import java.math.BigDecimal;

public record DashboardResponse(
        long totalFarmers,
        long totalMilkRecords,
        BigDecimal totalMilkQuantity,
        BigDecimal totalMilkAmount
) {}
