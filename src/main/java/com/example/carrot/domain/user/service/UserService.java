package com.example.carrot.domain.user.service;

import com.example.carrot.domain.user.dto.UserRequestDto;
import com.example.carrot.domain.user.dto.UserResponseDto;
import com.example.carrot.domain.user.repository.UserRepository;
import com.example.carrot.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserResponseDto signup(UserRequestDto requestDto) {
        if(userRepository.existsByUsername(requestDto.getUsername())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        User user = requestDto.toUser(bCryptPasswordEncoder);
        return UserResponseDto.of(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new InternalAuthenticationServiceException(username));
    }
}
