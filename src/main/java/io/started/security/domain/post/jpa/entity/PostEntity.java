package io.started.security.domain.post.jpa.entity;

import io.started.security.domain.comment.jpa.entity.CommentEntity;
import io.started.security.domain.common.jpa.entity.BaseTimeEntity;
import io.started.security.domain.member.jpa.entity.MemberEntity;
import lombok.*;

import javax.persistence.*;
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
    private Long postId;
    private String content;
    private boolean remove;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<CommentEntity> comments;
}
