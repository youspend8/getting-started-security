package io.started.security.domain.post.dto;

import io.started.security.domain.comment.dto.CommentDto;
import io.started.security.domain.post.jpa.entity.PostEntity;
import lombok.*;
import org.springframework.beans.BeanUtils;

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

    public static PostDto valueOf(PostEntity postEntity) {
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(postEntity, postDto);
        return postDto;
    }

    public PostEntity asNewEntity() {
        return PostEntity.builder()
                .content(content)
                .build();
    }
}
