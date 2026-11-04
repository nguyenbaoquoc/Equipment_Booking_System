package com.lab.equipmentbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lab.equipmentbooking.dto.request.ApiResponse;
import com.lab.equipmentbooking.dto.request.EquipmentCreationRequest;
import com.lab.equipmentbooking.dto.request.EquipmentUpdateRequest;
import com.lab.equipmentbooking.dto.response.EquipmentResponse;
import com.lab.equipmentbooking.entity.Equipment;
import com.lab.equipmentbooking.exception.AppException;
import com.lab.equipmentbooking.exception.ErrorCode;
import com.lab.equipmentbooking.mapper.EquipmentMapper;
import com.lab.equipmentbooking.repository.EquipmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentMapper equipmentMapper;
    private final EquipmentRepository equipmentRepository;

    // tạo thiết bị
    public EquipmentResponse createEquipment(EquipmentCreationRequest request) {
        if (equipmentRepository.existsByEquipmentCode(request.getEquipmentCode())) {
            throw new AppException(ErrorCode.EQUIPMENT_EXISTED);
        }

        Equipment equipment = equipmentMapper.toEquipment(request);

        Equipment savedEquipment = equipmentRepository.save(equipment);
        return equipmentMapper.toEquipmentResponse(savedEquipment);
    }

    // lấy toàn bộ danh sách thiết bị
    public List<EquipmentResponse> getAllEquipments() {
        return equipmentRepository
                .findAll()
                .stream()
                .map(equipmentMapper::toEquipmentResponse).toList();
    }

    // lấy thiết bị bằng code
    public EquipmentResponse getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .map(equipmentMapper::toEquipmentResponse)
                .orElseThrow(() -> new AppException(ErrorCode.EQUIPMENT_NOTFOUND));
    }

    // cập nhật thiết bị
    public EquipmentResponse updateEquipment(Long id, EquipmentUpdateRequest request) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EQUIPMENT_NOTFOUND));

        equipmentMapper.equipmentUpdate(equipment, request);

        Equipment updatedEquipment = equipmentRepository.save(equipment);

        return equipmentMapper.toEquipmentResponse(updatedEquipment);
    }

    // xóa toàn bộ equipment
    public void deleteEquipmentById(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.EQUIPMENT_NOTFOUND);
        }

        equipmentRepository.deleteById(id);
    }

    // lấy các thiết bị có sẵn

    // cập nhật trạng thái thiết bị
}
