package io.started.security.domain.comment.jpa.entity;

import io.started.security.domain.common.jpa.entity.BaseTimeEntity;
import io.started.security.domain.member.jpa.entity.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String content;
    private boolean remove;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    /**
     * 댓글을 삭제 상태로 변경합니다.
     */
    public void delete() {
        remove = true;
    }
}
