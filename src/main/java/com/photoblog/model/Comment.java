package com.photoblog.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by")
    private Account createdBy;

    private Integer post;

    public Comment() {}

    public Comment(String body, LocalDate createdDate, Account createdBy, Integer post) {
        this.body = body;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }
}
