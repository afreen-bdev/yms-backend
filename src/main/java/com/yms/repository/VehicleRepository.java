package com.yms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yms.entity.Vehicle;
import com.yms.entity.VehicleStatus;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	List<Vehicle> findByStatusIn(List<VehicleStatus> statuses);

}
