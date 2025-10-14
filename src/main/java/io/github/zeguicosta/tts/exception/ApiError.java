package io.github.zeguicosta.tts.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        String method,
        List<String> details
) {
    public static ApiError of(int status, String error, String message, String path, String method, List<String> details) {
        return new ApiError(Instant.now(), status, error, message, path, method, details);
    }
}