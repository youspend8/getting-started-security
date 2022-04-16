package io.started.security.controller;

import io.started.security.domain.comment.dto.CommentDto;
import io.started.security.domain.comment.service.CommentService;
import io.started.security.domain.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comment", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    private final CommentService commentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> post(
            @RequestParam("postId") long postId,
            @RequestBody CommentDto commentDto) {
        commentService.create(postId, commentDto);
        return ResponseEntity
                .created(URI.create(String.format("/post/%d", postId)))
                .body(ApiResponse.emptyOf(HttpStatus.CREATED));
    }

    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable("commentId") long commentId) {
        commentService.removeSoft(commentId);
        return ResponseEntity
                .noContent().build();
    }

    @PutMapping(path = "/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> put(
            @PathVariable("commentId") long commentId,
            @RequestBody CommentDto commentDto) {
        commentService.modify(commentId, commentDto);
        return ResponseEntity
                .created(URI.create(String.format("/commentId/%d", commentId)))
                .body(ApiResponse.emptyOf(HttpStatus.CREATED));
    }
}
