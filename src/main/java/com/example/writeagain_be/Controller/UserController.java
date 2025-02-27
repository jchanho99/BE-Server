package com.example.writeagain_be.Controller;

import com.example.writeagain_be.DTO.UserRequest;
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
    // @Autowired private AIService aiService;
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
    /*@PostMapping("/users/{userId}/articles")
    public ResponseEntity<String> sendBlogDataToAI(@PathVariable Long userId) {
        String response = aiService.sendRecentBlogsToAI(userId);
        return ResponseEntity.ok(response);
    }
    //프론트에서 제목 & 메모 데이터를 받아 AI 서버로 전달
    @GetMapping("/users/{userId}/articles")
    public ResponseEntity<String> sendUserInputToAI(@PathVariable Long userId, @RequestParam String title, @RequestParam String memo) {
        String response = aiService.sendUserInputToAI(userId, title, memo);
        return ResponseEntity.ok(response);
    }
    //AI 서버에서 생성된 블로그 글 받아서 프론트로 반환
    @GetMapping("/users/{userId}/articles")
    public ResponseEntity<String> getAIGeneratedArticle(@PathVariable Long userId) {
        String aiGeneratedArticle = aiService.getGeneratedArticle(userId);
        return ResponseEntity.ok(aiGeneratedArticle);
    }

     */
}
