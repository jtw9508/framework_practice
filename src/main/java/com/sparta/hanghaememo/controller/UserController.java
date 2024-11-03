package com.sparta.hanghaememo.controller;


import com.sparta.hanghaememo.dto.UserRequestDto;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid UserRequestDto requestDto) {
        String message = userService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/api/signin")
    public ResponseEntity<String> signin(@RequestBody @Valid UserRequestDto requestDto) {
        Optional<HttpHeaders> headersOptional = Optional.ofNullable(userService.signIn(requestDto));

        return headersOptional
                .map(headers -> ResponseEntity.ok()
                        .headers(headers)
                        .body("로그인 성공"))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("로그인 실패: 잘못된 사용자 정보입니다."));
    }

}