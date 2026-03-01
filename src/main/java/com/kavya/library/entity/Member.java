package com.kavya.library.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private LocalDate membershipDate;
    private Boolean active;

    public Member() {
    }

    public Member(String name, String email, String phone, LocalDate membershipDate, Boolean active) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipDate = membershipDate;
        this.active = active;
    }

    // getters and setters
    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for phone number
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    // Getter and Setter for membership date
    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
    // Getter and Setter for Active status
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}