package com.lab.equipmentbooking.dto.request;

import com.lab.equipmentbooking.constant.EquipmentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class EquipmentCreationRequest {
    @NotBlank(message = "EQUIPMENT_CODE_REQUIRED")
    @Size(min = 2, max = 255, message = "EQUIPMENT_CODE_INVALID")
    private String equipmentCode;

    @NotBlank(message = "EQUIPMENT_NAME_REQUIRED")
    @Size(min = 2, max = 255, message = "EQUIPMENT_NAME_VALID")
    private String name;

    @NotBlank(message = "LOCATION_REQUIRED")
    @Size(max = 255, message = "LOCATION_VALID")
    private String location;

    @Size(max = 1000, message = "DESCRIPTION_VALID")
    private String description;

    @Builder.Default
    @NotNull(message = "STATUS_INVALID")
    private EquipmentStatus status = EquipmentStatus.AVAILABLE;
}
