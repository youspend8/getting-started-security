package io.started.security.domain.comment.dto;

import io.started.security.domain.comment.jpa.entity.CommentEntity;
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

    public CommentEntity asNewEntity() {
        return CommentEntity.builder()
                .content(content)
                .build();
    }
}
