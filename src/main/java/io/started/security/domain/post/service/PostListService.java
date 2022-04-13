package io.started.security.domain.post.service;

import io.started.security.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostListService {
    @Transactional(readOnly = true)
    public Page<PostDto> fetch() {
        return null;
    }
}
