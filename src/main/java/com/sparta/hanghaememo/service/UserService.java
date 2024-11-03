package com.sparta.hanghaememo.service;


import com.sparta.hanghaememo.dto.UserRequestDto;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.repository.UserRepository;
import com.sparta.hanghaememo.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String signUp(UserRequestDto requestDto) {
        // 아이디 중복 검사
        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자 이름입니다.");
        }

        // 유효한 경우, 새로운 사용자 저장
        User userEnroll = new User(requestDto);
        userRepository.save(userEnroll);
        return "회원 가입이 성공적으로 완료되었습니다.";
    }

    @Transactional
    public HttpHeaders signIn(UserRequestDto requestDto) {
        // 사용자 정보 검증
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 사용자입니다."));

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성
        String token = jwtUtil.generateToken(user.getUsername());

        // 헤더에 토큰 추가 및 메시지 반환
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }

}