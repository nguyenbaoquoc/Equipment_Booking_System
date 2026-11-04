# 🏢 Lab Equipment Booking System (LEBS)

> **Hệ thống Quản lý và Đặt lịch Thiết bị Phòng Lab**  
> Dựa trên tài liệu đặc tả yêu cầu sản phẩm: `Lab_Equipment_Booking_System_PRD_v1.0`

---

## 📌 1. Giới thiệu dự án

Hệ thống **Lab Equipment Booking System** là ứng dụng web toàn diện hỗ trợ các thành viên phòng Lab (ML&IoT Lab) tra cứu, đặt trước và quản lý lịch sử dụng các thiết bị vật lý dùng chung (máy trạm GPU, camera công nghiệp, kính hiển vi, kit nhúng...). 

Hệ thống giải quyết triệt để các vấn đề:
- ❌ **Tránh trùng lịch hoàn toàn**: Cơ chế khóa đồng thời ngăn chặn tuyệt đối tình trạng 2 người cùng đặt 1 thiết bị vào 1 khoảng thời gian bị chồng lấn.
- 🔒 **Bảo vệ quyền riêng tư**: Thành viên chỉ thấy khung giờ bận, không thấy danh tính hoặc mục đích sử dụng của người khác.
- 📜 **Minh bạch lịch sử**: Lưu vết toàn bộ lịch sử đặt và hủy, không xóa cứng dữ liệu.

---

## 🛠️ 2. Công nghệ sử dụng (Tech Stack)

Dự án áp dụng kiến trúc chuẩn phân tầng **Client - Server (Decoupled Full-Stack)**:

| Thành phần | Công nghệ | Phiên bản khuyến nghị | Ghi chú |
| :--- | :--- | :--- | :--- |
| **Backend** | **Java Spring Boot 3** | Java 17+ / Spring Boot 3.3+ | RESTful API, Layered Architecture |
| **Database Access** | **Spring Data JPA / Hibernate** | Hibernate 6+ | Pessimistic Locking & Transactions |
| **Security** | **Spring Security 6 & Nimbus JWT** | Nimbus JOSE + JWT | Phân quyền RBAC (Role: `MEMBER`, `MANAGER`) |
| **Database** | **PostgreSQL** (hoặc MySQL 8+) | PostgreSQL 16+ | Hỗ trợ Transaction Serializable & Range constraints |
| **Frontend** | **React.js** | React 18+ (Vite) | Single Page Application (SPA) |
| **Styling & UI** | **TailwindCSS + Ant Design / Shadcn** | Mới nhất | Hỗ trợ Calendar Grid / Timeline View |
| **HTTP Client** | **Axios** | Mới nhất | Có Axios Interceptors đính kèm JWT Bearer |
| **Testing** | **JUnit 5, Mockito, Python script** | - | Script kiểm thử Concurrency (AT-05) |

---

## 📂 3. Cấu trúc thư mục dự án (Project Structure)

