package com.ubl.article.controller;

import com.ubl.article.service.ArticleService;
import com.ubl.article.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("article/{id}")
    public String articledetail(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id).get());
        model.addAttribute("comments", commentService.findByArticleId(id));
        return "article";
    }
    
    @PostMapping("article/{id}/comment")
    public String postComment(@PathVariable Long id, @RequestParam String authorName, @RequestParam String content) {        
        commentService.save(authorName, content, id);
        return "redirect:/article/" + id;
    }

}
