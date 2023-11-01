package com.example.anonymous_community.global.common;


import com.example.anonymous_community.global.exception.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ControllerAdvice
@Builder
public class ApiResponse<T> {
    private StatusCode statusCode; // "success" 또는 "fail"
    private int httpCode;   // HTTP 상태 코드 또는 사용자 정의 코드
    private T data; //API에서 반환되는 데이터



    public static <T> ApiResponse<T> success(int httpCode, T data) {
        return new ApiResponse<>(StatusCode.SUCCESS, httpCode, data);
    }

    public static ApiResponse<String> fail(int httpCode, String message) {
        return new ApiResponse<>(StatusCode.FAIL, httpCode, message);
    }


}

