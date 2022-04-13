package io.started.security.domain.member.jpa.entity;

import io.started.security.domain.common.Role;
import io.started.security.domain.common.jpa.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {
    @Id
    private Long memberId;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
