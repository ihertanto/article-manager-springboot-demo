package com.ubl.article.service;

import com.ubl.article.model.Article;
import com.ubl.article.model.Comment;
import com.ubl.article.repository.ArticleRepository;
import com.ubl.article.repository.CommentRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<Comment> findByArticleId(Long id) {
        return commentRepository.findByArticleId(id);
    }

    public Comment save(String authorName, String content, Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            return null;
        }
        Comment comment = new Comment(authorName, content, LocalDateTime.now(), article.get());
        return commentRepository.save(comment);
    }

}
