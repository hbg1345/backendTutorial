package com.example.firstproject.service;

import com.example.firstproject.dto.ReviewDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.entity.Review;
import com.example.firstproject.repository.CoffeeRepository;
import com.example.firstproject.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.InvalidIsolationLevelException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    CoffeeRepository coffeeRepository;

    public List<ReviewDto> read(Long coffeeId) {

        return reviewRepository.findByCoffeeId(coffeeId).stream().
                map(ReviewDto::createDto).toList();
    }

    @Transactional
    public ReviewDto create(Long coffeeId, ReviewDto dto) {
        Coffee coffee = coffeeRepository.findById(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("그런 제품은 없습니다.")
        );
        Review target = ReviewDto.createReview(dto, coffee);
        Review created = reviewRepository.save(target);
        return ReviewDto.createDto(created);
    }

    @Transactional
    public ReviewDto update(Long reviewId, ReviewDto dto) {
        Review target = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("그런 리뷰는 없습니다.")
        );
        if (dto.getId() != reviewId)
            throw new IllegalArgumentException("해당 리뷰는 수정할 수 없습니다.");
        target.patch(dto);
        Review updated = reviewRepository.save(target);
        return ReviewDto.createDto(updated);
    }

    public ReviewDto delete(Long reviewId) {
        Review target = reviewRepository.findById(reviewId).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 리뷰입니다."));
        reviewRepository.delete(target);
        return ReviewDto.createDto(target);
    }
}
