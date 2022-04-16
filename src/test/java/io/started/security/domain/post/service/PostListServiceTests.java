package io.started.security.domain.post.service;

import io.started.security.domain.post.dto.PostDto;
import io.started.security.domain.post.jpa.entity.PostEntity;
import io.started.security.domain.post.jpa.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("게시글 목록 서비스")
@SpringBootTest
public class PostListServiceTests {
    @Autowired
    private PostListService postListService;

    @MockBean
    private PostRepository postRepository;

    @DisplayName("목록 조회")
    @Test
    void shouldFetchPosts() {
        Page<PostEntity> mockList = getPostPageSample();
        when(postRepository.findAll(any(Pageable.class))).thenReturn(mockList);
        Page<PostDto> posts = postListService.fetch(PageRequest.of(0, 5));
        assertEquals(mockList.getSize(), posts.getSize());
    }

    private Page<PostEntity> getPostPageSample() {
        List<PostEntity> mockList = new ArrayList<>();
        mockList.add(mock(PostEntity.class));
        mockList.add(mock(PostEntity.class));
        mockList.add(mock(PostEntity.class));
        mockList.add(mock(PostEntity.class));
        mockList.add(mock(PostEntity.class));
        return new PageImpl<>(mockList);
    }
}
