package io.started.security.domain.comment.jpa.repository;

import io.started.security.domain.comment.jpa.entity.CommentEntity;
import io.started.security.domain.common.Role;
import io.started.security.domain.member.jpa.entity.MemberEntity;
import io.started.security.domain.member.jpa.repository.MemberRepository;
import io.started.security.domain.post.jpa.entity.PostEntity;
import io.started.security.domain.post.jpa.repository.PostRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("댓글 Repository 테스트")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentRepositoryTests {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(getMemberSample());
        postRepository.save(getPostSample());
        commentRepository.save(getCommentSample());
    }

    @DisplayName("댓글 단건 조회")
    @Order(1)
    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldFindCommentById(long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseGet(CommentEntity::new);
        assertEquals(comment.toString(), getCommentSample().toString());
    }

    @DisplayName("댓글 단건 삭제")
    @Order(2)
    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldDeleteCommentById(long commentId) {
        assertDoesNotThrow(() -> commentRepository.deleteById(commentId));
    }

    private CommentEntity getCommentSample() {
        final long commentId = 1L;
        return CommentEntity.builder()
                .commentId(commentId)
                .content("1번 게시글의 첫번째 댓글의 이런저런 내용...")
                .member(getMemberSample())
                .build();
    }

    private PostEntity getPostSample() {
        final long postId = 1L;
        return PostEntity.builder()
                .postId(postId)
                .member(getMemberSample())
                .content("게시글의 이런저런 내용...")
                .build();
    }

    private MemberEntity getMemberSample() {
        final long memberId = 1L;
        return MemberEntity.builder()
                .memberId(memberId)
                .name("관리자")
                .role(Role.ADMINISTRATOR)
                .build();
    }
}
