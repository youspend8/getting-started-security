package io.started.security.domain.member.jpa.repository;

import io.started.security.domain.common.Role;
import io.started.security.domain.member.jpa.entity.MemberEntity;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("회원 Repository 테스트")
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository.save(getMemberSample());
    }

    @DisplayName("회원 단건 조회")
    @Order(1)
    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldFindMemberById(long memberId) {
        MemberEntity member = memberRepository.findById(memberId)
                .orElseGet(MemberEntity::new);
        assertEquals(member.toString(), getMemberSample().toString());
    }

    @DisplayName("회원 단건 삭제")
    @Order(2)
    @ParameterizedTest
    @ValueSource(longs = 1)
    void shouldDeleteMemberById(long memberId) {
        assertDoesNotThrow(() -> memberRepository.deleteById(memberId));
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
