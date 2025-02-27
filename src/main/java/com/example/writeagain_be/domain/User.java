package com.example.writeagain_be.domain;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Convert(converter = BlogTypeConverter.class) // Enum 변환기 적용
    @Column(nullable = false)
    private BlogType blogType;

    public enum BlogType {
        VELOG("velog"),
        GITHUB("github"),
        BLOG("blog"),
        TISTORY("tistory");

        private final String value;

        BlogType(String value) {
            this.value = value;
        }

        @JsonValue // JSON 직렬화 시 소문자로 변환
        public String getValue() {
            return value;
        }

        @JsonCreator // JSON 역직렬화 시 소문자로 변환하여 Enum 매핑
        public static BlogType fromValue(String value) {
            for (BlogType type : BlogType.values()) {
                if (type.value.equalsIgnoreCase(value)) { // 대소문자 구분 없이 변환
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid BlogType: " + value);
        }
    }
}
