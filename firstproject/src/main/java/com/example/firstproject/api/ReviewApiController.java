package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.dto.ReviewDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import com.example.firstproject.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ReviewApiController {
    @Autowired
    ReviewService reviewService;
    // 1. 특정 커피 리뷰 조회
    @GetMapping("/api/coffees/{coffeeId}/reviews")
    public ResponseEntity<List<ReviewDto>> read(@PathVariable Long coffeeId){
        List<ReviewDto> dtos = reviewService.read(coffeeId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 2. 특정 커피 리뷰 추가
    @PostMapping("/api/coffees/{coffeeId}/reviews")
    public ResponseEntity<ReviewDto> create(@PathVariable Long coffeeId, @RequestBody ReviewDto dto){
        ReviewDto created = reviewService.create(coffeeId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }
    // 3. 특정 커피 리뷰 수정
    @PatchMapping("/api/coffees/{reviewId}/reviews")
    public ResponseEntity<ReviewDto> update(@PathVariable Long reviewId, @RequestBody ReviewDto dto){
        ReviewDto updated = reviewService.update(reviewId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // 4. 특정 커피 리뷰 제거
    @DeleteMapping("/api/coffees/{reviewId}/reviews")
    public ResponseEntity<ReviewDto> update(@PathVariable Long reviewId){
        ReviewDto deleted = reviewService.delete(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
