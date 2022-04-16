package io.started.security.domain.comment.service;

import io.started.security.domain.comment.dto.CommentDto;
import io.started.security.domain.comment.jpa.entity.CommentEntity;
import io.started.security.domain.comment.jpa.repository.CommentRepository;
import io.started.security.domain.post.jpa.entity.PostEntity;
import io.started.security.domain.post.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void create(long postId, CommentDto commentDto) {
        //  TODO : 추후에 사용자 인증(Authentication) 정보를 활용하여 회원 엔티티 값을 설정해주도록 합니다.
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow();
        CommentEntity commentEntity = commentDto.asNewEntity();
        postEntity.addComment(commentEntity);
    }

    @Transactional
    public void modify(long commentId, CommentDto commentDto) {
        //  TODO : 추후에 사용자 인증(Authentication) 및 인가(Authorization) 정보를 활용하여 수정 권한을 보유한 회원인지 확인합니다.
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow();
        commentEntity.setContent(commentDto.getContent());
    }

    @Transactional
    public void removeSoft(long commentId) {
        //  TODO : 추후에 사용자 인증(Authentication) 및 인가(Authorization) 정보를 활용하여 삭제 권한을 보유한 회원인지 확인합니다.
        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow();
        commentEntity.delete();
    }
}
