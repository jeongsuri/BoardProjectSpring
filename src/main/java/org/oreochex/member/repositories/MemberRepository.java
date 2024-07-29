package org.oreochex.member.repositories;

import org.oreochex.member.entities.Member;
import org.oreochex.member.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    default boolean exists(String email){
        QMember member = QMember.member;
        return exists(member.email.eq(email));
    }
}
