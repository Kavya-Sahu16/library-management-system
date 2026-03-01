package com.kavya.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // MANY borrow records can belong to ONE member
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    /*
     * 👉 This field connects to the member table
     * 👉 member_id is the foreign key
     * 👉 Many Borrow rows → One Member
     */

    // MANY borrow records can belong to ONE book
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    /*
     * 👉 This field connects to the book table
     * 👉 book_id is the foreign key
     * 👉 Many Borrow rows → One Book
     */

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Boolean returned;
    private LocalDate dueDate;
    private Double fine;

    public Borrow() {
    }

    // Getters and Setters
    // Getters and Setters for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and Setters for member
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    // Getters and Setters for book
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    // Getters and Setters for borrowDate
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    // Getters and Setters for returnDate
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Getters and Setters for returned
    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    // Getters and Setters for due date
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Getters and Setters for fine
    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }
}
