package com.ubl.article.controller;

import com.ubl.article.model.Article;
import com.ubl.article.service.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private ArticleService articleService;
    
     @GetMapping("/admin")
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/admin/articles")
    public String adminArticles(Model model) {
        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "admin/article";
    }

    @PostMapping("/admin/articles")
    public String addArticle(@RequestParam String title, @RequestParam String content) {
        articleService.save(title, content);
        return "redirect:/admin/articles";
    }

    @GetMapping("/admin/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteById(id);
        return "redirect:/admin/articles";
    }

}
