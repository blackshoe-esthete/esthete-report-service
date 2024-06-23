package org.example.blackshoe.esthetereportservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReportErrorResult {
    REQUIRED_VALUE(HttpStatus.BAD_REQUEST, "필수 값이 누락되었습니다."); //400

    private final HttpStatus httpStatus;
    private final String message;
}
