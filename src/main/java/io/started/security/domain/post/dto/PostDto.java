package io.started.security.domain.post.dto;

import io.started.security.domain.comment.dto.CommentDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PostDto {
    private long id;
    private String content;
    private String memberName;
    private List<CommentDto> comments;
}
