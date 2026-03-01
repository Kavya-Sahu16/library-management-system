package com.kavya.library.service;

import com.kavya.library.entity.Book;
import com.kavya.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Save book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
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
}
