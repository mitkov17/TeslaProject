package com.my.teslaproject.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Data
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 symbols")
    private String fullName;

    @Column(name = "username")
    @NotEmpty(message = "Username should not be empty")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "phone")
    private long phone;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Order> orders;

    public Person() {

    }

    public Person(String fullName, String username, int phone, String email, Date dateOfBirth) {
        this.fullName = fullName;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}