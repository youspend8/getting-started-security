package io.started.security.controller;

import io.started.security.domain.post.dto.PostDto;
import io.started.security.domain.post.service.PostListService;
import io.started.security.domain.post.service.PostService;
import io.started.security.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("게시글 API")
@WebMvcTest(PostController.class)
public class PostControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private PostListService postListService;

    @Test
    @DisplayName("게시글 목록 조회")
    void shouldGetOk() throws Exception {
        when(postListService.fetch(any())).thenReturn(Page.empty());
        mockMvc.perform(get("/post")
                        .param("page", "0")
                        .param("offset", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    @DisplayName("게시글 상세 조회")
    void shouldGetOk(long postId) throws Exception {
        PostDto postDto = getPostSample();
        when(postService.fetch(anyLong())).thenReturn(postDto);
        mockMvc.perform(get("/post/{postId}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 작성")
    void shouldPostCreated() throws Exception {
        mockMvc.perform(post("/post")
                        .content(JsonUtil.asJson(getPostSample()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.message").value(HttpStatus.CREATED.getReasonPhrase()))
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    @DisplayName("게시글 삭제")
    void shouldDeleteNoContent(long postId) throws Exception {
        mockMvc.perform(delete("/post/{postId}", postId))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    private PostDto getPostSample() {
        return PostDto.builder()
                .content("게시글 내용")
                .memberName("게시글 작성자 이름")
                .build();
    }
}
