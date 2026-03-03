package com.kavya.library.controller;

import com.kavya.library.dto.ApiResponse;
import com.kavya.library.entity.Book;
import com.kavya.library.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // POST - Add a new book
    @PostMapping // Handles POST request
    public Book addBook(@RequestBody Book book) { // @RequestBody → Converts JSON → Book object
        return bookService.saveBook(book);
    }

    // GET - All books
    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {

        List<Book> books = bookService.getAllBooks();

        ApiResponse<List<Book>> response = new ApiResponse<>(200, "Books fetched successfully", books);

        return ResponseEntity.ok(response);
    }

    // GET - Book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // DELETE - Delete book
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    // PUT - Update book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }
}