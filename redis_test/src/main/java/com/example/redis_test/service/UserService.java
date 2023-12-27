package com.example.redis_test.service;

import com.example.redis_test.UserProfile;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;
    private final StringRedisTemplate redisTemplate;

    /**
     * 레디스 스트링 저장을 이용해 캐시 직접 구현
     */
    public UserProfile getUserProfile(String userId){

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cacheName = ops.get("nameKey:" + userId);
        String userName = "";
        if (cacheName != null) {
            userName = cacheName;
        }else{
            userName = externalApiService.getUserName(userId);
            // 캐싱, 5초간 유지 되도록
            ops.set("nameKey:" + userId, userName, 5, TimeUnit.SECONDS);
        }

        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(userName, userAge);
    }




}
