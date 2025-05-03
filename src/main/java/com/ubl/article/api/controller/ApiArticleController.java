package com.ubl.article.api.controller;

import com.ubl.article.model.Article;
import com.ubl.article.model.Comment;
import com.ubl.article.service.ArticleService;
import com.ubl.article.service.CommentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/api/articles")
    public List<Article> getAll() {
        return articleService.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Optional<Article> getById(@PathVariable Long id) {
        return articleService.findById(id);
    }

    @GetMapping("/api/articles/{id}/comments")
    public List<Comment> getArticleComments(@PathVariable Long id) {
        return commentService.findByArticleId(id);
    }

    @PostMapping("/api/articles")
    public Article addArticle(@RequestBody Article article) {
        return articleService.save(article.getTitle(), article.getContent());
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        return articleService.findById(id).map(article -> {
            articleService.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
