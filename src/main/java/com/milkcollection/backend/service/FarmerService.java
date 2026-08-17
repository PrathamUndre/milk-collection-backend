package com.milkcollection.backend.service;

import com.milkcollection.backend.dto.FarmerRequest;
import com.milkcollection.backend.dto.FarmerResponse;
import com.milkcollection.backend.entity.Farmer;
import com.milkcollection.backend.repository.FarmerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerService {

    private final FarmerRepository farmerRepository;

    @Transactional
    public FarmerResponse create(FarmerRequest request) {
        if (farmerRepository.existsByMobile(request.mobile())) {
            throw new IllegalArgumentException("A farmer with this mobile number already exists");
        }

        Farmer farmer = Farmer.builder()
                .name(request.name().trim())
                .mobile(request.mobile().trim())
                .build();

        return toResponse(farmerRepository.save(farmer));
    }

    @Transactional(readOnly = true)
    public List<FarmerResponse> getAll() {
        return farmerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public FarmerResponse getById(Long id) {
        return toResponse(findEntity(id));
    }

    @Transactional
    public FarmerResponse update(Long id, FarmerRequest request) {
        Farmer farmer = findEntity(id);

        farmerRepository.findByMobile(request.mobile())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("A farmer with this mobile number already exists");
                });

        farmer.setName(request.name().trim());
        farmer.setMobile(request.mobile().trim());

        return toResponse(farmerRepository.save(farmer));
    }

    @Transactional
    public void delete(Long id) {
        Farmer farmer = findEntity(id);
        farmerRepository.delete(farmer);
    }

    public Farmer findEntity(Long id) {
        return farmerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Farmer not found with id: " + id));
    }

    private FarmerResponse toResponse(Farmer farmer) {
        return new FarmerResponse(
                farmer.getId(),
                farmer.getName(),
                farmer.getMobile()
        );
    }
}
