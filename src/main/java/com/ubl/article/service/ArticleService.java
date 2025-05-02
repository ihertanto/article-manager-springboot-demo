package com.ubl.article.service;

import com.ubl.article.model.Article;
import com.ubl.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  public List<Article> findAll() {
    return articleRepository.findAll();
  }
  
  public Optional<Article> findById(Long id) {
    return articleRepository.findById(id);
  }

  public Article save(String title, String content) {
    Article article = new Article();
    article.setTitle(title);
    article.setContent(content);
    article.setCreatedAt(LocalDateTime.now());
    return articleRepository.save(article);
  }

  public void deleteById(Long id) {
    articleRepository.deleteById(id);
  }
}
