package com.example.security_first.user.service;

import com.example.security_first.user.domain.User;
import com.example.security_first.user.exception.AppException;
import com.example.security_first.user.exception.ErrorCode;
import com.example.security_first.user.repository.UserRepository;
import com.example.security_first.util.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    public String join(String userName, String password) {
        userRepository.findByUserName(userName)
                .ifPresent(user -> {throw new AppException(ErrorCode.USERNAME_DUPLICATED, userName + "는 이미 있습니다.");});

        // 저장
        userRepository.save(new User(null, userName, passwordEncoder.encode(password)));
        return "success";
    }


    public String login(String userName, String password) {
        User selectedUser = userRepository.findByUserName(userName).orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOTFOUND, userName + "이 없습니다."));
        if(passwordEncoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘 못 입력했습니다");
        }

        return tokenProvider.createToken(selectedUser.getUserName(), 1000 * 60 * 60);
    }
}
