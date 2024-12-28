package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null)
            return null;
        log.info(article.toString());
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        Article found = articleRepository.findById(id).orElse(null);
        if (found == null || !id.equals(article.getId())){
            log.info("잘못된 요청입니다. id: {}, article: {}", id, article.toString());
            return null;
        }
        found.patch(article);
        Article updated = articleRepository.save(found);
        return updated;
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null){
            return null;
        }
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> transactionTest(List<ArticleForm> dtos) {
        List<Article> articleList =
                dtos.stream().map(ArticleForm::toEntity).toList();
//        List<Article> articleList = new ArrayList<>();
//        for (ArticleForm dto : dtos) {
//            Article article = dto.toEntity();
//            articleList.add(article);
//        }
        articleList.forEach(article -> articleRepository.save(article));
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패"));
        return articleList;
    }
}
