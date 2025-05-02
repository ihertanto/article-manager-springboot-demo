package com.ubl.article.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Comment{

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="Author name harus diisi!")
    private String authorName;

    @Column(length = 1000)
    @NotBlank(message="Isi content tidak boleh kosong")
    private String content;

    private LocalDateTime createdAt;

    public Comment(String authorName, String content, LocalDateTime createdAt, Article article) {
        this.authorName = authorName;
        this.content = content;
        this.createdAt = createdAt;
        this.article = article;
    }

    public Comment() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }


    @ManyToOne
    private Article article;
}