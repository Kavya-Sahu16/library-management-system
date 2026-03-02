package com.kavya.library.service;

import com.kavya.library.dto.DashboardResponseDTO;
import com.kavya.library.repository.BookRepository;
import com.kavya.library.repository.MemberRepository;
import com.kavya.library.repository.BorrowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // Handle null case (if no fines exist)
        dto.setTotalFineCollected(totalFine != null ? totalFine : 0.0);

        return dto;
    }
}
