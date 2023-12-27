package com.example.redis_test.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {
    public String getUserName(String userId){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (userId.equals("A")) {
            return "adam";
        }else if(userId.equals("B")){
            return "Bob";
        }
        return "";
    }


    /**
     *
     * 스프링에서 제공하는 캐싱을 이용
     * yml, springbootApplication에 어노테이션 추가하여 사용 가능
     *
     * @EnableCaching
     *
     * spring:
     *   cache:
     *      type: redis
     *
     * ageKey::A <- 키 이름 이렇게 저장 됨
     *
     */

    @Cacheable(cacheNames = "ageKey", key = "#userId")
    public int getUserAge(String userId){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (userId.equals("A")) {
            return 28;
        }else if(userId.equals("B")){
            return 32;
        }
        return 0;
    }
}
