package org.oreochex.member.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oreochex.member.constants.Authority;
import org.oreochex.member.controllers.RequestJoin;
import org.oreochex.member.entities.Authorities;
import org.oreochex.member.entities.Member;
import org.oreochex.member.repositories.AuthoritiesRepository;
import org.oreochex.member.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(RequestJoin form) {
        Member member = new ModelMapper().map(form, Member.class);
        //ModelMapper : Java에서 객체 간의 매핑을 단순화하고 자동화하는 라이브러리입니다. DTO(Data Transfer Object)와 엔티티 간의 변환을 쉽게 할 수 있게 도와줍니다
        String hash = passwordEncoder.encode(member.getPassword());
        member.setPassword(hash);

        save(member, List.of(Authority.USER));
    }

    public void save(Member member, List<Authority> authorities){
        //휴대전화번호 숫자만 기록
        String mobile = member.getMobile();
        if(StringUtils.hasText(mobile)) {
            mobile = mobile.replaceAll("\\D", "");
            member.setMobile(mobile);
        }

        memberRepository.saveAndFlush(member);
        //권한 추가, 수정 S
        if(authorities != null){
            List<Authorities> items = authoritiesRepository.findByMember(member);
            authoritiesRepository.deleteAll(items);
            authoritiesRepository.flush();

            items = authorities.stream().map( a -> Authorities.builder()
                    .member(member)
                    .authority(a)
                    .build()).toList();

            authoritiesRepository.saveAllAndFlush(items);
        }
        //권한 추가, 수정 E
    }
}
