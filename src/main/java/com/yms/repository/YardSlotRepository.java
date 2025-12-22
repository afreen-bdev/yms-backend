package com.yms.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yms.entity.YardSlot;

public interface YardSlotRepository extends JpaRepository<YardSlot, Long> {
    Optional<YardSlot> findFirstByOccupiedFalse();
}
