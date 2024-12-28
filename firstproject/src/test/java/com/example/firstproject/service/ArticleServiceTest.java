package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 1. GT Data.
        Article a = new Article(1L, "ㄴㅇㄹㄷ", "ㄹㄷㅈㄷㅈㄷ");
        List<Article> gT = new ArrayList<>(Arrays.asList((a)));
        // 2. Generated data.
        List<Article> articleList = articleService.index();
        // 3. Compare both.
        assertEquals(gT.toString(), articleList.toString());
    }

    @Test
    void show_성공() {
        Long id = 1L;
        // 1. Expected data
        Article expected = new Article(1L, "ㄴㅇㄹㄷ", "ㄹㄷㅈㄷㅈㄷ");
        // 2. Generated data.
        Article real = articleService.show(id);
        // 3. Compare both.
        assertEquals(expected.toString(), real.toString());
    }
    @Test
    void show_실패() {
        Long id = 2L;
        // 1. Expected Data.
        Article expected = null;
        // 2. Generated Data.
        Article real = articleService.show(id);
        // 3. Compare.
        assertEquals(null, real);
    }

    @Test
    @Transactional
    void create_성공() {
        String title = "테스트 제목";
        String content = "테스트 내용";
        Long id = 2L;
        ArticleForm dto = new ArticleForm(null, title, content);
        // 1. Expected
        Article expected = new Article(id, title, content);
        // 2. Real
        Article real = articleService.create(dto);
        assertEquals(expected.toString(), real.toString());
    }

    @Transactional
    @Test
    void create_실패() {
        String title = "테스트 제목";
        String content = "테스트 내용";
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id, title, content);
        // 1. Expected
        Article expected = null;
        // 2. Real
        Article real = articleService.create(dto);
        assertEquals(expected, real);
    }

    @Transactional
    @Test
    void update_성공() {
        Long id = 1L;
        String targetTitle = "fewwe";
        String targetContent = "fㄷ쟐ㄷㅈ";
        ArticleForm dto = new ArticleForm(id, targetTitle, targetContent);
        // 1. Expected data
        Article expected = new Article(1L, targetTitle, targetContent);
        // 2. Generated
        Article generated = articleService.update(id, dto);
        assertEquals(expected.toString(), generated.toString());
    }

    @Transactional
    @Test
    void update_실패() {
        Long id = -1L;
        String targetTitle = "fewwe";
        String targetContent = "fㄷ쟐ㄷㅈ";
        ArticleForm dto = new ArticleForm(id, targetTitle, targetContent);
        // 1. Expected data
        Article expected = null;
        // 2. Generated
        Article generated = articleService.update(id, dto);
        assertEquals(expected, generated);
    }

    @Transactional
    @Test
    void delete_성공() {
        Long id = 1L;
        Article expected = new Article(1L, "ㄴㅇㄹㄷ", "ㄹㄷㅈㄷㅈㄷ");
        Article generated = articleService.delete(id);
        assertEquals(expected.toString(), generated.toString());
    }

    @Transactional
    @Test
    void delete_실패() {
        Long id = -1L;
        Article expected = null;
        Article generated = articleService.delete(id);
        assertEquals(expected, generated);
    }
}