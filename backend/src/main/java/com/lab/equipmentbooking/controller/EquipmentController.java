package com.lab.equipmentbooking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.equipmentbooking.dto.request.ApiResponse;
import com.lab.equipmentbooking.dto.request.EquipmentCreationRequest;
import com.lab.equipmentbooking.dto.request.EquipmentUpdateRequest;
import com.lab.equipmentbooking.dto.response.EquipmentResponse;
import com.lab.equipmentbooking.service.EquipmentService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EquipmentController {
    EquipmentService equipmentService;

    @PostMapping
    public ApiResponse<EquipmentResponse> creatEquipment(@RequestBody @Valid EquipmentCreationRequest request) {
        return ApiResponse.<EquipmentResponse>builder()
                .result(equipmentService.createEquipment(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<EquipmentResponse>> getallEquipments() {
        return ApiResponse.<List<EquipmentResponse>>builder()
                .result(equipmentService.getAllEquipments())
                .build();
    }

    @GetMapping("/{equipmentId}")
    public ApiResponse<EquipmentResponse> getEquipmentById(@PathVariable Long equipmentId) {
        return ApiResponse.<EquipmentResponse>builder()
                .result(equipmentService.getEquipmentById(equipmentId))
                .build();
    }

    @PutMapping("/{equipmentId}")
    public ApiResponse<EquipmentResponse> updateEquipment(@PathVariable Long equipmentId,
            @RequestBody @Valid EquipmentUpdateRequest request) {
        return ApiResponse.<EquipmentResponse>builder()
                .result(equipmentService.updateEquipment(equipmentId, request))
                .build();
    }

    @DeleteMapping("/{equipmentId}")
    public ApiResponse<Void> deleteEquipmentById(@PathVariable Long equipmentId) {
        equipmentService.deleteEquipmentById(equipmentId);
        return ApiResponse.<Void>builder()
                .message("Đã xóa thiết bị thành công")
                .build();
    }
}
