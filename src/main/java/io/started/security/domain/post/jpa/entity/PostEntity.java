package io.started.security.domain.post.jpa.entity;

import io.started.security.domain.comment.jpa.entity.CommentEntity;
import io.started.security.domain.common.jpa.entity.BaseTimeEntity;
import io.started.security.domain.member.jpa.entity.MemberEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "post")
public class PostEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String content;
    private boolean remove;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Builder.Default
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id")
    private List<CommentEntity> comments = new ArrayList<>();

    /**
     * 게시글의 댓글을 신규 생성합니다.
     */
    public void addComment(CommentEntity comment) {
        comments.add(comment);
    }

    /**
     * 게시글을 삭제 상태로 변경합니다.
     */
    public void delete() {
        remove = true;
    }
}
