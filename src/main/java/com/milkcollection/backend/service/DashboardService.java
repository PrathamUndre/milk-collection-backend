package com.milkcollection.backend.service;

import com.milkcollection.backend.dto.DashboardResponse;
import com.milkcollection.backend.repository.FarmerRepository;
import com.milkcollection.backend.repository.MilkCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FarmerRepository farmerRepository;
    private final MilkCollectionRepository milkRepository;

    public DashboardResponse getDashboard() {
        List<com.milkcollection.backend.entity.MilkCollection> records =
                milkRepository.findAll();

        BigDecimal totalQuantity = records.stream()
                .map(com.milkcollection.backend.entity.MilkCollection::getQuantity)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalAmount = records.stream()
                .map(com.milkcollection.backend.entity.MilkCollection::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DashboardResponse(
                farmerRepository.count(),
                milkRepository.count(),
                totalQuantity,
                totalAmount
        );
    }
}
