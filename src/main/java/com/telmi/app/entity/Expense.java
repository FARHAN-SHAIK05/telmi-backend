 package com.telmi.app.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    private LocalDateTime createdAt;

    @ManyToOne
    private User paidBy;

    @ManyToOne
    private Group group;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<ExpenseSplit> splits;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public Group getGroup() {
        return group;
    }

    public List<ExpenseSplit> getSplits() {
        return splits;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setSplits(List<ExpenseSplit> splits) {
        this.splits = splits;
    }
}
