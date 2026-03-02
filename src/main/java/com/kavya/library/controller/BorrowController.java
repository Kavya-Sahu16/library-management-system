package com.kavya.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.kavya.library.entity.Borrow;
import com.kavya.library.service.BorrowService;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public Borrow addBorrow(@RequestBody Borrow borrow) {
        return borrowService.saveBorrow(borrow);
    }

    @GetMapping
    public List<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("/{id}")
    public Borrow getBorrowById(@PathVariable Long id) {
        return borrowService.getBorrowById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
        return "Borrow record deleted successfully";
    }

    @PutMapping("/{id}/return")
    public Borrow returnBook(@PathVariable Long id) {
        return borrowService.updateBorrow(id);
    }

    @GetMapping("/overdue")
    public List<Borrow> getOverdueBorrows() {
        return borrowService.getOverdueBorrows();
    }
}
