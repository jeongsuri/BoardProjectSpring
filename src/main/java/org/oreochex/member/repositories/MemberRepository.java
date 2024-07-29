package org.oreochex.member.repositories;

import org.oreochex.member.entities.Member;
import org.oreochex.member.entities.QMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

    @EntityGraph(attributePaths = "authorities") //JPA에서 엔티티 그래프를 정의하여 특정 쿼리 시에 연관된 엔티티를 즉시 로딩(Eager Fetch)하도록 하는 방법
    Optional<Member> findByEmail(String username);

    default boolean exists(String email){
        QMember member = QMember.member;
        return exists(member.email.eq(email));
    }
}
