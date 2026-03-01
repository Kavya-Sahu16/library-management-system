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

        // ❗ Null-safe active check
        if (Boolean.FALSE.equals(member.getActive())) {
            throw new RuntimeException("Member is not active");
        }

        // attach fully loaded member
        borrow.setMember(member);

        // Auto set due date (7 days from borrow date)
        borrow.setDueDate(borrow.getBorrowDate().plusDays(7));

        // decrease copies
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

    public Borrow updateBorrow(Long id, Borrow updatedBorrow) {

        Borrow existingBorrow = borrowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        // 🚫 Guard: prevent double return
        if (existingBorrow.getReturned()) {
            throw new RuntimeException("Book already returned");
        }

        // If changing from not returned → returned
        if (!existingBorrow.getReturned() && updatedBorrow.getReturned()) {

            Book book = existingBorrow.getBook();

            // increase copies
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            bookRepository.save(book);

            // Calculate fine
            LocalDate dueDate = existingBorrow.getDueDate();
            LocalDate returnDate = updatedBorrow.getReturnDate();

            if (returnDate.isAfter(dueDate)) {
                long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
                double fineAmount = daysLate * 10.0;
                existingBorrow.setFine(fineAmount);
            } else {
                existingBorrow.setFine(0.0);
            }
        }

        existingBorrow.setReturnDate(updatedBorrow.getReturnDate());
        existingBorrow.setReturned(updatedBorrow.getReturned());

        return borrowRepository.save(existingBorrow);
    }
    public List<Borrow> getOverdueBorrows() {
    return borrowRepository
            .findByReturnedFalseAndDueDateBefore(LocalDate.now());
}
}
