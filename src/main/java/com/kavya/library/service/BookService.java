package com.kavya.library.service;

import com.kavya.library.dto.BookRequestDTO;
import com.kavya.library.dto.BookResponseDTO;
import com.kavya.library.dto.GoogleBookDTO;
import com.kavya.library.entity.Book;
import com.kavya.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookService {
    private final GoogleBooksService googleBooksService;
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository,
                   GoogleBooksService googleBooksService) {
    this.bookRepository = bookRepository;
    this.googleBooksService = googleBooksService;
}

    // Save book
    public BookResponseDTO saveBook(BookRequestDTO dto) {
        logger.info("Saving new book with title: {}", dto.getTitle());

        Book book = mapToEntity(dto);
        Book saved = bookRepository.save(book);

        logger.info("Book saved successfully with ID: {}", saved.getId());

        return mapToDTO(saved);
    }

    // Get all books
    public Page<BookResponseDTO> getAllBooks(Pageable pageable) {
        logger.info("Fetching books - page: {}, size: {}",
                pageable.getPageNumber(),
                pageable.getPageSize());

        return bookRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    // Get book by ID
    public Book getBookById(Long id) {
        logger.info("Fetching book with ID: {}", id);
        return bookRepository.findById(id).orElse(null);
    }

    // Delete book
    public void deleteBook(Long id) {
        logger.info("Deleting book with ID: {}", id);
        bookRepository.deleteById(id);
    }

    // Update book
    public Book updateBook(Long id, Book updatedBook) {
        logger.info("Updating book with ID: {}", id);
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

    public Page<BookResponseDTO> searchBooks(String title, Pageable pageable) {
        logger.info("Searching books with title containing: {}", title);

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
    
    public List<Book> importGoogleBooks(String title) {

    List<GoogleBookDTO> googleBooks = googleBooksService.searchBooks(title);

    List<Book> savedBooks = new ArrayList<>();

    for (GoogleBookDTO g : googleBooks) {

        Book book = new Book();
        book.setTitle(g.getTitle());
        book.setAuthor(g.getAuthor());
        book.setIsbn(g.getIsbn());
        book.setAvailableCopies(5);

        savedBooks.add(bookRepository.save(book));
    }

    return savedBooks;
}
}
