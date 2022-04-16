package io.started.security.domain.post.service;

import io.started.security.domain.post.dto.PostDto;
import io.started.security.domain.post.jpa.entity.PostEntity;
import io.started.security.domain.post.jpa.repository.PostRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@DisplayName("게시글 서비스")
@SpringBootTest
public class PostServiceTests {
    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @DisplayName("게시글 상세 조회")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void shouldFetchPost(long postId) {
        PostEntity mock = Mockito.mock(PostEntity.class);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(mock));
        PostDto post = postService.fetch(postId);
        assertEquals(PostDto.valueOf(mock).toString(), post.toString());
    }

    @DisplayName("게시글 생성")
    @Test
    void shouldCreatePost() {
        PostEntity mock = Mockito.mock(PostEntity.class);
        when(postRepository.save(any()))
                .thenReturn(mock);
        assertDoesNotThrow(() -> postService.create(Mockito.mock(PostDto.class)));
    }

    @DisplayName("게시글 삭제")
    @ParameterizedTest
    @ValueSource(ints = 1)
    void shouldRemoveSoftPost(long postId) {
        PostEntity mock = Mockito.mock(PostEntity.class);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(mock));
        assertDoesNotThrow(() -> postService.removeSoft(postId));
    }
}
