package com.kavya.library.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kavya.library.entity.Borrow;
import com.kavya.library.service.BorrowService;

@RestController
@RequestMapping("/borrows")
@SecurityRequirement(name = "bearerAuth")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // LIBRARIAN can issue books
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    public Borrow addBorrow(@RequestBody Borrow borrow) {
        return borrowService.saveBorrow(borrow);
    }

    // Any authenticated user can view borrow records
    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/{id}")
    public Borrow getBorrowById(@PathVariable Long id) {
        return borrowService.getBorrowById(id);
    }

    // ADMIN can delete borrow records
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
        return "Borrow record deleted successfully";
    }

    // LIBRARIAN can mark books as returned
    @PreAuthorize("hasRole('LIBRARIAN')")
    @PutMapping("/{id}/return")
    public Borrow returnBook(@PathVariable Long id,
                             @RequestBody Borrow updatedBorrow) {
        return borrowService.updateBorrow(id, updatedBorrow);
    }

    @GetMapping("/overdue")
    public List<Borrow> getOverdueBorrows() {
        return borrowService.getOverdueBorrows();
    }
}