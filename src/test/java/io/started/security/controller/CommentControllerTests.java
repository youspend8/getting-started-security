package io.started.security.controller;

import io.started.security.domain.comment.dto.CommentDto;
import io.started.security.domain.comment.service.CommentService;
import io.started.security.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("댓글 API")
@WebMvcTest(CommentController.class)
public class CommentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @ParameterizedTest
    @ValueSource(ints = 1)
    @DisplayName("댓글 작성")
    void shouldPostCreated(long postId) throws Exception {
        mockMvc.perform(post("/comment")
                        .param("postId", Long.toString(postId))
                        .content(JsonUtil.asJson(getCommentSample()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.message").value(HttpStatus.CREATED.getReasonPhrase()))
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    @DisplayName("댓글 삭제")
    void shouldDeleteNoContent(long commentId) throws Exception {
        mockMvc.perform(delete("/comment/{commentId}", commentId))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @ParameterizedTest
    @ValueSource(longs = 1)
    @DisplayName("댓글 수정")
    void shouldPutCreated(long commentId) throws Exception {
        mockMvc.perform(put("/comment/{commentId}", commentId)
                        .content(JsonUtil.asJson(getCommentSample()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(HttpStatus.CREATED.getReasonPhrase()))
                .andDo(print());
    }

    private CommentDto getCommentSample() {
        return CommentDto.builder()
                .content("댓글 내용")
                .memberName("사용자 이름")
                .build();
    }
}
