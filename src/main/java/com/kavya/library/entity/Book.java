package com.kavya.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    public Book() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(unique = true, length = 20)
    private String isbn;

    @Column(name = "availableCopies")
    private int availableCopies;

    // Getters and Setters
    // Getter and Setter for id
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    // Getter and Setter for title
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    // Getter and Setter for author
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    // Getter and Setter for isbn
    public String getIsbn(){
        return isbn;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
    // Getter and Setter for availableCopies
    public int getAvailableCopies(){
        return availableCopies;
    }
    public void setAvailableCopies(int availableCopies){
        this.availableCopies = availableCopies;
    }
}
