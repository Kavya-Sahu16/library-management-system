package com.kavya.library.controller;

import com.kavya.library.dto.BookRequestDTO;
import com.kavya.library.dto.BookResponseDTO;
import com.kavya.library.entity.Book;
import com.kavya.library.service.BookService;

import org.springframework.http.HttpStatus;
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

    // GET - Search by title
    @GetMapping("/search")
    public ResponseEntity<Page<BookResponseDTO>> searchBooks(
            @RequestParam String title,
            Pageable pageable) {

        return ResponseEntity.ok(
                bookService.searchBooks(title, pageable));
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