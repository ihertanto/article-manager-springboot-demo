package com.ubl.article.repository;

import com.ubl.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
  // Bisa tambah custom query di sini jika perlu
}