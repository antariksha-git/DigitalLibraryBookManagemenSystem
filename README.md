# Digital Library Book Management System

## Introduction
The **Digital Library Book Management System (DLBMS)** is a Spring Boot application that allows librarians to efficiently manage books. It provides features to add, update, search, and remove books while maintaining their availability status.

## Features
- **Add a Book**: Store book details (ID, Title, Author, Genre, and Availability Status).
- **View All Books**: Retrieve a list of all books with their details.
- **Search for a Book**: Find a book using its unique ID or title.
- **Update Book Details**: Modify book attributes such as title, author, or availability status.
- **Delete a Book Record**: Remove a book from the catalog.
- **Exit System**: Close the application.

## Constraints
- **Book ID** and Title must be unique.
- **Title and Author** must be non-empty strings.
- **Availability Status** must be either `Available` or `Checked Out`.

## API Endpoints
### Base URL: `/api/v1/books`

| Method  | Endpoint               | Description                      |
|---------|------------------------|----------------------------------|
| POST    | `/`                    | Add a new book                  |
| GET     | `/`                    | Retrieve all books              |
| GET     | `/{bookIdOrTitle}`      | Search book by ID or title      |
| PUT     | `/{bookId}`            | Update book details             |
| DELETE  | `/{bookId}`            | Remove a book from the catalog  |

## Request and Response Formats
### Add a Book (POST `/api/v1/books`)
**Request Body:**
```json
{
  "bookId": "b34md",
  "title": "Harry Potter",
  "author": "JK Rowling",
  "genre": "Action",
  "availabilityStatus": "AVAILABLE"
}
```
**Response:**
```json
{
  "bookId": "b34md",
  "title": "Harry Potter",
  "author": "JK Rowling",
  "genre": "Action",
  "availabilityStatus": "AVAILABLE"
}
```

### Get All Books (GET `/api/v1/books`)
**Response:**
```json
[
  {
    "bookId": "asd123",
    "title": "Some Book",
    "author": "Craig Walls",
    "genre": "Technology",
    "availabilityStatus": "AVAILABLE"
  },
  {
    "bookId": "dsa124",
    "title": "Some other book",
    "author": "Robert C. Martin",
    "genre": "Software Engineering",
    "availabilityStatus": "CHECKED_OUT"
  }
]
```

### Update a Book (PUT `/api/v1/books/{bookId}`)
**Request Body:**
```json
{
  "title": "Spring Boot 2.0",
  "author": "Craig Walls",
  "genre": "Technology",
  "availabilityStatus": "CHECKED_OUT"
}
```
**Response:**
```json
{
  "bookId": "ASDF123",
  "title": "Spring Boot 2.0",
  "author": "Craig Walls",
  "genre": "Technology",
  "availabilityStatus": "CHECKED_OUT"
}
```

### Delete a Book (DELETE `/api/v1/books/{bookId}`)
**Response:**
```json
{
  "bookId": "ASDF123",
  "title": "Spring Boot 2.0",
  "author": "Craig Walls",
  "genre": "Technology",
  "availabilityStatus": "CHECKED_OUT"
}
```

## How to Run the Application
### Prerequisites
- Java 17+
- Maven 3+
- Spring Boot

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/antariksha-git/DigitalLibraryBookManagemenSystem.git
   ```
2. Navigate to the project directory:
   ```sh
   cd DigitalLibraryBookManagemenSystem
   ```
3. Build the project:
   ```sh
   mvn clean install
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```
5. Access the API at `http://localhost:8080/api/v1/books`.

## Technologies Used
- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Lombok**
- **Jakarta Validation**