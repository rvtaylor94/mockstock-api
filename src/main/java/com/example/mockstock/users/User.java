package com.example.mockstock.users;

import com.example.mockstock.portfolios.Portfolios;
import com.example.mockstock.transactions.Transactions;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity
@Table (name = "users")
@JsonInclude (JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "user_name")
    private String name;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column (name = "user_password", columnDefinition = "VARCHAR(255) BINARY")
    private String password;
    @Column(precision = 10, scale = 2)
    private Double balance;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Portfolios> portfolios;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Transactions> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return new BigDecimal(balance).setScale(2, RoundingMode.UP).doubleValue();
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setPortfolios(List<Portfolios> portfolios) {
        this.portfolios = portfolios;
    }


    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public User(String name, String firstName, String lastName, String password, Double balance) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.balance = balance;
    }

    public User() {}
}
