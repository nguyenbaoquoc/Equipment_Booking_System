# BÁO CÁO TIẾN ĐỘ TUẦN 01 (19/10/2026 – 25/10/2026)
### DỰ ÁN: LAB EQUIPMENT BOOKING SYSTEM (LEBS)

**Họ và tên**: Nguyễn Quốc Bảo  
**Giai đoạn thực hiện**: Gộp Tuần 1 và Tuần 2 theo PRD (Khởi tạo, Thiết kế CSDL & Danh mục thiết bị UC-01)  
**Branch Git**: `syns_main`

---

### 1. Công việc đã hoàn thành trong tuần
* **Học tập công nghệ**:
  * Tiếp cận và nắm vững cú pháp cốt lõi của Java 21: Kiểu dữ liệu, Class, Constructor, Getter/Setter, Kế thừa, Interface, Enum, Xử lý chuỗi và Collection (`ArrayList`, `HashMap`).
  * Nắm vững thư viện thời gian Java 8+ (`LocalDateTime`, `Duration`) phục vụ cho các quy tắc đặt lịch 4 giờ và 14 ngày.
  * Tìm hiểu mô hình kiến trúc 3 tầng trong Spring Boot 3: Controller -> Service -> Repository.
* **Thiết kế hệ thống**:
  * Thiết kế sơ đồ quan hệ thực thể (ERD) cho 3 bảng chính: `users`, `equipments`, `bookings`.
  * Lập danh sách quy chuẩn các API Endpoints và các quy tắc nghiệp vụ theo PRD Baseline v1.0.
* **Triển khai mã nguồn**:
  * Khởi tạo dự án Spring Boot 3 trên thư mục `backend/` kết nối CSDL PostgreSQL 16.
  * Xây dựng Entity `Equipment` (`@Table(name = "equipments")`), `EquipmentRepository` kế thừa `JpaRepository`.
  * Viết Service và Controller triển khai trọn vẹn nhóm chức năng Danh mục thiết bị: Thêm mới, Sửa thông tin, Lấy danh sách, Xem chi tiết, Tìm kiếm theo tên hoặc mã.

---

### 2. Kết quả / Sản phẩm đạt được
* **Ứng dụng chạy thực tế**:
  * Backend Spring Boot khởi động ổn định tại cổng `8080`, kết nối CSDL PostgreSQL `lab_equipment_db`.
  * Demo thành công hành trình **UC-01 (Tra cứu và xem danh mục thiết bị)** trên Postman:
    * `POST /api/v1/equipments`: Tạo mới thiết bị thành công (mã duy nhất).
    * `GET /api/v1/equipments`: Lấy danh sách thiết bị.
    * `GET /api/v1/equipments/{id}`: Xem chi tiết 1 thiết bị.
* **Tài liệu bàn giao**:
  * File thiết kế sơ đồ CSDL tại `docs/database/init.sql`.
  * Cấu hình dự án tại `backend/src/main/resources/application.yml`.
  * Đoạn code Java độc lập kiểm tra logic tiếp giáp và xung đột thời gian `startA < endB AND startB < endA`.

---

### 3. Những vấn đề hoặc khó khăn đang gặp phải
* **Về mặt kỹ thuật**:
  * Lần đầu làm việc với Java và Spring Boot nên mất thời gian làm quen với hệ thống Annotation (`@Entity`, `@Autowired`, `@RestController`) và việc cấu hình kết nối PostgreSQL thay vì MySQL như trong một số hướng dẫn.
* **Về mặt nghiệp vụ (Nhờ Mentor xác nhận/chốt trước Tuần 2)**:
  1. *Giới hạn đặt lịch*: Giới hạn 4 giờ/lượt đặt và tối đa 14 ngày tới trong PRD (Giả định A-04) có giữ nguyên cho việc nghiệm thu không?
  2. *Độ chia nhỏ thời gian*: Hệ thống có cần quy định bước nhảy thời gian tối thiểu (ví dụ: làm tròn theo block 15 phút hoặc 30 phút) để tránh việc người dùng đặt lẻ phút gây vụn lịch không?
  3. *Lịch trình hoàn thành*: Mục tiêu của em là hoàn thành toàn bộ sản phẩm và bộ test nghiệm thu trước ngày 08/12/2026 để có thời gian ôn thi học kỳ cuối năm.

---

### 4. Kế hoạch thực hiện trong tuần tiếp theo (Tuần 02: 26/10 – 01/11/2026)
* Xây dựng tầng phản hồi chuẩn hóa `ApiResponse<T>` và cơ chế bắt lỗi toàn cục `@ControllerAdvice` theo yêu cầu FR-19.
* Tích hợp Bean Validation (`jakarta.validation`) để kiểm tra tính hợp lệ của dữ liệu đầu vào.
* Triển khai Entity `Booking` và viết API Đặt lịch ban đầu bám sát các quy tắc thời gian (BR-01 đến BR-05).
* Bắt đầu nghiên cứu cơ chế Khóa bi quan (Pessimistic Locking) trong Spring Data JPA để giải quyết bài toán chống đặt trùng đồng thời (AT-05).
