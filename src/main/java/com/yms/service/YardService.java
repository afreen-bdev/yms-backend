package com.yms.service;

import com.yms.entity.Vehicle;

public interface YardService {
    Vehicle allocateToYard(Long vehicleId);
}