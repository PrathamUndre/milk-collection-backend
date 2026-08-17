package com.milkcollection.backend.controller;

import com.milkcollection.backend.dto.MilkCollectionRequest;
import com.milkcollection.backend.dto.MilkCollectionResponse;
import com.milkcollection.backend.service.MilkCollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milk")
@RequiredArgsConstructor
public class MilkCollectionController {

    private final MilkCollectionService milkService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MilkCollectionResponse create(
            @Valid @RequestBody MilkCollectionRequest request
    ) {
        return milkService.create(request);
    }

    @GetMapping
    public List<MilkCollectionResponse> getAll() {
        return milkService.getAll();
    }

    @GetMapping("/{id}")
    public MilkCollectionResponse getById(@PathVariable Long id) {
        return milkService.getById(id);
    }

    @GetMapping("/farmer/{farmerId}")
    public List<MilkCollectionResponse> getByFarmer(
            @PathVariable Long farmerId
    ) {
        return milkService.getByFarmer(farmerId);
    }

    @GetMapping("/date/{date}")
    public List<MilkCollectionResponse> getByDate(
            @PathVariable String date
    ) {
        return milkService.getByDate(date);
    }

    @GetMapping("/filter")
    public List<MilkCollectionResponse> getByDateAndSession(
            @RequestParam String date,
            @RequestParam String session
    ) {
        return milkService.getByDateAndSession(date, session);
    }

    @PutMapping("/{id}")
    public MilkCollectionResponse update(
            @PathVariable Long id,
            @Valid @RequestBody MilkCollectionRequest request
    ) {
        return milkService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        milkService.delete(id);
    }
}
