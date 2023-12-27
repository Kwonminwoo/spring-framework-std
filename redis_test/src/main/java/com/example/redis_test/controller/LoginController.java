package com.example.redis_test.controller;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    Map<String, String> sessionMap = new HashMap<>();


    /**
     *  간단한 세션 활용
     *  분산 환경이 아닌 곳에서 스프링으로 세션을 어떻게 활용할 수 있는지
     *
     *  두 개의 서버를 띄웠을 때 저장소가 어플리케이션 내부에 있어
     *  다른 서버에 있는 세션 정보를 확인 할 수 없는 문제가 생긴다
     *
     */
    @GetMapping("/login")
    public String login(HttpSession httpSession, @RequestParam String name){
        sessionMap.put(httpSession.getId(), name);

        return "saved";
    }

    @GetMapping("/myName")
    public String myName(HttpSession httpSession){
        return sessionMap.get(httpSession.getId());
    }


    /**
     *
     * spring:
     *   session:
     *     storage-type: redis
     *
     *
     *  implementation 'org.springframework.session:spring-session-data-redis'
     *
     *
     *  yml, gradle에 세션 저장소로 redis를 사용하겠다는 설정만 작성하면 redis를 session 저장소로 사용할 수 있게된다.
     */
    @GetMapping("/login2")
    public String login2(HttpSession httpSession, @RequestParam String name){
        httpSession.setAttribute("name", name);

        return "saved";
    }

    @GetMapping("/myName2")
    public String myName2(HttpSession httpSession){
        return (String) httpSession.getAttribute("name");
    }

}
