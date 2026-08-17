package com.milkcollection.backend.service;

import com.milkcollection.backend.dto.MilkCollectionRequest;
import com.milkcollection.backend.dto.MilkCollectionResponse;
import com.milkcollection.backend.entity.Farmer;
import com.milkcollection.backend.entity.MilkCollection;
import com.milkcollection.backend.repository.MilkCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MilkCollectionService {

    private final MilkCollectionRepository milkRepository;
    private final FarmerService farmerService;

    @Transactional
    public MilkCollectionResponse create(MilkCollectionRequest request) {
        Farmer farmer = farmerService.findEntity(request.farmerId());

        MilkCollection milk = new MilkCollection();
        applyRequest(milk, farmer, request);

        return toResponse(milkRepository.save(milk));
    }

    @Transactional(readOnly = true)
    public List<MilkCollectionResponse> getAll() {
        return milkRepository.findAllByOrderByIdDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MilkCollectionResponse getById(Long id) {
        MilkCollection milk = milkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Milk record not found with id: " + id));

        return toResponse(milk);
    }

    @Transactional(readOnly = true)
    public List<MilkCollectionResponse> getByFarmer(Long farmerId) {
        farmerService.findEntity(farmerId);

        return milkRepository.findByFarmerIdOrderByIdDesc(farmerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MilkCollectionResponse> getByDate(String date) {
        return milkRepository.findByDateOrderByIdDesc(date)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MilkCollectionResponse> getByDateAndSession(String date, String session) {
        return milkRepository.findByDateAndSessionOrderByIdDesc(date, session)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public MilkCollectionResponse update(Long id, MilkCollectionRequest request) {
        MilkCollection milk = milkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Milk record not found with id: " + id));

        Farmer farmer = farmerService.findEntity(request.farmerId());
        applyRequest(milk, farmer, request);

        return toResponse(milkRepository.save(milk));
    }

    @Transactional
    public void delete(Long id) {
        MilkCollection milk = milkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Milk record not found with id: " + id));

        milkRepository.delete(milk);
    }

    private void applyRequest(
            MilkCollection milk,
            Farmer farmer,
            MilkCollectionRequest request
    ) {
        milk.setFarmer(farmer);
        milk.setDate(request.date());
        milk.setSession(request.session());
        milk.setTime(request.time());
        milk.setMilkType(
                request.milkType() == null || request.milkType().isBlank()
                        ? "Cow"
                        : request.milkType().trim()
        );
        milk.setQuantity(request.quantity());
        milk.setFat(request.fat());
        milk.setRate(request.rate());

        // Amount is calculated on the server so the database remains consistent.
        BigDecimal amount = request.quantity()
                .multiply(request.rate())
                .setScale(2, RoundingMode.HALF_UP);

        milk.setAmount(amount);
    }

    private MilkCollectionResponse toResponse(MilkCollection milk) {
        return new MilkCollectionResponse(
                milk.getId(),
                milk.getFarmer().getId(),
                milk.getFarmer().getName(),
                milk.getDate(),
                milk.getSession(),
                milk.getTime(),
                milk.getMilkType(),
                milk.getQuantity(),
                milk.getFat(),
                milk.getRate(),
                milk.getAmount()
        );
    }
}
