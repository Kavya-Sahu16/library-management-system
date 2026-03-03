package com.kavya.library.controller;

import com.kavya.library.dto.ApiResponse;
import com.kavya.library.entity.Book;
import com.kavya.library.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // POST - Add a new book
    @PostMapping // Handles POST request
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) { // @RequestBody → Converts JSON → Book object
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.status(201).body(savedBook);
    }

    // GET - All books
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Book>>> getAllBooks(Pageable pageable) {

        Page<Book> booksPage = bookService.getAllBooks(pageable);

        ApiResponse<Page<Book>> response = new ApiResponse<>(200, "Books fetched successfully", booksPage);

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

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<Book>>> searchBooks(
            @RequestParam String title,
            Pageable pageable) {

        Page<Book> booksPage = bookService.searchBooks(title, pageable);

        ApiResponse<Page<Book>> response = new ApiResponse<>(200, "Books fetched successfully", booksPage);

        return ResponseEntity.ok(response);
    }
}