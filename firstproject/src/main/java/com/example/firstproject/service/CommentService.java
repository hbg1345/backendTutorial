package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    // 1. 댓글 조회
    public List<CommentDto> comments(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        List<CommentDto> dtos = comments.stream().map(a->CommentDto.createDto(a)).toList();
        return dtos;
    }

    // 2. 댓글 생성
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회 및 예외 처리
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        // 2. 댓글 엔터티 생성
        Comment comment = CommentDto.createComment(dto, article);
        // 3. 댓글 엔터티 DB에 저장
        commentRepository.save(comment);
        // 4. 댓글 엔터티를 dto로 변환
        return CommentDto.createDto(comment);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글입니다.")
        );
        comment.patch(dto);
        Comment updated = commentRepository.save(comment);
        return CommentDto.createDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글입니다.")
        );
        commentRepository.delete(comment);
        return CommentDto.createDto(comment);
    }
}

