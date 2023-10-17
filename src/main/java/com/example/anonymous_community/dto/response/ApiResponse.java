package com.example.anonymous_community.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ControllerAdvice
public class ApiResponse<T> {
    private int status;
    private String message;
    private T result;

    // getters and setters

    public static <T> ApiResponse<T> success(int status, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(status);
        response.setMessage("성공");
        response.setResult(data);
        return response;
    }

    public static <T> ApiResponse<T> fail(int status) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus(status);
        response.setMessage("실패");
        response.setResult((T) "fail");
        return response;
    }

    //Controller에 들어가기 전 오류가 발생할시 잡아주는 예외 처리
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(ApiResponse.fail(HttpStatus.UNAUTHORIZED.value()));
    }
}

