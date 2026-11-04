package com.lab.equipmentbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Lỗi hệ thống ngoài dự tính
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi hệ thống chưa xác định được", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(9001, "Lỗi enum key không hợp lệ", HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION(9001, "Dữ liệu bị trùng lặp hoặc vi phạm ràng buộc CSDL", HttpStatus.CONFLICT),

    // lỗi: 1xxx cho User
    USER_EXISTED(1001, "Tên đăng nhập đã tồn tại", HttpStatus.BAD_REQUEST),
    USER_NOTFOUND(1002, "Không tìm thấy người dùng", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1003, "Tài khoản hoặc mật khẩu không chính xác", HttpStatus.UNAUTHORIZED),
    USERNAME_INVALID(1004, "Tên đăng nhập phải từ 3 đến 50 ký tự", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1005, "Mật khẩu có ít nhất 6 kỹ tự", HttpStatus.BAD_REQUEST),
    FULLNAME_REQUIRED(1006, "Họ và tên không được để trống", HttpStatus.BAD_REQUEST),
    ROLE_REQUIRED(1007, "Vai trò (Role) không được để trống", HttpStatus.BAD_REQUEST),

    // lỗi:2xxx cho Equipment
    EQUIPMENT_EXISTED(2001, "Thiết bị đã tồn tại", HttpStatus.BAD_REQUEST),
    EQUIPMENT_NOTFOUND(2002, "Không tìm thấy thiết bị", HttpStatus.NOT_FOUND),
    EQUIPMENT_CODE_INVALID(2003, "Mã Thiết bị cần ít nhất 2 kí tự", HttpStatus.BAD_REQUEST),
    EQUIPMENT_CODE_REQUIRED(2004, "Mã Thiết bị là bắt buộc", HttpStatus.BAD_REQUEST),
    EQUIPMENT_NAME_INVALID(2005, "Tên Thiết bị cần ít nhất 3 kí tự", HttpStatus.BAD_REQUEST),
    EQUIPMENT_NAME_REQUIRED(2006, "Tên Thiết bị là bắt buộc", HttpStatus.BAD_REQUEST),
    LOCATION_REQUIRED(2007, "Vị trí Thiết bị là bắt buộc", HttpStatus.BAD_REQUEST),
    LOCATION_INVALID(2008, "Vị trí Thiết bị có tôi đa 255 kí tự", HttpStatus.BAD_REQUEST),
    DESCRIPTION_VALID(2009, "Mô tả thiết bị tối đa 1000 kí tự", HttpStatus.BAD_REQUEST),
    STATUS_INVALID(2010, "Trạng thái thiết bị (Status) không thỏa mãn ", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
