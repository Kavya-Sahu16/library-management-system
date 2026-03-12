# Library Management System API

A RESTful backend system for managing library operations such as books, members, borrowing, and returns.  
The system also integrates with the Google Books API to import books automatically.

This project is built using **Spring Boot**, **MySQL**, and **JWT Authentication**, and is deployed on the cloud.

---

## Live API

Base URL:

https://library-api-42m4.onrender.com

Swagger Documentation:

https://library-api-42m4.onrender.com/swagger-ui/index.html

---

## Features

- User authentication using JWT
- Role based access control (Admin, Librarian)
- Book inventory management
- Member management
- Borrow and return tracking
- Fine management
- Import books from Google Books API
- RESTful API design
- Swagger API documentation
- Cloud deployment

---

## Screenshots

### Swagger API Documentation
![Swagger UI]
<img width="1470" height="956" alt="Screenshot 2026-03-12 at 4 02 32 PM" src="https://github.com/user-attachments/assets/27a6546f-35be-4b04-aad8-318f3e19b3e2" />

<img width="1470" height="956" alt="Screenshot 2026-03-12 at 4 02 58 PM" src="https://github.com/user-attachments/assets/25c0f281-055a-4af7-9106-35d3b9ce6aeb" />


### Login API (JWT Authentication)
<img width="1470" height="956" alt="Screenshot 2026-03-12 at 4 09 48 PM" src="https://github.com/user-attachments/assets/0b1051c5-4d9b-4303-b58f-f02c97243fa7" />

<img width="1470" height="956" alt="Screenshot 2026-03-12 at 4 10 00 PM" src="https://github.com/user-attachments/assets/ddbffd60-f0e6-4c6f-bfd7-1550518b9dd4" />


### Import Books from Google Books API
<img width="1470" height="956" alt="Screenshot 2026-03-12 at 4 11 17 PM" src="https://github.com/user-attachments/assets/805f35e8-b0ac-46e4-a055-cdd8834ca308" />

<img width="1470" height="956" alt="Screenshot 2026-03-12 at 4 11 34 PM" src="https://github.com/user-attachments/assets/877b089c-03f2-49ca-9b21-f355c08e8717" />

### Books Stored in Database


## Tech Stack

Backend:
- Java
- Spring Boot
- Spring Security
- Spring Data JPA

Database:
- MySQL

Authentication:
- JWT (JSON Web Token)

Tools:
- Maven
- Docker
- Swagger (OpenAPI)

Deployment:
- Render

---

## Project Structure

```
src/main/java/com/kavya/library

controller     -> REST API endpoints  
service        -> Business logic  
repository     -> Database access layer  
entity         -> JPA entities  
dto            -> Data transfer objects  
security       -> JWT authentication and filters  
config         -> Application configuration  
```

---

## Authentication

The API uses **JWT (JSON Web Token)** for authentication.

### Login

POST /auth/login

Request Body

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Response

```json
{
  "token": "JWT_TOKEN"
}
```

Use the token for protected endpoints:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## API Endpoints

### Authentication

POST /auth/login  
Login and receive a JWT token.

---

### Books

GET /books  
Get all books.

GET /books/{id}  
Get book by ID.

POST /books  
Add a new book.

PUT /books/{id}  
Update book information.

DELETE /books/{id}  
Delete a book.

---

### Google Books Integration

POST /books/import-google?title={title}

Imports books from Google Books API and stores them in the database.

Example:

```
POST /books/import-google?title=harry potter
```

---

### Members

GET /members  
Get all members.

POST /members  
Add a new member.

---

### Borrow / Return

POST /borrow  
Borrow a book.

POST /return  
Return a borrowed book.

---

## Database Schema

Main tables:

- users
- book
- member
- borrow

Users table stores login credentials and roles.

---

## Running the Project Locally

Clone the repository

```
git clone https://github.com/Kavya-Sahu16/library-management-system.git
```

Navigate to the project directory

```
cd library-management-system
```

Run the application

```
mvn spring-boot:run
```

---

## Future Improvements

Possible enhancements:

- Pagination for book listing
- Global exception handling
- Logging with Spring Boot
- Unit testing
- Rate limiting for APIs
- Frontend interface

---

## Author

Kavya Sahu

GitHub:
https://github.com/Kavya-Sahu16

---

## License

This project is for educational purposes.
