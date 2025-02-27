package com.example.writeagain_be.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIRequest {
    private String title;  // 사용자가 입력한 제목
    private String memo;   // 사용자가 입력한 메모
}

