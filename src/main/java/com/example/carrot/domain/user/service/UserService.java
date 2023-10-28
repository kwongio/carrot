package com.example.carrot.domain.user.service;

import com.example.carrot.domain.user.domain.User;
import com.example.carrot.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        validateDuplicateUser(user);

        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        Optional<User> findUser = userRepository.findByUsername(user.getUsername());
        if(findUser.isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
