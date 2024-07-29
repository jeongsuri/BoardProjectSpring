package org.oreochex.member.entities;

import jakarta.persistence.*;
import lombok.*;
import org.oreochex.global.entities.BaseEntity;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long seq;

    @Column(length = 65, unique = true, nullable = false)
    private String email;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String userName;

    @Column(length = 15, nullable = false)
    private String mobile;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Authorities> authorities;
}
