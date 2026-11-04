# GIÁO TRÌNH JAVA CẤP TỐC, OOP CHUYÊN SÂU & HỆ THỐNG BÀI TẬP THỰC CHIẾN TỪNG PHẦN
### Dự án: Lab Equipment Booking System (LEBS)

> **Mục tiêu tài liệu**: Dành riêng cho sếp Bảo chuyển đổi tư duy từ Python (FastAPI) và SQL (Oracle) sang Java 21 + Spring Boot 3 chỉ trong **2 - 3 ngày**.
> **Cấu trúc thực hành đặc biệt**:
> - **Phần 1 (Cấu trúc cơ bản)**: Đi kèm **5 bài tập từ dễ đến khó** có lời giải mẫu ngay sau phần 1.
> - **Phần 2 (OOP chuyên sâu)**: Đi kèm **5 bài tập từ dễ đến khó** có lời giải mẫu ngay sau phần 2.
> - **Phần 3 (Thư viện chuẩn)**: **Mỗi mục nhỏ (3.1 đến 3.6) đều có 1 bài tập thực chiến riêng biệt** có lời giải mẫu đi liền ngay sau đó.

---

## MỤC LỤC
1. [PHẦN 1: CÁC CẤU TRÚC ĐIỀU KHIỂN CƠ BẢN](#phần-1-các-cấu-trúc-điều-khiển-cơ-bản)
   - 1.1. Kiểu dữ liệu, Biến và Ép kiểu
   - 1.2. Toán tử
   - 1.3. Cấu trúc rẽ nhánh (If - Else, Toán tử 3 ngôi, Switch Expression)
   - 1.4. Vòng lặp (For, For-each, While, Do-while, Break & Continue)
   - 🎯 **5 BÀI TẬP THỰC HÀNH PHẦN 1 (TỪ DỄ ĐẾN KHÓ)**
2. [PHẦN 2: LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG (OOP) TRONG SPRING BOOT](#phần-2-lập-trình-hướng-đối-tượng-oop-trong-spring-boot)
   - 2.1. Access Modifiers (Phạm vi truy cập)
   - 2.2. Từ khóa `this` và `super`
   - 2.3. Bốn trụ cột OOP (Đóng gói, Kế thừa, Đa hình, Trừu tượng)
   - 2.4. Record (DTO bất biến) & Enum
   - 🎯 **5 BÀI TẬP THỰC HÀNH PHẦN 2 (TỪ DỄ ĐẾN KHÓ)**
3. [PHẦN 3: CẨM NANG THƯ VIỆN CHUẨN & BÀI TẬP TỪNG MỤC NHỎ](#phần-3-cẩm-nang-thư-viện-chuẩn--bài-tập-từng-mục-nhỏ)
   - 3.1. `java.lang` (String, Objects, Wrapper) + 🎯 **Bài tập 3.1**
   - 3.2. `java.util` (List, Map, Optional) + 🎯 **Bài tập 3.2**
   - 3.3. `java.util.stream` (Stream API) + 🎯 **Bài tập 3.3**
   - 3.4. `java.time` (LocalDateTime, Duration, ChronoUnit) + 🎯 **Bài tập 3.4**
   - 3.5. `jakarta.validation` (Bean Validation) + 🎯 **Bài tập 3.5**
   - 3.6. Lombok & Spring Data JPA Repositories + 🎯 **Bài tập 3.6**

---

# PHẦN 1: CÁC CẤU TRÚC ĐIỀU KHIỂN CƠ BẢN

---

### 1.1. Kiểu dữ liệu, Biến và Ép kiểu

Java là ngôn ngữ **định kiểu tĩnh (Static Typing)** và phân biệt hoa thường.

| Khái niệm | Python | Java 21 | Ghi chú cho dự án LEBS |
| :--- | :--- | :--- | :--- |
| Số nguyên lớn | `id = 100` | `Long id = 100L;` | Khóa chính Database luôn dùng `Long` |
| Số nguyên nhỏ | `count = 5` | `int count = 5;` | Dùng cho đếm vòng lặp, số giờ |
| Số thực | `hours = 2.5` | `double hours = 2.5;` | Dùng cho tính toán thời gian lẻ |
| Chuỗi ký tự | `code = "EQ-01"` | `String code = "EQ-01";` | Dùng dấu nháy kép `"..."` |
| Đúng / Sai | `active = True` | `boolean active = true;` | Viết thường: `true`, `false` |
| Giá trị rỗng | `val = None` | `Object val = null;` | Mọi Object trong Java đều có thể là `null` |
| Tự suy luận kiểu | `x = 10` | `var x = 10;` (Java 10+) | Dùng khi lười viết kiểu dài trong method |

#### Ép kiểu (Type Casting):
* **Ép kiểu ngầm định (nhỏ sang lớn)**: `int a = 10; double b = a; // Thành 10.0`
* **Ép kiểu tường minh (lớn sang nhỏ)**: `double d = 3.99; int i = (int) d; // Cắt phần thập phân thành 3`
* **Chuyển chuỗi sang số**: `Long id = Long.parseLong("101"); int port = Integer.parseInt("8080");`

---

### 1.2. Toán tử (Toán học, So sánh, Logic)

| Loại toán tử | Python | Java 21 | Ví dụ thực tế |
| :--- | :--- | :--- | :--- |
| Cộng, Trừ, Nhân, Chia | `+`, `-`, `*`, `/` | `+`, `-`, `*`, `/` | `long totalMinutes = hours * 60;` |
| Chia lấy dư | `%` | `%` | `int remainder = minutes % 60;` |
| Tăng / Giảm 1 đơn vị | `i += 1` | `i++` hoặc `++i` | Dùng trong vòng lặp for |
| So sánh số | `==`, `!=`, `<`, `<=`, `>`, `>=` | `==`, `!=`, `<`, `<=`, `>`, `>=` | `if (durationHours > 4) // BR-04` |
| So sánh chuỗi | `name == "admin"` | `name.equals("admin")` | **TUYỆT ĐỐI KHÔNG DÙNG `==` CHO CHUỖI** |
| Và (AND logic) | `and` | `&&` | `if (startA < endB && startB < endA)` |
| Hoặc (OR logic) | `or` | `\|\|` | `if (isPast \|\| isOverLimit)` |
| Phủ định (NOT) | `not` | `!` | `if (!equipment.isActive())` |

---

### 1.3. Cấu trúc rẽ nhánh: If - Else, Toán tử 3 ngôi, Switch Expression

#### A. If - Else If - Else
```java
if (duration <= 0) {
    System.out.println("Thời lượng phải lớn hơn 0");
} else if (duration > 4) {
    System.out.println("Thời lượng không được vượt quá 4 giờ");
} else {
    System.out.println("Khung giờ hợp lệ");
}
```

#### B. Toán tử 3 ngôi (Ternary Operator)
Cú pháp: `điều_kiện ? giá_trị_nếu_đúng : giá_trị_nếu_sai;`
```java
// Python: status_text = "Hoạt động" if is_active else "Bảo trì"
String statusText = isActive ? "Hoạt động" : "Bảo trì";
```

#### C. Switch - Case kiểu mới (Switch Expression Java 14+)
```java
public enum Role { MEMBER, MANAGER, GUEST }
Role userRole = Role.MANAGER;

String permissions = switch (userRole) {
    case MANAGER -> "Toàn quyền quản trị danh mục và hủy lịch bất kỳ";
    case MEMBER  -> "Được đặt lịch và chỉ tự hủy lịch của bản thân";
    case GUEST   -> "Chỉ được xem danh mục thiết bị công khai";
};
```

---

### 1.4. Vòng lặp: For, For-each, While, Do-While, Break & Continue

* **Vòng lặp `for` truyền thống**: `for (int i = 1; i <= 5; i++) { ... }` (dùng khi cần chỉ số).
* **Vòng lặp `for-each` nâng cao**: `for (String code : equipmentCodes) { ... }` (dùng nhiều nhất).
* **Vòng lặp `while`**: Chạy khi điều kiện còn đúng.
* **Vòng lặp `do-while`**: Chạy ít nhất 1 lần rồi mới kiểm tra điều kiện.
* **`break`**: Thoát ngay khỏi vòng lặp khi tìm thấy mục tiêu.
* **`continue`**: Bỏ qua lượt hiện tại, nhảy sang phần tử tiếp theo.

---

## 🎯 5 BÀI TẬP THỰC HÀNH PHẦN 1 (TỪ DỄ ĐẾN KHÓ)

---

#### BÀI TẬP 1.1 (MỨC ĐỘ: DỄ): KHAI BÁO BIẾN VÀ ÉP KIỂU THỜI GIAN
* **Đề bài**: Khai báo biến `durationHours` kiểu số thực `double = 2.5`. Tính ra tổng số phút tương ứng (lưu vào biến `totalMinutes` kiểu `int`). In kết quả ra màn hình dạng: `"Thời lượng: 2.5 giờ tương đương 150 phút"`.
* **Lời giải mẫu**:
```java
public class Exercise1_1 {
    public static void main(String[] args) {
        double durationHours = 2.5;
        // Ép kiểu tường minh từ double sang int
        int totalMinutes = (int) (durationHours * 60);
        
        System.out.println("Thời lượng: " + durationHours + " giờ tương đương " + totalMinutes + " phút");
    }
}
```

---

#### BÀI TẬP 1.2 (MỨC ĐỘ: DỄ - TRUNG BÌNH): KIỂM TRA ĐIỀU KIỆN ĐẶT LỊCH BR-04 BẰNG IF-ELSE
* **Đề bài**: Viết hàm `checkBookingDuration(int minutes)`. Dùng `if - else if - else`:
  * Nếu `minutes <= 0`: in ra `"LỖI: Thời lượng đặt phải lớn hơn 0"`.
  * Nếu `minutes > 240`: in ra `"LỖI: Thời lượng vượt quá 4 giờ (tối đa 240 phút)"`.
  * Ngược lại: in ra `"HỢP LỆ: Khung giờ đặt được chấp nhận"`.
* **Lời giải mẫu**:
```java
public class Exercise1_2 {
    public static void checkBookingDuration(int minutes) {
        if (minutes <= 0) {
            System.out.println("LỖI: Thời lượng đặt phải lớn hơn 0");
        } else if (minutes > 240) {
            System.out.println("LỖI: Thời lượng vượt quá 4 giờ (tối đa 240 phút)");
        } else {
            System.out.println("HỢP LỆ: Khung giờ đặt được chấp nhận (" + minutes + " phút)");
        }
    }

    public static void main(String[] args) {
        checkBookingDuration(0);   // Báo lỗi <= 0
        checkBookingDuration(300); // Báo lỗi > 240
        checkBookingDuration(120); // Báo hợp lệ
    }
}
```

---

#### BÀI TẬP 1.3 (MỨC ĐỘ: TRUNG BÌNH): PHÂN LOẠI MÃ LỖI HTTP BẰNG SWITCH EXPRESSION
* **Đề bài**: Viết hàm trả về thông báo lỗi tiếng Việt dựa trên mã HTTP status code (200, 201, 400, 403, 404, 409). Sử dụng cú pháp `switch expression` của Java mới.
* **Lời giải mẫu**:
```java
public class Exercise1_3 {
    public static String getErrorMessage(int httpStatusCode) {
        return switch (httpStatusCode) {
            case 200 -> "Thành công (OK)";
            case 201 -> "Tạo mới lượt đặt thành công (Created)";
            case 400 -> "Dữ liệu nhập không hợp lệ (Bad Request)";
            case 403 -> "Bạn không có quyền thực hiện thao tác này (Forbidden)";
            case 404 -> "Không tìm thấy thiết bị hoặc lượt đặt (Not Found)";
            case 409 -> "Xung đột lịch: Khung giờ này đã có người đặt trước (Conflict)";
            default  -> "Lỗi hệ thống không xác định: " + httpStatusCode;
        };
    }

    public static void main(String[] args) {
        System.out.println("409: " + getErrorMessage(409));
        System.out.println("403: " + getErrorMessage(403));
    }
}
```

---

#### BÀI TẬP 1.4 (MỨC ĐỘ: KHÁ): DUYỆT DANH SÁCH MÃ THIẾT BỊ BẰNG FOR-EACH, CONTINUE VÀ BREAK
* **Đề bài**: Cho mảng chuỗi mã thiết bị `String[] codes = {"EQ-OFF-01", "EQ-GPU-01", "EQ-OFF-02", "EQ-GPU-02", "EQ-CAM-01"};`.
  1. Duyệt qua mảng bằng `for-each`.
  2. Nếu mã chứa chữ `"OFF"` (máy hỏng/tắt), dùng `continue` bỏ qua không in.
  3. Nếu tìm thấy đúng mã `"EQ-GPU-02"`, in ra `"ĐÃ TÌM THẤY MÁY CHỦ GPU-02, DỪNG TÌM KIẾM!"` và dùng `break` dừng vòng lặp ngay lập tức.
* **Lời giải mẫu**:
```java
public class Exercise1_4 {
    public static void main(String[] args) {
        String[] codes = {"EQ-OFF-01", "EQ-GPU-01", "EQ-OFF-02", "EQ-GPU-02", "EQ-CAM-01"};

        for (String code : codes) {
            // Bỏ qua các máy có tiền tố hoặc chứa OFF (máy bảo trì)
            if (code.contains("OFF")) {
                System.out.println("-> Bỏ qua máy ngừng hoạt động: " + code);
                continue;
            }

            System.out.println("Kiểm tra thiết bị: " + code);

            if (code.equals("EQ-GPU-02")) {
                System.out.println("=> ĐÃ TÌM THẤY MÁY CHỦ GPU-02, DỪNG TÌM KIẾM!");
                break;
            }
        }
    }
}
```

---

#### BÀI TẬP 1.5 (MỨC ĐỘ: KHÓ): MÔ PHỎNG VÒNG LẶP RETRY KẾT NỐI DATABASE BẰNG WHILE
* **Đề bài**: Viết hàm `boolean connectToPostgreSql()` mô phỏng việc kết nối CSDL PostgreSQL với cơ chế Retry tối đa 3 lần bằng vòng lặp `while`. Mỗi lần thử thất bại, tăng số lần thử lên 1 và chờ tăng dần (exponential backoff). Giả lập lần thứ 3 thì kết nối thành công.
* **Lời giải mẫu**:
```java
public class Exercise1_5 {
    public static boolean connectToPostgreSql() {
        int maxRetries = 3;
        int attempt = 1;
        boolean connected = false;

        while (attempt <= maxRetries && !connected) {
            System.out.println("Đang thử kết nối PostgreSQL cổng 5432 (Lần thử " + attempt + "/" + maxRetries + ")...");
            
            // Giả lập logic: Chỉ lần thứ 3 mới kết nối thành công
            if (attempt == 3) {
                connected = true;
                System.out.println("==> Kết nối CSDL lab_equipment_db THÀNH CÔNG!");
            } else {
                System.out.println("Lỗi kết nối tạm thời. Đợi " + (attempt * 500) + "ms trước khi thử lại...");
                try {
                    Thread.sleep(attempt * 500L); // Tạm dừng
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                attempt++;
            }
        }
        return connected;
    }

    public static void main(String[] args) {
        boolean result = connectToPostgreSql();
        System.out.println("Trạng thái cuối cùng: " + (result ? "SẴN SÀNG CHẠY SERVER" : "KHỞI ĐỘNG THẤT BẠI"));
    }
}
```

---

# PHẦN 2: LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG (OOP) TRONG SPRING BOOT

---

### 2.1. Access Modifiers (Phạm vi truy cập)
* `private`: Chỉ trong cùng 1 Class. Dùng cho **100% thuộc tính của Entity và DTO**.
* `public`: Bất kỳ đâu cũng gọi được. Dùng cho Controller, Service Method.
* `protected`: Trong cùng Package và các Class con kế thừa. Dùng cho lớp cha `BaseEntity`.

### 2.2. Từ khóa `this` và `super`
* `this`: Đại diện cho chính đối tượng hiện tại (`this.name = name`).
* `super`: Gọi Constructor hoặc phương thức của lớp cha (`super(id, createdAt)`).

### 2.3. Bốn trụ cột OOP
1. **Đóng gói (Encapsulation)**: Thuộc tính `private`, truy xuất qua Getter/Setter có kiểm tra logic.
2. **Kế thừa (Inheritance)**: Lớp cha `BaseEntity` chứa các cột chung (`id`, `createdAt`), lớp con `Equipment extends BaseEntity`.
3. **Đa hình (Polymorphism)**:
   * *Overloading (Nạp chồng)*: Cùng tên hàm nhưng khác tham số đầu vào.
   * *Overriding (Ghi đè)*: `@Override` định nghĩa lại hành vi của hàm lớp cha/interface.
4. **Trừu tượng (Abstraction)**: Dùng `Interface` định nghĩa hợp đồng nghiệp vụ (`BookingService`).

---

## 🎯 5 BÀI TẬP THỰC HÀNH PHẦN 2 (TỪ DỄ ĐẾN KHÓ)

---

#### BÀI TẬP 2.1 (MỨC ĐỘ: DỄ - TÍNH ĐÓNG GÓI): CLASS USER VÀ BẢO VỆ DỮ LIỆU
* **Đề bài**: Tạo class `User` với các trường `private`: `id` (Long), `username` (String), `passwordHash` (String). Viết Getter/Setter. Trong hàm `setUsername(String username)`, nếu tên rỗng hoặc ít hơn 3 ký tự thì bắn lỗi `IllegalArgumentException`.
* **Lời giải mẫu**:
```java
public class Exercise2_1 {
    public static class User {
        private Long id;
        private String username;
        private String passwordHash;

        public User(Long id, String username, String passwordHash) {
            this.id = id;
            setUsername(username); // Gọi qua setter để kiểm tra
            this.passwordHash = passwordHash;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getUsername() { return username; }
        public void setUsername(String username) {
            if (username == null || username.trim().length() < 3) {
                throw new IllegalArgumentException("Username không được để trống và phải có ít nhất 3 ký tự");
            }
            this.username = username.trim();
        }

        public String getPasswordHash() { return passwordHash; }
        public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    }

    public static void main(String[] args) {
        User u = new User(1L, "quocbao", "$2a$10$hashed_password");
        System.out.println("Tạo User thành công: " + u.getUsername());

        try {
            u.setUsername("ab"); // Quá ngắn -> Phải ném ngoại lệ
        } catch (IllegalArgumentException e) {
            System.out.println("Bắt lỗi đóng gói thành công: " + e.getMessage());
        }
    }
}
```

---

#### BÀI TẬP 2.2 (MỨC ĐỘ: TRUNG BÌNH - TÍNH KẾ THỪA): LỚP CHA BASEENTITY VÀ EQUIPMENT
* **Đề bài**: Tạo lớp trừu tượng `abstract class BaseEntity` có thuộc tính `protected Long id;` và `protected String createdAt;`. Tạo lớp con `Equipment extends BaseEntity` có thêm `code` và `name`. Dùng `super` để khởi tạo thuộc tính của lớp cha.
* **Lời giải mẫu**:
```java
public class Exercise2_2 {
    public static abstract class BaseEntity {
        protected Long id;
        protected String createdAt;

        public BaseEntity(Long id, String createdAt) {
            this.id = id;
            this.createdAt = createdAt;
        }

        public Long getId() { return id; }
        public String getCreatedAt() { return createdAt; }
    }

    public static class Equipment extends BaseEntity {
        private String code;
        private String name;

        public Equipment(Long id, String createdAt, String code, String name) {
            super(id, createdAt); // Gọi constructor của BaseEntity
            this.code = code;
            this.name = name;
        }

        public String getCode() { return code; }
        public String getName() { return name; }
    }

    public static void main(String[] args) {
        Equipment eq = new Equipment(10L, "2026-10-19 14:00:00", "EQ-GPU-01", "Máy trạm RTX 4090");
        System.out.println("ID (từ BaseEntity): " + eq.getId());
        System.out.println("Ngày tạo (từ BaseEntity): " + eq.getCreatedAt());
        System.out.println("Mã thiết bị: " + eq.getCode());
    }
}
```

---

#### BÀI TẬP 2.3 (MỨC ĐỘ: KHÁ - ĐA HÌNH & GHI ĐÈ @OVERRIDE): THÔNG BÁO LỊCH ĐẶT
* **Đề bài**: Tạo Interface `NotificationService` có hàm `void send(String recipient, String message)`. Tạo 2 class thực thi:
  1. `EmailNotificationService`: In ra `"[EMAIL gửi đến " + recipient + "]: " + message`.
  2. `ConsoleNotificationService`: In ra `"[CONSOLE LOG]: " + message`.
  Viết hàm `notifyUser(NotificationService service, String to, String msg)` để thể hiện tính đa hình.
* **Lời giải mẫu**:
```java
public class Exercise2_3 {
    public interface NotificationService {
        void send(String recipient, String message);
    }

    public static class EmailNotificationService implements NotificationService {
        @Override
        public void send(String recipient, String message) {
            System.out.println("[EMAIL gửi đến " + recipient + "]: " + message);
        }
    }

    public static class ConsoleNotificationService implements NotificationService {
        @Override
        public void send(String recipient, String message) {
            System.out.println("[CONSOLE LOG]: Gửi cho " + recipient + " -> " + message);
        }
    }

    public static void notifyUser(NotificationService service, String to, String msg) {
        service.send(to, msg); // Tính đa hình: Tùy đối tượng truyền vào mà hàm tương ứng chạy
    }

    public static void main(String[] args) {
        NotificationService email = new EmailNotificationService();
        NotificationService console = new ConsoleNotificationService();

        notifyUser(email, "bao@lab.vn", "Lượt đặt #101 đã được xác nhận");
        notifyUser(console, "manager_lab", "Cảnh báo máy GPU bảo trì");
    }
}
```

---

#### BÀI TẬP 2.4 (MỨC ĐỘ: KHÁ - RECORD & DTO BẢO VỆ PRIVACY BR-13): TÁCH BIỆT DTO THEO VAI TRÒ
* **Đề bài**: Tạo Entity `BookingData` có thông tin: `id`, `userName`, `purpose`, `timeSlot`. Tạo 2 Record DTO:
  * `PublicSlotResponse(String timeSlot)` (Dành cho Member).
  * `AdminBookingResponse(Long id, String userName, String purpose, String timeSlot)` (Dành cho Manager).
  Viết hàm chuyển đổi trả về đúng DTO theo vai trò.
* **Lời giải mẫu**:
```java
public class Exercise2_4 {
    public enum Role { MEMBER, MANAGER }

    public record BookingData(Long id, String userName, String purpose, String timeSlot) {}
    
    // DTO cho Member (Tuyệt đối giấu tên và mục đích)
    public record PublicSlotResponse(String timeSlot) {}

    // DTO cho Manager (Đầy đủ)
    public record AdminBookingResponse(Long id, String userName, String purpose, String timeSlot) {}

    public static Object convertToDto(BookingData data, Role callerRole) {
        if (callerRole == Role.MANAGER) {
            return new AdminBookingResponse(data.id(), data.userName(), data.purpose(), data.timeSlot());
        }
        return new PublicSlotResponse(data.timeSlot());
    }

    public static void main(String[] args) {
        BookingData sample = new BookingData(101L, "Nguyễn Quốc Bảo", "Train YOLOv8", "10:00 - 12:00");

        Object forMember = convertToDto(sample, Role.MEMBER);
        System.out.println("Member nhìn thấy: " + forMember); // Không có tên người đặt!

        Object forManager = convertToDto(sample, Role.MANAGER);
        System.out.println("Manager nhìn thấy: " + forManager); // Có đầy đủ tên và mục đích
    }
}
```

---

#### BÀI TẬP 2.5 (MỨC ĐỘ: KHÓ - TỔNG HỢP OOP & BUSINESS RULES): QUẢN LÝ HỦY LỊCH BR-07 & BR-08
* **Đề bài**: Thiết kế hệ thống hủy lịch gồm Interface `BookingManager` và class `BookingManagerImpl`:
  * Nếu người hủy là Member: Chỉ được hủy lịch do chính mình tạo (nếu hủy của người khác -> ném `SecurityException`).
  * Nếu người hủy là Manager: Được hủy bất kỳ lịch nào, nhưng **bắt buộc phải có lý do** (nếu lý do rỗng -> ném `IllegalArgumentException`).
  * Lịch đã hủy chuyển trạng thái sang `CANCELLED`.
* **Lời giải mẫu**:
```java
public class Exercise2_5 {
    public enum Role { MEMBER, MANAGER }
    public enum Status { CONFIRMED, CANCELLED }

    public static class Booking {
        private Long id;
        private Long userId;
        private Status status = Status.CONFIRMED;
        private String reason;

        public Booking(Long id, Long userId) {
            this.id = id;
            this.userId = userId;
        }

        public Long getId() { return id; }
        public Long getUserId() { return userId; }
        public Status getStatus() { return status; }
        public void setStatus(Status status) { this.status = status; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }

    public interface BookingManager {
        void cancelBooking(Booking booking, Long callerId, Role callerRole, String reason);
    }

    public static class BookingManagerImpl implements BookingManager {
        @Override
        public void cancelBooking(Booking booking, Long callerId, Role callerRole, String reason) {
            if (callerRole == Role.MEMBER) {
                // BR-07: Member chỉ được hủy lịch của mình
                if (!booking.getUserId().equals(callerId)) {
                    throw new SecurityException("Từ chối: Bạn không thể hủy lịch của thành viên khác!");
                }
            } else if (callerRole == Role.MANAGER) {
                // BR-08: Manager hủy bắt buộc phải có lý do
                if (reason == null || reason.trim().isEmpty()) {
                    throw new IllegalArgumentException("Từ chối: Quản trị viên hủy lịch bắt buộc phải nhập lý do!");
                }
                booking.setReason(reason.trim());
            }

            booking.setStatus(Status.CANCELLED);
            System.out.println("Hủy lịch #" + booking.getId() + " thành công!");
        }
    }

    public static void main(String[] args) {
        BookingManager manager = new BookingManagerImpl();
        Booking b1 = new Booking(101L, 1L); // Lịch của User 1

        // Test 1: User 2 (Member) cố hủy lịch của User 1 -> Phải bị chặn
        try {
            manager.cancelBooking(b1, 2L, Role.MEMBER, null);
        } catch (SecurityException e) {
            System.out.println("Test 1 Passed: " + e.getMessage());
        }

        // Test 2: Manager hủy lịch nhưng quên nhập lý do -> Phải bị chặn
        try {
            manager.cancelBooking(b1, 99L, Role.MANAGER, "   ");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 2 Passed: " + e.getMessage());
        }

        // Test 3: Manager hủy có lý do -> Thành công
        manager.cancelBooking(b1, 99L, Role.MANAGER, "Phòng Lab sửa chữa điện");
        System.out.println("Test 3 Passed: Trạng thái lịch = " + b1.getStatus() + ", Lý do = " + b1.getReason());
    }
}
```

---

# PHẦN 3: CẨM NANG THƯ VIỆN CHUẨN & BÀI TẬP TỪNG MỤC NHỎ

---

### 3.1. THƯ VIỆN `java.lang` (String, Objects, Wrapper)

| Lớp | Hàm thông dụng | Ý nghĩa & Ví dụ trong LEBS |
| :--- | :--- | :--- |
| `String` | `.equals(s)` | So sánh chuỗi có phân biệt hoa thường: `"CONFIRMED".equals(status)` |
| `String` | `.equalsIgnoreCase(s)` | So sánh không phân biệt hoa thường: `role.equalsIgnoreCase("manager")` |
| `String` | `.contains(sub)` | Kiểm tra chuỗi con: `name.toLowerCase().contains("rtx")` |
| `String` | `.isBlank()` | `true` nếu chuỗi rỗng hoặc toàn khoảng trắng: `purpose.isBlank()` |
| `String` | `.trim()` | Cắt khoảng trắng 2 đầu chuỗi: `reason.trim()` |
| `String` | `.substring(a, b)` | Cắt chuỗi từ index `[a, b)`: `"EQ-GPU-01".substring(0, 2) // "EQ"` |
| `Objects`| `Objects.equals(a, b)` | So sánh an toàn 2 giá trị dù có `null`: `Objects.equals(booking.getUserId(), uid)` |
| `Long`   | `Long.parseLong(str)`  | Chuyển chuỗi sang số Long: `Long.parseLong("105")` |

#### 🎯 BÀI TẬP 3.1: CHUẨN HÓA MỤC ĐÍCH SỬ DỤNG VÀ SO SÁNH AN TOÀN USER ID
* **Đề bài**: Viết hàm `boolean validateAndCompare(String rawPurpose, Long bookingUserId, Long currentUserId)`:
  1. Kiểm tra `rawPurpose` không được `isBlank()`. Nếu rỗng bắn ngoại lệ `"Mục đích không được để trống"`.
  2. Cắt khoảng trắng thừa ở 2 đầu `rawPurpose`. Nếu độ dài sau khi cắt `> 200` ký tự thì bắn ngoại lệ `"Mục đích tối đa 200 ký tự"`.
  3. Dùng `Objects.equals()` so sánh `bookingUserId` và `currentUserId`. Trả về `true` nếu trùng khớp (chính chủ), `false` nếu khác.
* **Lời giải mẫu**:
```java
import java.util.Objects;

public class Exercise3_1 {
    public static boolean validateAndCompare(String rawPurpose, Long bookingUserId, Long currentUserId) {
        if (rawPurpose == null || rawPurpose.isBlank()) {
            throw new IllegalArgumentException("Mục đích sử dụng không được để trống");
        }
        
        String cleanPurpose = rawPurpose.trim();
        if (cleanPurpose.length() > 200) {
            throw new IllegalArgumentException("Mục đích sử dụng tối đa 200 ký tự");
        }

        // So sánh an toàn tránh NullPointerException nếu 1 trong 2 là null
        return Objects.equals(bookingUserId, currentUserId);
    }

    public static void main(String[] args) {
        boolean isOwner = validateAndCompare("Nghiên cứu thị giác máy tính", 100L, 100L);
        System.out.println("Chính chủ đặt lịch: " + isOwner); // true
    }
}
```

---

### 3.2. THƯ VIỆN `java.util` (List, Map, Optional)

```java
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
```

* `List<T>`: `.add()`, `.get(index)`, `.size()`, `List.of(...)`.
* `Map<K, V>`: `.put(k, v)`, `.get(k)`, `.getOrDefault(k, defaultVal)`, `.containsKey(k)`.
* `Optional<T>`: `.isPresent()`, `.isEmpty()`, `.orElseThrow(() -> new AppException(...))`.

#### 🎯 BÀI TẬP 3.2: TRA CỨU THIẾT BỊ BẰNG MAP VÀ OPTIONAL.ORELSETHROW
* **Đề bài**: Tạo `Map<String, String>` lưu danh mục thiết bị dạng `code -> name`. Viết hàm `String findDeviceName(String code)` trả về `Optional<String>`. Ở hàm `main`, gọi hàm và dùng `.orElseThrow()` để ném ngoại lệ nếu không tìm thấy mã thiết bị.
* **Lời giải mẫu**:
```java
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Exercise3_2 {
    private static final Map<String, String> deviceTable = new HashMap<>();

    static {
        deviceTable.put("EQ-GPU-01", "Máy trạm RTX 4090");
        deviceTable.put("EQ-CAM-01", "Camera Basler");
    }

    public static Optional<String> findDeviceName(String code) {
        return Optional.ofNullable(deviceTable.get(code));
    }

    public static void main(String[] args) {
        // Case 1: Tìm thấy
        String name = findDeviceName("EQ-GPU-01")
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thiết bị!"));
        System.out.println("Tìm thấy: " + name);

        // Case 2: Không tìm thấy -> Bắn lỗi chuẩn mực Spring Boot
        try {
            findDeviceName("EQ-UNKNOWN")
                    .orElseThrow(() -> new RuntimeException("LỖI 404: Thiết bị không tồn tại trong hệ thống!"));
        } catch (RuntimeException e) {
            System.out.println("Bắt lỗi thành công: " + e.getMessage());
        }
    }
}
```

---

### 3.3. THƯ VIỆN `java.util.stream` (Stream API)

* `.filter(predicate)`: Lọc theo điều kiện.
* `.map(function)`: Biến đổi phần tử.
* `.toList()`: Thu gom thành List.
* `.anyMatch(predicate)`: Có phần tử nào khớp không.
* `Collectors.joining(", ")`: Nối thành chuỗi.

#### 🎯 BÀI TẬP 3.3: LỌC THIẾT BỊ ACTIVE VÀ NỐI TÊN THÀNH CHUỖI
* **Đề bài**: Cho danh sách 4 thiết bị. Dùng Stream API:
  1. Lọc ra các thiết bị có `isActive == true`.
  2. Lấy ra danh sách tên thiết bị.
  3. Nối tên các thiết bị active thành 1 chuỗi duy nhất, cách nhau bằng dấu phẩy `", "`.
* **Lời giải mẫu**:
```java
import java.util.List;
import java.util.stream.Collectors;

public class Exercise3_3 {
    public record Equipment(String code, String name, boolean active) {}

    public static void main(String[] args) {
        List<Equipment> list = List.of(
            new Equipment("EQ-01", "Máy RTX 4090", true),
            new Equipment("EQ-02", "Máy in 3D (Hỏng)", false),
            new Equipment("EQ-03", "Máy hiện sóng Rigol", true),
            new Equipment("EQ-04", "Server A100", true)
        );

        String activeDeviceNames = list.stream()
                .filter(Equipment::active)             // Lọc active == true
                .map(Equipment::name)                 // Lấy ra tên
                .collect(Collectors.joining(", "));   // Nối chuỗi

        System.out.println("Các thiết bị đang sẵn sàng: " + activeDeviceNames);
        // Output: Máy RTX 4090, Máy hiện sóng Rigol, Server A100
    }
}
```

---

### 3.4. THƯ VIỆN THỜI GIAN `java.time`

* `LocalDateTime`: Quản lý ngày giờ (`now()`, `of()`, `isBefore()`, `isAfter()`).
* `Duration` / `ChronoUnit`: Đo khoảng cách thời gian (`ChronoUnit.MINUTES.between(...)`).
* Công thức xung đột nửa mở `[start, end)`: `startA < endB && startB < endA`.

#### 🎯 BÀI TẬP 3.4: THUẬT TOÁN KIỂM TRA XUNG ĐỘT VÀ TÍNH THỜI LƯỢNG
* **Đề bài**: Viết hàm `boolean isConflict(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB)`. Kiểm tra với 2 ca:
  * Ca 1: `A = 09:00 - 10:00`, `B = 10:00 - 11:00` (Tiếp giáp biên -> Không xung đột, AT-04).
  * Ca 2: `A = 09:00 - 10:30`, `B = 10:00 - 11:00` (Trùng 30 phút -> Báo xung đột, AT-03).
* **Lời giải mẫu**:
```java
import java.time.LocalDateTime;

public class Exercise3_4 {
    public static boolean isConflict(LocalDateTime startA, LocalDateTime endA,
                                     LocalDateTime startB, LocalDateTime endB) {
        // startA < endB VÀ startB < endA
        return startA.isBefore(endB) && startB.isBefore(endA);
    }

    public static void main(String[] args) {
        LocalDateTime base = LocalDateTime.of(2026, 11, 2, 0, 0);

        // Ca 1: Tiếp giáp (09:00-10:00 và 10:00-11:00)
        boolean conflict1 = isConflict(base.plusHours(9), base.plusHours(10),
                                       base.plusHours(10), base.plusHours(11));
        System.out.println("Ca 1 (Tiếp giáp) có xung đột không: " + conflict1); // false (Chính xác!)

        // Ca 2: Trùng lấn (09:00-10:30 và 10:00-11:00)
        boolean conflict2 = isConflict(base.plusHours(9), base.plusHours(10).plusMinutes(30),
                                       base.plusHours(10), base.plusHours(11));
        System.out.println("Ca 2 (Trùng 30p) có xung đột không: " + conflict2); // true (Chính xác!)
    }
}
```

---

### 3.5. THƯ VIỆN BEAN VALIDATION (`jakarta.validation.constraints`)

* `@NotNull`: Không được null.
* `@NotBlank`: Chuỗi không rỗng và không khoảng trắng.
* `@Size(min, max)`: Giới hạn độ dài.
* `@Future`: Bắt buộc ở tương lai.

#### 🎯 BÀI TẬP 3.5: ĐỊNH NGHĨA DTO BOOKING VỚI ĐẦY ĐỦ ANNOTATION RÀNG BUỘC
* **Đề bài**: Viết class DTO `BookingCreationRequest` dùng các annotation của `jakarta.validation` để đáp ứng toàn bộ các yêu cầu của PRD:
  * `equipmentId`: Không được null.
  * `startTime`: Bắt buộc ở tương lai, không được null.
  * `endTime`: Không được null.
  * `purpose`: Không được để trống, độ dài từ 1 đến 200 ký tự.
* **Lời giải mẫu**:
```java
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class Exercise3_5 {
    public static class BookingCreationRequest {
        @NotNull(message = "Thiết bị không được để trống")
        private Long equipmentId;

        @NotNull(message = "Thời gian bắt đầu không được để trống")
        @Future(message = "Thời gian bắt đầu phải ở tương lai")
        private LocalDateTime startTime;

        @NotNull(message = "Thời gian kết thúc không được để trống")
        private LocalDateTime endTime;

        @NotBlank(message = "Mục đích sử dụng không được để trống")
        @Size(min = 1, max = 200, message = "Mục đích sử dụng tối đa 200 ký tự")
        private String purpose;

        // Constructor, Getter, Setter...
        public Long getEquipmentId() { return equipmentId; }
        public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }
        public LocalDateTime getStartTime() { return startTime; }
        public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
        public LocalDateTime getEndTime() { return endTime; }
        public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
        public String getPurpose() { return purpose; }
        public void setPurpose(String purpose) { this.purpose = purpose; }
    }

    public static void main(String[] args) {
        System.out.println("DTO BookingCreationRequest đã được định nghĩa chuẩn xác với Bean Validation!");
    }
}
```

---

### 3.6. LOMBOK & SPRING DATA JPA REPOSITORIES

* Lombok: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`.
* JpaRepository: `findById()`, `save()`, `existsByCode()`.
* Pessimistic Lock: `@Lock(LockModeType.PESSIMISTIC_WRITE)` khóa dòng thiết bị trong CSDL.

#### 🎯 BÀI TẬP 3.6: VIẾT REPOSITORY KHÓA DÒNG THIẾT BỊ CHỐNG ĐẶT TRÙNG (AT-05)
* **Đề bài**: Viết Interface `EquipmentRepository` kế thừa `JpaRepository<Equipment, Long>`:
  1. Hàm kiểm tra tồn tại mã thiết bị: `boolean existsByCode(String code)`.
  2. Hàm tìm thiết bị theo ID và **khóa dòng chống tranh chấp đồng thời bằng Pessimistic Lock** (`SELECT ... FOR UPDATE`).
* **Lời giải mẫu**:
```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.util.Optional;

public class Exercise3_6 {
    // Giả định Entity Equipment
    public static class Equipment {
        private Long id;
        private String code;
    }

    // Interface Repository chuẩn mực cho dự án LEBS
    public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
        
        // 1. Kiểm tra mã thiết bị đã tồn tại chưa (tránh trùng mã)
        boolean existsByCode(String code);

        // 2. KHÓA BẢN GHI THIẾT BỊ BẰNG PESSIMISTIC LOCK ĐỂ GIẢI BÀI TOÁN CONCURRENCY AT-05
        @Lock(LockModeType.PESSIMISTIC_WRITE)
        @Query("SELECT e FROM Equipment e WHERE e.id = :id")
        Optional<Equipment> findByIdForUpdate(@Param("id") Long id);
    }

    public static void main(String[] args) {
        System.out.println("Repository định nghĩa hoàn hảo cơ chế Pessimistic Lock (SELECT ... FOR UPDATE)!");
    }
}
```
