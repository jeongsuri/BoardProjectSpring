package org.oreochex.member.repositories;

import org.oreochex.member.entities.Authorities;
import org.oreochex.member.entities.AuthoritiesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AuthoritiesRepository extends JpaRepository<Authorities, AuthoritiesId>, QuerydslPredicateExecutor<Authorities> {
}
