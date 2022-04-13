package io.started.security.controller;

import io.started.security.domain.common.dto.ApiResponse;
import io.started.security.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    @GetMapping
    public ResponseEntity<ApiResponse<Page<PostDto>>> get() {
        return ResponseEntity.ok(
                ApiResponse.valueOf(null));
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<PostDto>> get(
            @PathVariable("postId") long postId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(null));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> post() {
        long postId = 0L;
        return ResponseEntity
                .created(URI.create(String.format("/post/%d", postId)))
                .body(ApiResponse.emptyOf(HttpStatus.CREATED));
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable("postId") long postId) {
        return ResponseEntity
                .noContent().build();
    }
}
