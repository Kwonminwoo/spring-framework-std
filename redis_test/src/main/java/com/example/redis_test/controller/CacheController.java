package com.example.redis_test.controller;

import com.example.redis_test.UserProfile;
import com.example.redis_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * 레디스 캐시로 활용하기
 * 레디스에 원하는 데이터가 있는지 확인하고 없으면 프로세스를 진행
 * Cache Aside 패턴으로 구현
 *
 */
@RestController
@RequiredArgsConstructor
public class CacheController {

    private final UserService userService;

    @GetMapping("/users/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable("userId") String userId){
        return userService.getUserProfile(userId);
    }
}
