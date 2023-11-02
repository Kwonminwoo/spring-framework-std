package com.example.security_first.user.controller;

import com.example.security_first.user.dto.UserJoinRequest;
import com.example.security_first.user.dto.UserLoginRequest;
import com.example.security_first.user.exception.AppException;
import com.example.security_first.user.exception.ErrorCode;
import com.example.security_first.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test @DisplayName("회원가입 성공")
    @WithMockUser
    void join() throws Exception {
        String userName = "asdasd";
        String password = "hqwdn23314njsa";

        mvc.perform(post("/api/v1/users/join")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test @DisplayName("회원가입 실패: userName 중복")
    @WithMockUser
    void joinFail() throws Exception {
        String userName = "asdasd";
        String password = "hqwdn23314njsa";

        when(userService.join(any(), any()))
                .thenThrow(new RuntimeException("해당 userId가 중복됨"));

        mvc.perform(post("/api/v1/users/join")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
                )
                .andDo(print())
                .andExpect(status().isConflict());
    }



    @Test
    @WithMockUser
    @DisplayName("로그인 성공")
    void login_success() throws Exception {
        String userName = "asdasd";
        String password = "hqwdn23314njsa";

        when(userService.login(any(), any()))
                .thenReturn("token");

        mvc.perform(post("/api/v1/users/login")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("로그인 실패 - userName 없음")
    void login_fail1() throws Exception{
        String userName = "asdasd";
        String password = "hqwdn23314njsa";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOTFOUND, "해당 id가 없습니다."));

        mvc.perform(post("/api/v1/users/login")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @DisplayName("로그인 실패 - password 틀림")
    void login_fail2() throws Exception{
        String userName = "asdasd";
        String password = "hqwdn23314njsa";

        when(userService.login(any(), any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, "password가 틀림"));

        mvc.perform(post("/api/v1/users/login")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}