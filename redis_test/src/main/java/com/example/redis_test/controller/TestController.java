package com.example.redis_test.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *  Redis 연동 간단 테스트
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/set")
    public String setFruit(@RequestParam String name){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("fruit", name);

        return "saved";
    }

    @GetMapping("/get")
    public String getFruit(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get("fruit");
    }
}
