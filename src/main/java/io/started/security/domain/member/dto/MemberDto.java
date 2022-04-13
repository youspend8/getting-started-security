package io.started.security.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.started.security.domain.common.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberDto {
    private Long memberId;
    private String name;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @JsonIgnore
    private String password;
}
