package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {
    @Autowired
    CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return (List<Coffee>) coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        if (coffee.getId() == null)
            return coffeeRepository.save(coffee);
        return null;
    }

    public Coffee update(Long id, CoffeeDto dto) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        Coffee source = dto.toEntity();
        if (target == null || source.getId() != id)
            return null;
        target.patch(source);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null)
            return null;
        coffeeRepository.delete(target);
        return target;
    }
}
