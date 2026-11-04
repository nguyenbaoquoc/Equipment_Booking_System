package com.lab.equipmentbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import com.lab.equipmentbooking.dto.request.EquipmentCreationRequest;
import com.lab.equipmentbooking.dto.request.EquipmentUpdateRequest;
import com.lab.equipmentbooking.dto.response.EquipmentResponse;
import com.lab.equipmentbooking.entity.Equipment;

@Component
@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    Equipment toEquipment(EquipmentCreationRequest request);

    EquipmentResponse toEquipmentResponse(Equipment equipment);

    void equipmentUpdate(@MappingTarget Equipment equipment, EquipmentUpdateRequest request);
}
