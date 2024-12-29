package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import com.example.firstproject.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.InvalidIsolationLevelException;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDto {
    private Long id;
    private Long coffeeId;
    private String name;
    private String body;
    private Long score;

    public static ReviewDto createDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getCoffee().getId(),
                review.getName(),
                review.getBody(),
                review.getScore()
        );
    }

    public static Review createReview(ReviewDto dto, Coffee coffee) {
        if (dto.getId() != null)
            throw new IllegalArgumentException("잘못된 리뷰입니다.");
        if (dto.getCoffeeId() != coffee.getId())
            throw new InvalidIsolationLevelException("리뷰하려는 제품이 맞는지 다시한번 확인해주세요.");

        return new Review(
                null, coffee, dto.getName(), dto.getBody(), dto.getScore()
        );
    }
}
