package com.photoblog.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private Account createdBy;

    public Album() {}

    public Album(String description, LocalDate createdDate, Account createdBy) {
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Account createdBy) {
        this.createdBy = createdBy;
    }
}