```text
Equipment_Booking_System/
│
├── backend/                                   # Ứng dụng Java Spring Boot 3
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/lab/equipmentbooking/
│   │   │   │   ├── config/                   # Cấu hình: Security, WebMvc (CORS), Swagger/OpenAPI
│   │   │   │   ├── constant/                 # Enums: BookingStatus, RoleType, ErrorCodes
│   │   │   │   ├── controller/               # REST API Controllers (Auth, Equipment, Booking)
│   │   │   │   ├── dto/
│   │   │   │   │   ├── request/              # Request Payloads (Validation với Jakarta Validation)
│   │   │   │   │   └── response/             # Response DTOs & ApiResponse chuẩn hóa
│   │   │   │   ├── entity/                   # JPA Entities (User, Equipment, Booking)
│   │   │   │   ├── exception/                # GlobalExceptionHandler (@ControllerAdvice), AppException
│   │   │   │   ├── mapper/                   # MapStruct / Chuyển đổi Entity <-> DTO
│   │   │   │   ├── repository/               # Spring Data JPA Repositories (có @Lock truy vấn)
│   │   │   │   └── service/                  # Business Logic Services
│   │   │   └── resources/
│   │   │       └── application.yml           # File cấu hình CSDL, Port, JWT Secret
│   │   └── test/                             # Unit tests, Integration tests
│   │       └── java/com/lab/equipmentbooking/
│   │           └── concurrency/              # Kiểm thử tranh chấp đồng thời (AT-05)
│   └── pom.xml                               # Quản lý thư viện Maven (hoặc build.gradle)
│
├── frontend/                                  # Ứng dụng React.js (Vite)
│   ├── public/                               # Tệp tĩnh (favicon, logo)
│   ├── src/
│   │   ├── assets/                           # Hình ảnh, icons, styles
│   │   ├── components/
│   │   │   ├── common/                       # Navbar, Footer, Button, Modal, Loading...
│   │   │   └── calendar/                     # Component hiển thị lưới thời gian bận/rảnh
│   │   ├── context/                          # AuthContext (quản lý User đăng nhập, Token)
│   │   ├── hooks/                            # Custom React Hooks (useAuth, useEquipments...)
│   │   ├── layouts/                          # MainLayout, DashboardLayout
│   │   ├── pages/                            # Trang: Login, EquipmentList, Detail, MyBookings, Admin
│   │   ├── routes/                           # Cấu hình định tuyến & ProtectedRoute
│   │   ├── services/                         # Tầng gọi API qua Axios (api.js, bookingService.js...)
│   │   └── utils/                            # Hàm tiện ích xử lý Date/Time, format tiền tệ/chuỗi
│   ├── package.json                          # Thư viện npm
│   └── vite.config.js                        # Cấu hình Vite dev server
│
├── docs/                                      # Tài liệu thiết kế & Hướng dẫn
│   ├── architecture/                         # Sơ đồ kiến trúc, luồng hoạt động
│   ├── api/                                  # Tài liệu API (OpenAPI/Swagger specs)
│   └── database/                             # Sơ đồ ERD, kịch bản tạo bảng CSDL
│
├── reference/                                 # Tài liệu tham chiếu gốc
│   ├── Lab_Equipment_Booking_System_PRD_v1.0 (1).docx
│   └── extracted_prd.txt
│
├── scripts/                                   # Kịch bản hỗ trợ kiểm thử & nạp dữ liệu
│   └── test_concurrency.py                   # Script Python giả lập 5 request đồng thời (AT-05)
│
├── .gitignore                                 # Loại bỏ target/, node_modules/, secrets
└── README.md                                  # Tài liệu tổng quan dự án
```

---

## ⚖️ 4. Quy tắc nghiệp vụ cốt lõi (Core Business Rules)

Hệ thống tuân thủ nghiêm ngặt các quy tắc nghiệp vụ theo PRD:

1. **Quy ước khoảng thời gian (BR-01 & BR-02)**:
   - Khoảng đặt được hiểu là nửa mở: `[start_time, end_time)`.
   - Hai khoảng chỉ tiếp giáp tại đầu mút (ví dụ `09:00 - 10:00` và `10:00 - 11:00`) **được phép** cùng tồn tại.
   - Hai lượt đặt $A$ và $B$ của cùng một thiết bị bị coi là **xung đột** khi và chỉ khi:
     $$\text{start}_A < \text{end}_B \quad \text{AND} \quad \text{start}_B < \text{end}_A$$
2. **Giới hạn thời gian (BR-03 & BR-04)**:
   - `start_time` phải ở tương lai (`start_time > now()`).
   - Thời lượng mỗi lượt: $0 < \text{duration} \le 4\text{ giờ}$.
   - Thời gian bắt đầu không quá **14 ngày** tính từ lúc đặt.
3. **Trạng thái thiết bị (BR-05 & BR-11)**:
   - Chỉ thiết bị ở trạng thái `ACTIVE` (Nhận đặt) mới cho phép tạo lượt đặt mới.
   - Khi thiết bị chuyển sang `INACTIVE` (Ngừng nhận đặt), các lượt đặt đã xác nhận trong tương lai **không bị tự hủy**; Quản lý (Manager) sẽ chủ động rà soát và hủy thủ công kèm lý do.
