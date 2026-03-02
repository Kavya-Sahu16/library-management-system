package com.kavya.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.kavya.library.entity.Book;
import com.kavya.library.entity.Borrow;
import com.kavya.library.repository.BookRepository;
import com.kavya.library.repository.BorrowRepository;
import com.kavya.library.repository.MemberRepository;
import com.kavya.library.entity.Member;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Borrow saveBorrow(Borrow borrow) {

        Book book = bookRepository.findById(borrow.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book not available");
        }

        Member member = memberRepository.findById(borrow.getMember().getId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (Boolean.FALSE.equals(member.getActive())) {
            throw new RuntimeException("Member is not active");
        }

        // Set proper references
        borrow.setBook(book);
        borrow.setMember(member);

        // ✅ Backend controls dates
        LocalDate today = LocalDate.now();
        borrow.setBorrowDate(today);
        borrow.setDueDate(today.plusDays(7));

        // ✅ Default values
        borrow.setReturned(false);
        borrow.setFine(0.0);

        // Decrease stock
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(Long id) {
        return borrowRepository.findById(id).orElse(null);
    }

    public void deleteBorrow(Long id) {
        borrowRepository.deleteById(id);
    }

    @Transactional
    public Borrow updateBorrow(Long id, Borrow updatedBorrow) {

        Borrow existingBorrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (Boolean.TRUE.equals(existingBorrow.getReturned())) {
            throw new RuntimeException("Book already returned");
        }

        LocalDate returnDate = updatedBorrow.getReturnDate();

        existingBorrow.setReturnDate(returnDate);
        existingBorrow.setReturned(true);

        Book book = existingBorrow.getBook();

        // Increase stock
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        // Calculate fine
        if (returnDate.isAfter(existingBorrow.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(existingBorrow.getDueDate(), returnDate);
            existingBorrow.setFine(daysLate * 10.0);
        } else {
            existingBorrow.setFine(0.0);
        }

        return borrowRepository.save(existingBorrow);
    }

    public List<Borrow> getOverdueBorrows() {
        return borrowRepository
                .findByReturnedFalseAndDueDateBefore(LocalDate.now());
    }
}