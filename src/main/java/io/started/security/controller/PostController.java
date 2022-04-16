package io.started.security.controller;

import io.started.security.domain.comment.dto.Paging;
import io.started.security.domain.common.dto.ApiResponse;
import io.started.security.domain.post.dto.PostDto;
import io.started.security.domain.post.service.PostListService;
import io.started.security.domain.post.service.PostService;
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
    private final PostService postService;
    private final PostListService postListService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PostDto>>> get(
            @ModelAttribute Paging paging) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(postListService.fetch(paging)));
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<PostDto>> get(
            @PathVariable("postId") long postId) {
        return ResponseEntity.ok(
                ApiResponse.valueOf(postService.fetch(postId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<Void>> post(@RequestBody PostDto postDto) {
        long postId = postService.create(postDto);
        return ResponseEntity
                .created(URI.create(String.format("/post/%d", postId)))
                .body(ApiResponse.emptyOf(HttpStatus.CREATED));
    }

    @DeleteMapping(path = "/{postId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable("postId") long postId) {
        postService.removeSoft(postId);
        return ResponseEntity
                .noContent().build();
    }
}
