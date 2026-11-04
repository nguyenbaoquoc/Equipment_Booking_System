# QUY TRINH XAC THUC MAT KHAU VA TAO JWT TOKEN (AUTHENTICATION & JWT GENERATION)

Tai lieu huong dan chi tiet va kich ban thuyet trinh he thong Authentication trong du an **Lab Equipment Booking System**.

---

## 1. TONG QUAN KIEN TRUC (HIGH-LEVEL OVERVIEW)

He thong bao mat duoc thiet ke dua tren hai tru cot cot loi:
1. **Bao mat mat khau nguoi dung:** Su dung thuat toan bam mot chieu **BCrypt** de luu tru trong Database, tuyet doi khong luu plain text.
2. **Co che phan quyen va duy tri phien:** Su dung **JWT (JSON Web Token)** khong trang thai (Stateless), giup he thong xac thuc nhe nhang ma khong can luu Session trong RAM cua Server.

---

## 2. GIAI DOAN 1: DANG KY VA MA HOA MAT KHAU (REGISTRATION FLOW)

### 2.1. Quy trinh xu ly
1. Client gui thong tin nguoi dung qua API `POST /users` kem mat khau dang tho (Plain Text).
2. `UserService` kiem tra ten dang nhap (`username`) da ton tai hay chua.
3. Truoc khi luu vao MySQL, mat khau duoc truyen qua `BCryptPasswordEncoder(10)` de bam.
4. Chuoi hash (vi du: `$2a$10$wK1...`) duoc gan vao Entity `User` va ghi vao bang `users`.

### 2.2. Tai sao lai dung BCrypt?
- **Ma hoa mot chieu (One-way Hash):** Tu chuoi bam `$2a$10$...`, khong the giai ma nguoc ve chuoi mat khau ban dau.
- **Tu dong them Muoi (Salt):** Moi lan bam mot mat khau giong nhau (vi du `"Password123"`), BCrypt se tu sinh ra mot chuoi ngau nhien (Salt) khac nhau. Do do, hai nguoi dung co cung mat khau thi chuoi hash trong Database van hoan toan khac nhau.
- **Chong tan cong Tu dien va Rainbow Table:** Hacker du chiem duoc Database cung khong the tra nguoc mat khau.
- **Cost Factor (Log Rounds = 10):** Tang do kho tinh toan, lam cham toc do do mat khau (Brute Force) cua ke tan cong.

---

## 3. GIAI DOAN 2: XAC THUC MAT KHAU (AUTHENTICATION FLOW)

Khi nguoi dung dang nhap qua API `POST /auth/token`:

```text
Client (Postman/Web)
        |
        |  1. POST /auth/token {username, password}
        v
AuthenticationController
        |
        |  2. Goi authenticate(request)
        v
AuthenticationService
        |
        |  3. Tim User theo username
        v
UserRepository (Database MySQL)
        |
        |  4. Tra ve UserEntity (chua password da bam trong DB)
        v
AuthenticationService
        |
        |  5. passwordEncoder.matches(rawPassword, encodedPassword)
        |     - Sai: Nem AppException(ErrorCode.UNAUTHENTICATED) -> Tra ve 401
        |     - Dung: Chuyen sang buoc tao Token
        v
Tao JWT Token bang Nimbus JOSE JWT
        |
        |  6. Tra ve AuthenticationResponse {token, authenticated: true}
        v
Client nhan ket qua
```

### 3.1. Co che hoat dong cua ham `matches()`
Nhieu nguoi nghi server se giai ma mat khau trong DB ra de so sanh, nhung thuc te:
- Ham `matches(rawPassword, encodedPassword)` se boc tach chuoi **Salt** da duoc giau ben trong chuoi `encodedPassword` o Database.
- Dung dung chuoi Salt do de bam mat khau `rawPassword` ma nguoi dung vua go vao.
- Neu chuoi hash vua tao ra trung voi chuoi hash dang luu o Database -> Xac nhan dung mat khau -> Tra ve `true`.

---

## 4. GIAI DOAN 3: KHOI TAO VA KY JWT TOKEN (JWT TOKEN GENERATION)

