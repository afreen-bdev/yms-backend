package com.yms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yms.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
