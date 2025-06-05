# 📒 Address Book Application (Spring Boot)

A complete **Spring Boot CRUD REST API** to manage address records using **H2 database**, **JPA**, **DTO pattern**, **Lombok**, **input validations**, and **global exception handling**, with structured logs and data integrity checks.

---

## 🗂️ Folder Structure:
```
src/main/java
└── com.gevernova.addressbook
├── controller
│ └── AddressBookController.java
├── service
│ └── AddressBookService.java
├── entity
│ └── AddressBook.java
├── dto
│ ├── AddressBookRequestDTO.java
│ └── AddressBookResponseDTO.java
├── repository
│ └── AddressBookRepository.java
└── exception
├── GlobalExceptionHandler.java
└── EntryNotFoundException.java
```
---
## 🚀 Project Overview

This is a RESTful **Address Book Management API** that allows:

- Adding a new address (`POST`)
- Fetching address by ID (`GET`)
- Updating address details (`PUT`)
- Deleting address (`DELETE`)
- Fetching all addresses (`GET`)

The API uses:
- **H2 in-memory database**
- **DTO pattern** for clean architecture
- **Lombok** to minimize boilerplate
- **Jakarta Validation** to enforce request constraints
- **Custom exception handling**
- **SLF4J logs** for service tracking

---
## 🧰 Technologies Used

| Technology            | Description                                |
|-----------------------|--------------------------------------------|
| Spring Boot           | Backend framework                          |
| Spring Web            | For building REST APIs                     |
| Spring Data JPA       | ORM tool for database access               |
| H2 Database           | In-memory DB for development               |
| Lombok                | Reduces boilerplate code                   |
| Jakarta Validation    | Validates incoming request data            |
| SLF4J + Logback       | For logging support                        |
| Maven                 | Build and dependency management            |

---

## 🔧 How to Run

### Prerequisites:
- Java 17 or higher
- Maven installed
- IDE like IntelliJ or VS Code (optional)

### Running the Project

```bash
# Clone the repository
git clone https://github.com/yourusername/address-book-api.git

# Navigate into the project folder
cd address-book-api

# Run using Maven
mvn spring-boot:run
```
---
## 🔗 API Endpoints

| HTTP Method | Endpoint               | Description           |
|-------------|------------------------|------------------------|
| `POST`      | `/addressbook`         | Add a new address      |
| `GET`       | `/addressbook/{id}`    | Get address by ID      |
| `GET`       | `/addressbook`         | Get all addresses      |
| `PUT`       | `/addressbook/{id}`    | Update address by ID   |
| `DELETE`    | `/addressbook/{id}`    | Delete address by ID   |
---

## 📬 Example POST Request Body

```bash
{
  "fullName": "Disha Yadav",
  "email": "disha@example.com",
  "phoneNumber": "9876543210",
  "address": "123 Main Street, Pune"
}
```

## ✅ Validations Used
| **Field**     | **Validation Annotation**            |
|---------------|------------------------------------|
| `fullName`    | `@NotBlank`, `@Pattern`             |
| `email`       | `@NotBlank`, `@Email`               |
| `phoneNumber` | `@Pattern` (valid 10-digit number) |
| `address`     | `@NotBlank`                        |

## ❌ Exception Handling

### 📍 Custom Exception
`ResourceNotFoundException`: Thrown when an address is not found by ID.

```java
throw new ResourceNotFoundException("Address not found with ID: " + id);
```

### 📍 Global Exception Handler
Uses `@ControllerAdvice` and `@ExceptionHandler`

Returns structured JSON with timestamp, status, and error messages

**Example Response:**

```json
{
  "timestamp": "2025-06-05T12:30:00",
  "status": 404,
  "errors": [
    "Address not found with ID: 10"
  ]
}
```
## 📊 Logging with SLF4J

Logging helps trace the flow and debug issues:

```java
// Example logging statements using SLF4J
log.info("Creating new address entry: {}", fullName);
log.warn("Address with ID {} not found", id);
log.error("Error occurred while saving address: {}", e.getMessage());
```
## 🗃️ H2 Database Console

- **Accessible at:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** *(leave blank)*

---

## 📦 Lombok Annotations

| Annotation         | Purpose                                 |
|--------------------|------------------------------------------|
| `@Data`            | Getters, setters, toString, equals, etc. |
| `@Builder`         | Implements builder pattern               |
| `@AllArgsConstructor` | Generates constructor with all fields |
| `@NoArgsConstructor`  | Generates no-args constructor         |

---

## 🔄 DTO Pattern

Using DTOs to separate API and entity logic:

- **`AddressBookRequestDTO`**: Handles incoming validated data
- **`AddressBookResponseDTO`**: Sends sanitized response to client

---


## 🙋‍♀️ Author

**Disha**  
_Backend Developer | Spring Boot | REST APIs | Java_


