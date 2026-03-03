package com.kavya.library.service;

import com.kavya.library.dto.BookRequestDTO;
import com.kavya.library.dto.BookResponseDTO;
import com.kavya.library.entity.Book;
import com.kavya.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Save book
    public BookResponseDTO saveBook(BookRequestDTO dto) {
        Book book = mapToEntity(dto);
        Book saved = bookRepository.save(book);
        return mapToDTO(saved);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Delete book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Update book
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
            return bookRepository.save(existingBook);
        }

        return null;
    }

    public Page<BookResponseDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    public Page<BookResponseDTO> searchBooks(String title, Pageable pageable) {
        return bookRepository
                .findByTitleContainingIgnoreCase(title, pageable)
                .map(this::mapToDTO);
    }

    private BookResponseDTO mapToDTO(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getAvailableCopies());
    }

    private Book mapToEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailableCopies(dto.getAvailableCopies());
        return book;
    }
}
