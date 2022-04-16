package io.started.security.domain.post.service;

import io.started.security.domain.post.dto.PostDto;
import io.started.security.domain.post.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostListService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<PostDto> fetch(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(PostDto::valueOf);
    }
}
