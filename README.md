# 📝 Todo App Backend - Spring Boot REST API

## 🚀 Giới thiệu

Đây là dự án **Todo App Backend** được xây dựng bằng **Spring Boot**, cung cấp hệ thống **RESTful API** để quản lý công việc (Todo) với các chức năng như tạo mới, cập nhật, xoá, tìm kiếm, lọc, phân trang và xác thực người dùng bằng JWT.

Dự án được xây dựng nhằm mục tiêu luyện tập các kiến thức quan trọng trong phát triển backend Java hiện đại, bao gồm:

- CRUD API
- DTO Pattern
- Validation
- Pagination
- Filter & Search với Specification
- PATCH Update
- Global Exception Handling
- JWT Authentication
- Spring Security

---

## 🛠️ Công nghệ sử dụng

Dự án sử dụng các công nghệ sau:

- **Java 17**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **Hibernate**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Jakarta EE**
- **MySQL**
- **Maven**
- **Lombok** *(nếu có sử dụng trong project)*
- **Postman** *(dùng để test API)*

---

## 📂 Cấu trúc dự án
text com.example.todoapp ├── controller ├── service ├── repository ├── model ├── dto ├── mapper ├── specification ├── exception ├── security └── config

### Ý nghĩa các package:
- **controller**: Xử lý request từ client
- **service**: Chứa business logic
- **repository**: Tương tác với cơ sở dữ liệu
- **model**: Chứa entity của hệ thống
- **dto**: Dữ liệu truyền qua API
- **mapper**: Chuyển đổi giữa entity và DTO
- **specification**: Xử lý tìm kiếm / lọc động
- **exception**: Xử lý exception tập trung
- **security**: Cấu hình bảo mật và JWT

---

## ✨ Chức năng chính

### 1. Quản lý Todo
- Tạo công việc mới
- Xem danh sách công việc
- Cập nhật công việc
- Xoá công việc

### 2. Tìm kiếm và lọc
- Lọc theo trạng thái `completed`
- Tìm kiếm theo từ khóa trong `title` hoặc `description`

### 3. Phân trang
- Hỗ trợ phân trang dữ liệu bằng `page` và `size`

### 4. Xác thực người dùng
- Đăng ký tài khoản
- Đăng nhập để nhận JWT token
- Bảo vệ các API bằng JWT

### 5. Cập nhật một phần
- Hỗ trợ `PATCH` để chỉ cập nhật các field được gửi lên

---

## 📌 API Endpoints

### 🔐 Auth
| Method | Endpoint | Mô tả |
|--------|----------|------|
| POST | `/auth/register` | Đăng ký tài khoản |
| POST | `/auth/login` | Đăng nhập và nhận JWT |

### 📋 Todo
| Method | Endpoint | Mô tả |
|--------|----------|------|
| GET | `/todos` | Lấy danh sách todo |
| POST | `/todos` | Tạo todo mới |
| PATCH | `/todos/{id}` | Cập nhật một phần todo |
| DELETE | `/todos/{id}` | Xoá todo |

### 🔍 Ví dụ filter + search + pagination
http GET /todos?completed=true&keyword=java&page=0&size=5
``` 
---

## 🧪 Hướng dẫn sử dụng

### 1. Clone dự án
```

bash git clone <repository-url> cd todo-app```

### 2. Cấu hình cơ sở dữ liệu

Mở file cấu hình ứng dụng, ví dụ:
```

properties spring.datasource.url=jdbc:mysql://localhost:3306/todo_db spring.datasource.username=root spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect``` 

> Lưu ý: thay `your_password` bằng mật khẩu MySQL của bạn.

### 3. Tạo database
Tạo database trong MySQL:
```

sql CREATE DATABASE todo_db;```

### 4. Chạy ứng dụng

#### Cách 1: Dùng Maven
```

bash mvn spring-boot:run``` 

#### Cách 2: Chạy file jar
```

bash java -jar target/todo-app-0.0.1-SNAPSHOT.jar```

---

## 🔑 Cách dùng JWT

### Bước 1: Đăng ký tài khoản
Gửi request:
```

http POST /auth/register``` 

### Bước 2: Đăng nhập
Gửi request:
```

http POST /auth/login```

Sau khi đăng nhập thành công, API sẽ trả về **JWT token**.

### Bước 3: Gửi token khi gọi API bảo vệ
Thêm header:
```

http Authorization: Bearer <your_token>``` 

---

## 🧾 Ví dụ request

### Tạo Todo
```

json { "title": "Learn Spring Boot", "description": "Practice REST API", "completed": false }```

### Update Todo bằng PATCH
```

json { "title": "Learn Spring Boot Advanced" }``` 

> Chỉ những field được gửi lên mới được cập nhật.

---

## ⚠️ Lưu ý

- Các API ngoài `/auth/**` cần **JWT token**
- `PATCH` không cập nhật những field không được gửi
- Tìm kiếm keyword sử dụng cơ chế `LIKE`
- Dữ liệu đầu vào được kiểm tra bằng **Validation**
- Lỗi được xử lý tập trung qua **Global Exception Handling**

---

## 📈 Hướng phát triển tiếp theo

- Phân quyền theo vai trò `ADMIN` / `USER`
- Refresh Token
- Upload file
- Dockerize ứng dụng
- Tích hợp frontend React
- Logging và monitoring nâng cao

---

## 👨‍💻 Tác giả

Dự án được xây dựng với mục tiêu:

- Luyện tập phát triển backend Java
- Nâng cao kỹ năng xây dựng REST API
- Chuẩn bị cho vị trí **Java Backend Intern / Junior**

---

## 📄 License

Dự án phục vụ mục đích học tập và phát triển cá nhân.
```
