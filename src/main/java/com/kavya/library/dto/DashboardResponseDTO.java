package com.kavya.library.dto;

public class DashboardResponseDTO {

    private long totalBooks;
    private long totalMembers;
    private long totalActiveBorrows;
    private long totalOverdueBorrows;
    private double totalFineCollected;

    // Getters and Setters
    // Getters and Setters for total books
    public long getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(long totalBooks) {
        this.totalBooks = totalBooks;
    }
    // Getters and Setters for total members
    public long getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(long totalMembers) {
        this.totalMembers = totalMembers;
    }
    // Getters and Setters for total active borrows
    public long getTotalActiveBorrows() {
        return totalActiveBorrows;
    }

    public void setTotalActiveBorrows(long totalActiveBorrows) {
        this.totalActiveBorrows = totalActiveBorrows;
    }
    // Getters and Setters for total overdue borrows
    public long getTotalOverdueBorrows() {
        return totalOverdueBorrows;
    }

    public void setTotalOverdueBorrows(long totalOverdueBorrows) {
        this.totalOverdueBorrows = totalOverdueBorrows;
    }
    // Getters and Setters for total fine collected
    public double getTotalFineCollected() {
        return totalFineCollected;
    }

    public void setTotalFineCollected(double totalFineCollected) {
        this.totalFineCollected = totalFineCollected;
    }
}
