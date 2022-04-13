package io.started.security.domain.post.service;

import io.started.security.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    @Transactional(readOnly = true)
    public PostDto fetch(long postId) {
        return null;
    }

    @Transactional
    public void create(PostDto postDto) {

    }

    @Transactional
    public void removeSoft(long postId) {

    }
}
