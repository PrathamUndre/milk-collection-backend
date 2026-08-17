package com.milkcollection.backend.repository;

import com.milkcollection.backend.entity.MilkCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilkCollectionRepository extends JpaRepository<MilkCollection, Long> {

    List<MilkCollection> findByFarmerIdOrderByIdDesc(Long farmerId);

    List<MilkCollection> findAllByOrderByIdDesc();

    List<MilkCollection> findByDateOrderByIdDesc(String date);

    List<MilkCollection> findByDateAndSessionOrderByIdDesc(String date, String session);
}
