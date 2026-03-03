package com.kavya.library.service;

import com.kavya.library.dto.DashboardResponseDTO;
import com.kavya.library.repository.BookRepository;
import com.kavya.library.repository.MemberRepository;
import com.kavya.library.repository.BorrowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

import java.time.LocalDate;

@Service
public class DashboardService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    public DashboardResponseDTO getDashboardStats() {

        DashboardResponseDTO dto = new DashboardResponseDTO();

        // Total Books
        dto.setTotalBooks(bookRepository.count());

        // Total Members
        dto.setTotalMembers(memberRepository.count());

        // Total Borrows
        dto.setTotalBorrows(borrowRepository.count());

        // Active Borrows (not returned)
        dto.setTotalActiveBorrows(
                borrowRepository.countByReturnDateIsNull());

        // Overdue Borrows
        dto.setTotalOverdueBorrows(
                borrowRepository.countByDueDateBeforeAndReturnDateIsNull(LocalDate.now()));

        // Total Fine Collected
        Double totalFine = borrowRepository.getTotalFineCollected();
        dto.setTotalFineCollected(totalFine != null ? totalFine : 0.0);

        // Total Returned Books
        dto.setTotalReturnedBorrows(
                borrowRepository.countByReturnedTrue());

        // Most Borrowed Book
        Pageable topOne = PageRequest.of(0, 1);
        List<Object[]> result = borrowRepository.findMostBorrowedBook(topOne);

        if (!result.isEmpty()) {
            dto.setMostBorrowedBook((String) result.get(0)[0]);
            dto.setMostBorrowCount((Long) result.get(0)[1]);
        } else {
            dto.setMostBorrowedBook("N/A");
            dto.setMostBorrowCount(0L);
        }
        return dto;
    }
}
