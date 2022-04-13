package io.started.security.domain.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String message;
    private T data;

    public static ApiResponse<Void> success() {
        return emptyOf(HttpStatus.OK);
    }

    public static <T> ApiResponse<T> valueOf(T data) {
        return of(HttpStatus.OK, data);
    }

    public static ApiResponse<Void> error(HttpStatus httpStatus) {
        return emptyOf(httpStatus);
    }

    public static ApiResponse<Void> emptyOf(HttpStatus httpStatus) {
        return new ApiResponse<>(httpStatus.getReasonPhrase(), null);
    }

    private static <T> ApiResponse<T> of(HttpStatus httpStatus, T data) {
        return new ApiResponse<>(httpStatus.getReasonPhrase(), data);
    }
}
