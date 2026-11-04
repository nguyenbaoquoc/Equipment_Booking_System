package com.lab.equipmentbooking.dto.request;

import com.lab.equipmentbooking.constant.EquipmentStatus;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EquipmentUpdateRequest {

    @Size(max = 255, message = "LOCATION_INVALID")
    String location;

    @Size(max = 1000, message = "DESCRIPTION_INVALID")
    String description;

    EquipmentStatus status;

}
