package com.lab.equipmentbooking.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lab.equipmentbooking.dto.request.UserCreationRequest;
import com.lab.equipmentbooking.dto.request.UserUpdateRequest;
import com.lab.equipmentbooking.dto.response.UserResponse;
import com.lab.equipmentbooking.entity.User;
import com.lab.equipmentbooking.exception.AppException;
import com.lab.equipmentbooking.exception.ErrorCode;
import com.lab.equipmentbooking.mapper.UserMapper;
import com.lab.equipmentbooking.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // nhận dto chuyển về entity, mã hóa mật khẩu và đẩy vô db
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    // query tất cả User trả về list UserResponse
    public List<UserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toUserResponse).toList();
    }

    // lấy thông tin 1 người theo id
    public UserResponse getUserbyID(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    }

    // cập nhật thông tin người dùng
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        userMapper.userUpdate(user, request);

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    // xóa Toàn bộ user
    public void deleteUsers() {
        userRepository.deleteAll();
    }

    // xóa User theo id
    public void deleteUserbyId(Long id) {
        if (userRepository.existsById(id) == false)
            throw new AppException(ErrorCode.USER_NOTFOUND);
        userRepository.deleteById(id);
    }
}
