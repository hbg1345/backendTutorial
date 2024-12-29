package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글 모든 댓글 조회")
    void findByArticleId() {
        Long id = 1L;
        Article article = new Article(id, "ㄴㅇㄹㄷ", "ㄹㄷㅈㄷㅈㄷ");
        Comment a = new Comment(1L, article,"박씨", "굿");
        Comment b = new Comment(2L, article,"박씨2", "굿2");
        List<Comment> expected = Arrays.asList(a,b);
        List<Comment> real = commentRepository.findByArticleId(id);
        assertEquals(expected.toString(), real.toString());
    }
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    @Test
    void findByNickname() {
        String nickname = "박씨";
        Article article = new Article(1L, "ㄴㅇㄹㄷ", "ㄹㄷㅈㄷㅈㄷ");
        Comment a = new Comment(1L, article,"박씨", "굿");

        List<Comment> expected = Arrays.asList(a);
        List<Comment> real = commentRepository.findByNickname(nickname);
        assertEquals(expected.toString(), real.toString());

        List<Comment> real2 = commentRepository.findByNickname(null);
        List<Comment> real3 = commentRepository.findByNickname("");
        List<Comment> real4 = commentRepository.findByNickname("없는 이름");

        List<Comment> expected2 = Collections.emptyList();
        assertEquals(expected2, real2);
        assertEquals(expected2, real3);
        assertEquals(expected2, real4);
    }
}