package com.kavya.library.dto;

public class BookResponseDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer availableCopies;

    public BookResponseDTO(Long id, String title, String author, String isbn, Integer availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availableCopies = availableCopies;
    }

    // Getters only (no setters needed for response)
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }
}
