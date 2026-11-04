package com.lab.equipmentbooking.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lab.equipmentbooking.constant.Role;
import com.lab.equipmentbooking.dto.request.UserCreationRequest;
import com.lab.equipmentbooking.dto.response.UserResponse;
import com.lab.equipmentbooking.entity.User;
import com.lab.equipmentbooking.mapper.UserMapper;
import com.lab.equipmentbooking.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServicePasswordEncodeTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Tạo user thì mật khẩu phải được mã hóa BCrypt trước khi lưu vào DB")
    void createUser_PasswordIsEncoded() {
        // Given
        String rawPassword = "MySecretPassword123";
        UserCreationRequest request = UserCreationRequest.builder()
                .username("sepbao")
                .password(rawPassword)
                .fullName("Nguyen Quoc Bao")
                .role(Role.MANAGER)
                .build();

        User userEntity = User.builder()
                .username("sepbao")
                .password(rawPassword)
                .fullName("Nguyen Quoc Bao")
                .role(Role.MANAGER)
                .build();

        when(userRepository.existsByUsername("sepbao")).thenReturn(false);
        when(userMapper.toUser(request)).thenReturn(userEntity);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(userMapper.toUserResponse(any(User.class))).thenReturn(new UserResponse(1L, "sepbao", "Nguyen Quoc Bao", Role.MANAGER));

        // When
        userService.createUser(request);

        // Then: Kiểm tra mật khẩu trong entity đã được băm bằng BCrypt
        assertNotEquals(rawPassword, userEntity.getPassword());
        assertTrue(new BCryptPasswordEncoder(10).matches(rawPassword, userEntity.getPassword()));
    }
}
