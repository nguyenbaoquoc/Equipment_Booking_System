package com.lab.equipmentbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.equipmentbooking.entity.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    boolean existsByEquipmentCode(String code);

    boolean existsByName(String name);

    Optional<Equipment> findByName(String name);

    Optional<Equipment> findByEquipmentCode(String name);
}