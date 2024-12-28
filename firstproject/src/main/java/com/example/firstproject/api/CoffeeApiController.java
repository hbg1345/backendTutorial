package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CoffeeApiController {
    @Autowired
    CoffeeRepository coffeeRepository;
    // Get
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return (List<Coffee>) coffeeRepository.findAll();
    }
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }
    // Post
    @PostMapping("/api/coffees")
    public Coffee create(@RequestBody CoffeeDto dto){
        return coffeeRepository.save(dto.toEntity());
    }
    // Patch
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto){
        log.info("dto is" + dto.toString());
        Coffee coffee = dto.toEntity();
        log.info(coffee.toString());

        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (id != coffee.getId() || target == null){
            log.info("잘못된 입력" + id + coffee.getId());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        target.patch(coffee);
        coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(target);
    }
    // Delete
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
