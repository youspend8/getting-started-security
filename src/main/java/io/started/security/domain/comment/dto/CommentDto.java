package io.started.security.domain.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CommentDto {
    private long id;
    private String content;
    private String memberName;
    private LocalDateTime createdAt;
}
