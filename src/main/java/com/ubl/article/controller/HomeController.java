package com.ubl.article.controller;

import com.ubl.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
      model.addAttribute("articles", articleService.findAll());
      return "home";
    }

}
