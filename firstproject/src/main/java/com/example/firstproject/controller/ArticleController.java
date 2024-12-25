package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired //Dependency Injection(DI): 인터페이스지만 스프링부트가 자동으로 객체를 생성, 주입.
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString());
        log.info(form.toString());
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId(); // view파일이 아니라 url redirect.
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "/articles/index";
    }
}
