package com.example.security_first.user.controller;

import com.example.security_first.user.dto.UserJoinRequest;
import com.example.security_first.user.dto.UserLoginRequest;
import com.example.security_first.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest userJoinRequest){
        userService.join(userJoinRequest.getUserName(), userJoinRequest.getPassword());
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
        return ResponseEntity.ok().body(token);
    }


    @GetMapping
    public String test(){
        return "test";
    }
}
