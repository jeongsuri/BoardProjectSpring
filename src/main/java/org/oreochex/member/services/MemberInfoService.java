package org.oreochex.member.services;

import lombok.RequiredArgsConstructor;
import org.oreochex.member.MemberInfo;
import org.oreochex.member.constants.Authority;
import org.oreochex.member.entities.Authorities;
import org.oreochex.member.entities.Member;
import org.oreochex.member.repositories.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        List<Authorities> tmp = Objects.requireNonNullElse(member.getAuthorities(),
                List.of(Authorities.builder().member(member).authority(Authority.USER).build()));

        List<SimpleGrantedAuthority> authorities = tmp.stream()
                .map(a -> new SimpleGrantedAuthority(a.getAuthority().name()))
                .toList();


        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .member(member)
                .authorities(authorities)
                .build();
    }
}