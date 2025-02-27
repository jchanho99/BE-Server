package com.example.writeagain_be.Controller;

import com.example.writeagain_be.DTO.AIRequest;
import com.example.writeagain_be.DTO.UserRequest;
import com.example.writeagain_be.Service.AIService;
import com.example.writeagain_be.Service.BlogService;
import com.example.writeagain_be.Service.UserService;
import com.example.writeagain_be.domain.BlogPost;
import com.example.writeagain_be.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class UserController {
    // 블로그 사이트 선택 & 닉네임 입력(사용자 정보 저장)
    @Autowired private UserService userService;
    @Autowired private BlogService blogService;
    @Autowired private AIService aiService;
    @PostMapping("/users/{userId}/information")
    public ResponseEntity<User> registerUser(@PathVariable Long userId, @RequestBody UserRequest request) {
        User user = userService.saveUser(userId, request);
        return ResponseEntity.ok(user);
    }


    // 사용자 최신 블로그 데이터 가져오기
    @GetMapping("/users/{userId}/blogs")
    public ResponseEntity<List<BlogPost>> getRecentBlogs(@PathVariable Long userId) {
        List<BlogPost> blogs = blogService.fetchRecentPosts(userId);
        return ResponseEntity.ok(blogs);
    }
    //최신 블로그 글 데이터 AI 서버로 전달
    @PostMapping("/{userId}/users/articles")
    public ResponseEntity<String> sendBlogDataToAI(@PathVariable Long userId) {
        List<BlogPost> blogs = blogService.fetchRecentPosts(userId); // 최신 블로그 데이터 가져오기
        String response = aiService.sendBlogDataToAI(userId, blogs); // AI 서버로 데이터 전달
        return ResponseEntity.ok(response);
    }

    //프론트에서 제목 & 메모 데이터를 받아 AI 서버로 전달
    @PostMapping("/{userId}/articles")
    public ResponseEntity<String> generateBlogPost(
            @PathVariable Long userId,
            @RequestBody AIRequest aiRequest
    ) {
        String generatedArticle = aiService.sendToAI(userId, aiRequest);  // userId 제거
        return ResponseEntity.ok(generatedArticle);
    }
}