4. **Không sửa đổi thời gian (BR-10)**:
   - Không cho phép chỉnh sửa giờ của lượt đã xác nhận. Nếu muốn đổi giờ, người dùng phải hủy và tạo lượt mới.
5. **Quyền riêng tư (BR-13)**:
   - Tài khoản `MEMBER` khi xem lịch chỉ thấy các khoảng giờ bận, **tuyệt đối không thấy** tên hoặc mục đích sử dụng của thành viên khác.

---

## ⚡ 5. Chiến lược xử lý Tranh chấp đồng thời (AT-05 / NFR-04)

Yêu cầu nghiệm thu quan trọng nhất (**AT-05**): *Khi có 5 yêu cầu đồng thời gửi đến cùng 1 thiết bị và cùng 1 khung giờ, hệ thống chỉ cho phép đúng 1 yêu cầu thành công, 4 yêu cầu còn lại bị từ chối với mã `409 Conflict`*.

### Giải pháp kỹ thuật trong Spring Boot:
Sử dụng **Database Transaction** kết hợp **Pessimistic Write Lock (`SELECT ... FOR UPDATE`) trên bản ghi Thiết bị**:

> **Lưu ý kỹ thuật quan trọng**: Không thể chỉ khóa các bản ghi `Booking` đang trùng, vì khi khung giờ còn trống hoàn toàn, câu lệnh tìm kiếm sẽ trả về 0 bản ghi (không có dòng nào bị khóa), dẫn đến cả 5 request đều tưởng còn trống và cùng chèn dữ liệu!
> 
> **Cách xử lý đúng**: Khóa dòng của chính thiết bị đó trước khi kiểm tra:

```java
// 1. Trong EquipmentRepository.java: Khóa dòng thiết bị khi bắt đầu giao dịch
@Lock(LockModeType.PESSIMISTIC_WRITE)
@Query("SELECT e FROM Equipment e WHERE e.id = :id")
Optional<Equipment> findByIdForUpdate(@Param("id") Long id);

// 2. Trong BookingService.java:
@Transactional
public BookingResponse createBooking(BookingCreationRequest request) {
    // Luồng nào đến trước sẽ khóa thiết bị này, 4 luồng sau phải xếp hàng chờ
    Equipment equipment = equipmentRepository.findByIdForUpdate(request.getEquipmentId())
        .orElseThrow(() -> new AppException(ErrorCode.EQUIPMENT_NOT_FOUND));

    // Sau khi có khóa an toàn, mới kiểm tra trùng lịch
    boolean isConflicted = bookingRepository.existsOverlappingBooking(...);
    if (isConflicted) {
        throw new AppException(ErrorCode.BOOKING_TIME_CONFLICT); // Trả về mã 409
    }

    // Tạo và lưu lượt đặt mới...
}
```
*Ngoài ra, hệ thống kích hoạt thêm ràng buộc **Exclusion Constraint** trong PostgreSQL (`tstzrange` với `&&`) làm chốt chặn tầng thứ hai ở cấp độ CSDL để đảm bảo tính toàn vẹn tuyệt đối.*

---

## 🚀 6. Hướng dẫn cài đặt & Khởi chạy cục bộ (Local Development)

### 6.1. Yêu cầu môi trường (Prerequisites)
- **Java**: JDK 17 hoặc JDK 21 (Eclipse Temurin / Oracle OpenJDK)
- **Node.js**: v18+ hoặc v20+ (kèm `npm`)
- **Database**: PostgreSQL (cổng 5432) hoặc MySQL (cổng 3306)
- **Python**: 3.9+ (để chạy script kiểm thử concurrency)

### 6.2. Cấu hình Cơ sở dữ liệu
1. Tạo CSDL trong PostgreSQL:
   ```sql
   CREATE DATABASE lab_equipment_db;
   ```
2. Cập nhật thông tin kết nối trong `backend/src/main/resources/application.yml` (username, password).

