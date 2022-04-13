package io.started.security.domain.comment.service;

import io.started.security.domain.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Transactional
    public void create(long postId, CommentDto commentDto) {

    }

    @Transactional
    public void modify(long postId, long commentId) {

    }

    @Transactional
    public void removeSoft(long commentId) {

    }
}
