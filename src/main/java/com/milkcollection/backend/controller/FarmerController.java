package com.milkcollection.backend.controller;

import com.milkcollection.backend.dto.FarmerRequest;
import com.milkcollection.backend.dto.FarmerResponse;
import com.milkcollection.backend.service.FarmerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FarmerResponse create(@Valid @RequestBody FarmerRequest request) {
        return farmerService.create(request);
    }

    @GetMapping
    public List<FarmerResponse> getAll() {
        return farmerService.getAll();
    }

    @GetMapping("/{id}")
    public FarmerResponse getById(@PathVariable Long id) {
        return farmerService.getById(id);
    }

    @PutMapping("/{id}")
    public FarmerResponse update(
            @PathVariable Long id,
            @Valid @RequestBody FarmerRequest request
    ) {
        return farmerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        farmerService.delete(id);
    }
}
