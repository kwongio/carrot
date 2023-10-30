package com.example.carrot.domain.user.controller;

import com.example.carrot.domain.user.domain.User;
import com.example.carrot.domain.user.dto.UserRequestDto;
import com.example.carrot.domain.user.dto.UserResponseDto;
import com.example.carrot.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    @PostMapping("/user/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto requestDto){
        return ResponseEntity.ok(userService.signup(requestDto));
    }
    
}
