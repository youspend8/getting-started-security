package io.started.security.domain.post.service;

import io.started.security.domain.post.dto.PostDto;
import io.started.security.domain.post.jpa.entity.PostEntity;
import io.started.security.domain.post.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostDto fetch(long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow();
        return PostDto.valueOf(postEntity);
    }

    @Transactional
    public long create(PostDto postDto) {
        //  TODO : 추후에 사용자 인증(Authentication) 정보를 활용하여 회원 엔티티 값을 설정해주도록 합니다.
        PostEntity postEntity = postDto.asNewEntity();
        return postRepository.save(postEntity).getPostId();
    }

    @Transactional
    public void removeSoft(long postId) {
        //  TODO : 추후에 사용자 인증(Authentication) 및 인가(Authorization) 정보를 활용하여 삭제 권한을 보유한 회원인지 확인합니다.
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow();
        postEntity.delete();
    }
}
