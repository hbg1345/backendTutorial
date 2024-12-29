package com.example.firstproject.entity;

import com.example.firstproject.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="coffee_id")
    private Coffee coffee;
    @Column
    private String name;
    @Column
    private String body;
    @Column
    private Long score;

    public void patch(ReviewDto dto) {
        if (dto.getId() != this.id)
            throw new IllegalArgumentException("수정할 수 없음.");
        if (dto.getName() != null)
            this.name = dto.getName();
        if (dto.getBody() != null)
            this.body = dto.getBody();
        if (dto.getScore() != null)
            this.score = dto.getScore();
    }
}
