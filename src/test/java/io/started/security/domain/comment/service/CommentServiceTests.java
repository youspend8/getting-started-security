package io.started.security.domain.comment.service;

import io.started.security.domain.comment.dto.CommentDto;
import io.started.security.domain.comment.jpa.entity.CommentEntity;
import io.started.security.domain.comment.jpa.repository.CommentRepository;
import io.started.security.domain.post.jpa.entity.PostEntity;
import io.started.security.domain.post.jpa.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("댓글 서비스")
@SpringBootTest
public class CommentServiceTests {
    @Autowired
    private CommentService commentService;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private CommentRepository commentRepository;

    @DisplayName("댓글 생성")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void shouldCreateComment(long postId) {
        PostEntity mockPost = mock(PostEntity.class);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(mockPost));
        assertDoesNotThrow(() ->
                commentService.create(postId, mock(CommentDto.class)));
    }

    @DisplayName("댓글 수정")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void shouldModifyPost(long commentId) {
        CommentEntity mock = mock(CommentEntity.class);
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(mock));
        assertDoesNotThrow(() ->
                commentService.modify(commentId, mock(CommentDto.class)));
    }

    @DisplayName("댓글 삭제")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void shouldRemoveSoftComment(long commentId) {
        CommentEntity mock = mock(CommentEntity.class);
        when(commentRepository.findById(anyLong())).thenReturn(Optional.of(mock));
        assertDoesNotThrow(() -> commentService.removeSoft(commentId));
    }
}
