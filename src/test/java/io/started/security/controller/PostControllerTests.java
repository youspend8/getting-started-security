package io.started.security.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("게시글 API")
@WebMvcTest(PostController.class)
public class PostControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("게시글 목록 조회")
    void shouldGetOk() throws Exception {
        mockMvc.perform(get("/post"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").isArray())
                .andExpect(jsonPath("message").value(HttpStatus.OK.getReasonPhrase()))
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    @DisplayName("게시글 상세 조회")
    void shouldGetOk(long postId) throws Exception {
        mockMvc.perform(get("/post/{postId}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 작성")
    void shouldPostCreated() throws Exception {
        mockMvc.perform(post("/post"))
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
                .andExpect(jsonPath("$.message").value(HttpStatus.NO_CONTENT.getReasonPhrase()))
                .andDo(print());
    }
}
