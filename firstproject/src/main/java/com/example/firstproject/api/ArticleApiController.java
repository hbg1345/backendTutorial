package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    // Get
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    // Post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        log.info(article.toString());
        return articleRepository.save(article);
    }
    // Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> create(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        Article found = articleRepository.findById(id).orElse(null);
        if (found == null || !id.equals(article.getId())){
            // 400 잘못된 요청
            log.info("잘못된 요청입니다. id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        found.patch(article);
        Article updated = articleRepository.save(found);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        articleRepository.delete(target);
        // return ResponseEntity.status(HttpStatus.OK).body(null);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
