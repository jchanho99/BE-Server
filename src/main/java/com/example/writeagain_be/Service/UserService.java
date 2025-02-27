package com.example.writeagain_be.Service;

import com.example.writeagain_be.DTO.UserRequest;
import com.example.writeagain_be.Repository.UserRepository;
import com.example.writeagain_be.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 정보 저장
    @Transactional
    public User saveUser(Long userId, UserRequest request) {
        User user = userRepository.findById(userId).orElse(new User());
        user.setUsername(request.getUsername());
        user.setBlogType(request.getBlogType());
        return userRepository.save(user);
    }


    // userId로 username 가져오기
    public String getUsernameById(Long userId) {
        return userRepository.findById(userId)
                .map(User::getUsername)
                .orElse("Unknown User"); // 예외 대신 기본 문자열 반환
    }
}
