package com.example.carrot.domain.user.controller;

import com.example.carrot.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    @GetMapping("/user/signup")
    public ResponseEntity<?> signup(){
        userService.signup();
        return ResponseEntity.ok("");
    }

}
