package io.started.security.domain.post.jpa.repository;

import io.started.security.domain.common.Role;
import io.started.security.domain.member.jpa.entity.MemberEntity;
import io.started.security.domain.member.jpa.repository.MemberRepository;
import io.started.security.domain.post.jpa.entity.PostEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("게시글 Repository 테스트")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(getMemberSample());
        postRepository.save(getPostSample());
    }

    @DisplayName("게시글 단건 조회")
    @Order(1)
    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldFindPostById(long postId) {
        PostEntity compare = getPostSample();
        PostEntity post = postRepository.findById(postId)
                .orElseGet(PostEntity::new);

        assertEquals(compare.toString(), post.toString());
    }

    @DisplayName("게시글 단건 삭제")
    @Order(2)
    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldDeletePostById(long postId) {
        assertDoesNotThrow(() -> postRepository.deleteById(postId));
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
