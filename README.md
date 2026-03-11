# 📚 Library Management System API

A **Spring Boot REST API** for managing a library system.  
The system allows administrators and users to manage books, members, borrowing transactions, and fines.

It also integrates with the **Google Books API** to import books automatically into the library database.

This project demonstrates backend development using:

- Spring Boot
- REST APIs
- JWT Authentication
- MySQL Database
- Swagger API documentation
- Cloud deployment

---

# 🚀 Live API

Swagger Documentation:

https://library-api-42m4.onrender.com/swagger-ui/index.html

You can test all API endpoints directly from Swagger.

---

# 🛠 Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-based authentication |
| Spring Data JPA (Hibernate) | ORM |
| MySQL | Relational Database |
| Swagger / OpenAPI | API Documentation |
| Maven | Build Tool |
| Render | Cloud Deployment |

---

# 🏗 System Architecture

```
Client
   ↓
Controller (REST APIs)
   ↓
Service Layer (Business Logic)
   ↓
Repository Layer (Spring Data JPA)
   ↓
MySQL Database
```

The project follows a **layered backend architecture**.

| Layer | Responsibility |
|---|---|
| Controller | Handles HTTP requests |
| Service | Business logic |
| Repository | Database operations |
| Entity | Database models |
| DTO | Data transfer objects |

---

# 🔐 Authentication

The API uses **JWT (JSON Web Token)** for authentication.

### Login

```
POST /auth/login
```

Request body:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Response:

```json
{
  "token": "JWT_TOKEN_HERE"
}
```

Use the token for protected endpoints:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# 📚 Book APIs

### Get all books

```
GET /books
```

Returns paginated list of books.

---

### Search books

```
GET /books/search?title=harry
```

Search books by title.

---

### Get book by ID

```
GET /books/{id}
```

---

### Add new book (ADMIN)

```
POST /books
```

Request body:

```json
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "9780132350884",
  "availableCopies": 5
}
```

---

### Update book (ADMIN)

```
PUT /books/{id}
```

---

### Delete book (ADMIN)

```
DELETE /books/{id}
```

---

# 🌐 Google Books API Integration

Books can be fetched from **Google Books API**.

### Search Google Books

```
GET /books/google?title=harry potter
```

Example response:

```json
[
  {
    "title": "Harry Potter and the Sorcerer's Stone",
    "author": "J.K. Rowling",
    "isbn": "9781781100486"
  }
]
```

---

### Import books from Google

```
POST /books/import-google?title=harry potter
```

This will fetch books from Google Books and **save them in the database**.

---

# 🗄 Database Schema

Main tables used:

```
users
book
member
borrow
```

Example **book table**

| Column | Type |
|---|---|
| id | BIGINT |
| title | VARCHAR |
| author | VARCHAR |
| isbn | VARCHAR |
| availableCopies | INT |

---

# 📦 Features

✔ RESTful API design  
✔ JWT Authentication  
✔ Role-based authorization (ADMIN / USER)  
✔ Book inventory management  
✔ Search and pagination  
✔ Google Books API integration  
✔ Swagger API documentation  
✔ MySQL database integration  
✔ Cloud deployment

---

# ▶ Running Locally

### Clone repository

```
git clone https://github.com/Kavya-Sahu16/library-management-system.git
```

### Navigate to project

```
cd library-management-system
```

### Run application

```
mvn spring-boot:run
```

Server starts at:

```
http://localhost:8080
```

Swagger documentation:

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📷 API Documentation

Interactive API documentation is available through **Swagger UI**.

It allows testing endpoints such as:

- `/auth/login`
- `/books`
- `/books/search`
- `/books/import-google`

---

# 📈 Future Improvements

- Member management
- Borrow / return workflow
- Fine calculation
- Email notifications
- Admin dashboard

---

# 👩‍💻 Author

**Kavya Sahu**

Backend Developer | Java | Spring Boot

GitHub:

https://github.com/Kavya-Sahu16

---

⭐ If you found this project useful, consider giving it a star!
