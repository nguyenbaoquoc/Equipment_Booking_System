package com.lab.equipmentbooking.dto.request;

import com.lab.equipmentbooking.constant.Role;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    // không update username vì đó là định danh cố định

    @Size(min = 6, message = "INVALID_PASSWORD")
    private String password;

    @Size(max = 255, message = "FULLNAME_INVALID")
    private String fullName;

    private Role role;
}
