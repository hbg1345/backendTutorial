package com.example.firstproject.repository;

import com.example.firstproject.dto.ReviewDto;
import com.example.firstproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "select * from review where coffee_id=:coffeeId", nativeQuery = true)
    List<Review> findByCoffeeId(Long coffeeId);
}
