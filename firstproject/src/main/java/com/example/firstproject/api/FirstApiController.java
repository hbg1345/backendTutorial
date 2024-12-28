package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 일반 view page(.mustache)를 반환하는 controller와 달리 json 또는 텍스트 데이터를 반환.
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}