Token JWT duoc tao boi thu vien chuyen dung `nimbus-jose-jwt` theo chuan RFC 7519, gom 3 phan ngan cach nhau boi dau cham (`.`):

```text
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXBiYW8iLCJpc3MiOiJlcXVpcG1lbnRib29raW5nIiwiZXhwIjoxNzkzNTQ...etc.s9f_...
[--- 1. HEADER ---] . [------------------ 2. PAYLOAD -------------------] . [-- 3. SIGNATURE --]
```

### 4.1. Phan 1: Header (Phan dau)
- Khai bao thuat toan ma hoa chu ky so: `JWSAlgorithm.HS512` (HMAC SHA-512).
- Thong bao cho he thong biet cach thuc de kiem tra chu ky token.

### 4.2. Phan 2: Payload / Claims (Noi dung du lieu)
Chua cac thong tin xac thuc cua nguoi dung:
- `sub` (Subject): Ten dang nhap cua nguoi dung (`username`).
- `iss` (Issuer): Don vi phat hanh token (`equipmentbooking`).
- `iat` (Issued At): Thoi diem sinh ra token.
- `exp` (Expiration Time): Thoi diem het han cua token (he thong hien tai set la 1 gio ke tu luc dang nhap).

### 4.3. Phan 3: Signature (Chu ky so bao ve token)
- Server su dung mot khoa bi mat goi la **`SIGNER_KEY`** (chuoi 256 bits).
- Thuat toan HMAC SHA-512 se ket hop:
  ```text
  Signature = HMACSHA512(Base64URL(Header) + "." + Base64URL(Payload), SIGNER_KEY)
  ```
- **Y nghia cuc ky quan trong:** Signature dam bao tinh toan ven (Integrity). Neu Hacker co y dinh sua doi thong tin ben trong Payload (vi du sua username thanh nguoi khac), chu ky se khong con khop voi `SIGNER_KEY` cua Server -> Token bi vo hieu hoa ngay lap tuc.

---

## 5. DANG KY VA DANG NHAP THUC TE (POSTMAN TESTING)

### 1. Tao nguoi dung moi (Hash Password)
- **URL:** `POST http://localhost:8080/users`
- **Body:**
```json
{
  "username": "sepbao",
  "password": "Password123",
  "fullName": "Nguyen Quoc Bao",
  "role": "MANAGER"
}
```
- **Ket qua:** Luu vao MySQL voi mat khau băm `$2a$10$...`.

### 2. Dang nhap lay Token (Match Password & Generate Token)
- **URL:** `POST http://localhost:8080/auth/token`
- **Body:**
```json
{
  "username": "sepbao",
  "password": "Password123"
}
```
- **Ket qua:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXBiYW8i...",
  "authenticated": true
}
```

---

## 6. KICH BAN THUYET TRINH GOI Y CHO SEP BAO (PRESENTATION SCRIPT)

Khi thuyet trinh truoc thay co hoac ban be, sep co the trinh bay theo 4 y ngan gon sau:

1. **Mo dau:** 
   "Em xin phep trinh bay co che Bao mat va Xac thuc nguoi dung trong du an. De dam bao chuan bao mat hien dai, du an cua em ket hop 2 thanh phan la: BCrypt de bao ve mat khau va JWT de quan ly phien dang nhap."
2. **Ve ma hoa mat khau:** 
   "Khi nguoi dung tao tai khoan, he thong khong bao gio luu mat khau goc. He thong su dung BCrypt de bam mat khau kem Salt ngau nhien. Dieu nay dam bao du co bi lo Database thi hacker cung khong the dich nguoc duoc mat khau."
3. **Ve co che xac thuc (Authentication):** 
   "Khi dang nhap, nguoi dung gui username va password len API /auth/token. He thong lay chuoi hash trong DB ra va dung ham matches() cua BCrypt de so sanh. Neu sai se bi chan va tra ve loi 401 Unauthorized."
4. **Ve JWT Token:** 
   "Khi mat khau chinh xac, Service su dung thu vien Nimbus JOSE JWT de ky mot token voi thuat toan HS512 va khoa bi mat Signer Key. Token nay chua thong tin username, han su dung trong 1 gio. Client se dung token nay de gan vao Authorization Header trong cac request tiep theo de thuc hien cac chuc nang cua he thong."

