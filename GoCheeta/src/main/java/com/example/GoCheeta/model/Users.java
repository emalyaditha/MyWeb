package com.example.GoCheeta.model;

import lombok.Getter;

import javax.persistence.*;
@Getter
@Entity
@Table(name = "users")
public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(nullable = false, unique = true, length = 45)
        private String email;

        @Column(length = 255, nullable = false)
        private String password;

        @Column(length = 45, nullable = false, name = "first_name")
        private String firstName;

        @Column(length = 45, nullable = false, name = "last_name")
        private String lastName;

        @Column(length = 45, nullable = false, name = "phone_number")
        private String phoneNumber;

        @Column(length = 45, nullable = false, name = "car")
        private String car;

        @Column(length = 45, nullable = false, name = "branch")
        private String branch;

    @Column(length = 45, nullable = false, name = "role")
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Users(Integer id, String email, String password, String firstName, String lastName, String phoneNumber, String car, String branch, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.car = car;
        this.branch = branch;
        this.role = role;
    }

    public Users() {
    }
}
