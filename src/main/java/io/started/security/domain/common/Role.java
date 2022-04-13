package io.started.security.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    READER("읽기 권한만 허영된 사용자"),
    WRITER("쓰기 권한만 허용된 사용자"),
    READER_WRITER("읽기/쓰기 권한이 허용된 사용자"),
    ADMINISTRATOR("읽기/쓰기/삭제 권한이 모두 허용된 관리자");

    private final String description;
}
