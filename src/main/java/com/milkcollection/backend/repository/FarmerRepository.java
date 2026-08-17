package com.milkcollection.backend.repository;

import com.milkcollection.backend.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    boolean existsByMobile(String mobile);

    Optional<Farmer> findByMobile(String mobile);
}
