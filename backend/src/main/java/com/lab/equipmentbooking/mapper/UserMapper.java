package com.lab.equipmentbooking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import com.lab.equipmentbooking.dto.request.UserCreationRequest;
import com.lab.equipmentbooking.dto.request.UserUpdateRequest;
import com.lab.equipmentbooking.dto.response.UserResponse;
import com.lab.equipmentbooking.entity.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void userUpdate(@MappingTarget User user, UserUpdateRequest request);

}
