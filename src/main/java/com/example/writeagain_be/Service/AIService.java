package com.example.writeagain_be.Service;

import com.example.writeagain_be.DTO.AIRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AIService {
    private final WebClient webClient;
    private final String aiServerUrl;  // final로 변경하여 생성자에서 초기화

    // 명시적인 생성자 추가 (WebClient와 AI 서버 URL을 주입)
    public AIService(WebClient.Builder webClientBuilder, @Value("${ai.server.url}") String aiServerUrl) {
        this.webClient = webClientBuilder.baseUrl(aiServerUrl).build();
        this.aiServerUrl = aiServerUrl;
    }

    public String sendToAI(Long userId, AIRequest aiRequest) {
        System.out.println("[DEBUG] userId: " + userId);
        System.out.println(" [DEBUG] title: " + aiRequest.getTitle());
        System.out.println(" [DEBUG] memo: " + aiRequest.getMemo());

        // AI 서버 없이도 백엔드가 정상 동작하는지 확인하기 위해 더미 응답 반환
        return "AI 서버 응답 (테스트용)";
       /* AI 서버 연결 안 되었기 때문에 일단 주석 처리함
       return webClient.get()
                .uri("/users/" + userId + "/articles")  // baseUrl이 설정되어 있으므로 엔드포인트만 추가
                .bodyValue(aiRequest)  // 사용자가 입력한 제목 & 메모를 AI 서버로 전달
                .retrieve()
                .bodyToMono(String.class)  // AI 서버 응답을 문자열로 변환 (Markdown 그대로 유지됨)
                .block();  // 동기 처리 (필요시 비동기 변경 가능)
                */
    }


}