### 6.3. Khởi chạy Backend (Spring Boot)
```bash
cd backend
# Chạy với Maven Wrapper trên Windows:
mvnw.cmd clean spring-boot:run
# Hoặc trên Linux/macOS:
./mvnw clean spring-boot:run
```
API sẽ lắng nghe tại: `http://localhost:8080/api/v1`  
Tài liệu Swagger UI: `http://localhost:8080/api/v1/swagger-ui/index.html`

### 6.4. Khởi chạy Frontend (React Vite)
```bash
cd frontend
npm install
npm run dev
```
Giao diện sẽ chạy tại: `http://localhost:5173`

### 6.5. Chạy kịch bản kiểm thử Concurrency (AT-05)
```bash
# Cài đặt thư viện aiohttp nếu chưa có
pip install aiohttp

# Chạy test bắn 5 request đồng thời
python scripts/test_concurrency.py
```

---

## 🎯 7. Ma trận nghiệm thu (Acceptance Test Checklist)

| Mã | Tình huống kiểm thử | Tiêu chí đạt | Trạng thái |
| :---: | :--- | :--- | :---: |
| **AT-01** | Member xem thiết bị và khung giờ bận | Thấy khoảng bận, không thấy tên/mục đích người khác | ⬜ Chưa làm |
| **AT-02** | Đặt lịch hợp lệ | Tạo đúng 1 lượt xác nhận, hiện trong "Lịch của tôi" | ⬜ Chưa làm |
| **AT-03** | Đặt giờ trùng hoặc chứa trọn lịch cũ | Báo lỗi xung đột `409 Conflict`, không tạo lịch mới | ⬜ Chưa làm |
| **AT-04** | Đặt giờ tiếp giáp (10:00–11:00 và 11:00–12:00) | Thành công | ⬜ Chưa làm |
| **AT-05** | Bắn 5 request đồng thời vào cùng 1 khung giờ | **Chính xác 1 thành công, 4 thất bại** | ⬜ Chưa làm |
| **AT-06** | Đặt giờ quá khứ / > 4h / > 14 ngày | Bị từ chối với phản hồi rõ ràng (`400 Bad Request`) | ⬜ Chưa làm |
| **AT-07** | Member hủy lịch tương lai của mình | Lịch chuyển sang `CANCELLED`, slot được giải phóng | ⬜ Chưa làm |
| **AT-08** | Member cố tình hủy lịch của người khác | Bị từ chối `403 Forbidden` | ⬜ Chưa làm |
| **AT-09** | Hủy lịch đã bắt đầu hoặc hủy lại lịch cũ | Bị từ chối | ⬜ Chưa làm |
| **AT-10** | Manager ngừng nhận đặt thiết bị có lịch tương lai | Cảnh báo lịch tương lai, không tự động hủy lịch cũ | ⬜ Chưa làm |
| **AT-11** | Manager hủy lịch của Member kèm lý do | Lịch chuyển `CANCELLED`, ghi nhận người hủy và lý do | ⬜ Chưa làm |
| **AT-12** | Manager tạo thiết bị trùng mã định danh | Bị từ chối | ⬜ Chưa làm |

---

## 📅 8. Kế hoạch bàn giao (Milestones)

- **Giai đoạn 1**: Thiết kế CSDL, tạo Entity, hoàn thành CRUD thiết bị và phân quyền cơ bản.
- **Giai đoạn 2**: Hoàn thiện API Booking, Validation logic, cài đặt Pessimistic Locking xử lý tranh chấp.
- **Giai đoạn 3**: Tích hợp Spring Security 6 & JWT, viết bộ kiểm thử tự động đạt chuẩn AT-01 đến AT-12.
- **Giai đoạn 4**: Xây dựng giao diện React (Calendar View / Timeline Grid), kết nối API.
- **Giai đoạn 5**: Seed dữ liệu mẫu (100 thiết bị, 1.000 lượt đặt), diễn tập thuyết minh kỹ thuật (Technical Defense).

---
*Tài liệu được khởi tạo và duy trì bởi đội ngũ phát triển dự án.*
