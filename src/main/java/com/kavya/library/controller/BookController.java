package com.kavya.library.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.kavya.library.dto.BookRequestDTO;
import com.kavya.library.dto.BookResponseDTO;
import com.kavya.library.dto.GoogleBookDTO;
import com.kavya.library.entity.Book;
import com.kavya.library.service.BookService;
import com.kavya.library.service.GoogleBooksService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/books")
@SecurityRequirement(name = "bearerAuth")
public class BookController {

    private final BookService bookService;
    private final GoogleBooksService googleBooksService;

    public BookController(BookService bookService,
            GoogleBooksService googleBooksService) {
        this.bookService = bookService;
        this.googleBooksService = googleBooksService;
    }

    // POST - Add a new book
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(
            @Valid @RequestBody BookRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.saveBook(dto));
    }

    // GET - All books (paginated)
    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> getAllBooks(
            Pageable pageable) {

        return ResponseEntity.ok(
                bookService.getAllBooks(pageable));
    }

    // GET - Search by title in DB
    @GetMapping("/search")
    public ResponseEntity<Page<BookResponseDTO>> searchBooks(
            @RequestParam String title,
            Pageable pageable) {

        return ResponseEntity.ok(
                bookService.searchBooks(title, pageable));
    }

    // NEW: Search books from Google Books API
    @GetMapping("/google")
    public ResponseEntity<List<GoogleBookDTO>> searchGoogleBooks(
            @RequestParam String title) {

        return ResponseEntity.ok(
                googleBooksService.searchBooks(title));
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
    public Book updateBook(@PathVariable Long id,
            @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    @PostMapping("/import-google")
    public ResponseEntity<?> importGoogleBooks(@RequestParam String title) {

        return ResponseEntity.ok(
                bookService.importGoogleBooks(title));
    }
}