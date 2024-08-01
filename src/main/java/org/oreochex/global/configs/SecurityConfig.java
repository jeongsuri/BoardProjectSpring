package org.oreochex.global.configs;

import org.oreochex.member.services.LoginFailureHandler;
import org.oreochex.member.services.LoginSuccessHandler;
import org.oreochex.member.services.MemberAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         * 로그인 처리를 넘길 페이지 설정
         * 개발자마다 파라미터명이 다르기 때문에 파라미트명 설정.
         * 로그인 성공 후 페이지 설정
         * 로그인 실패 후 페이지 설정
         */
        /* 로그인, 로그아웃 S */
        http.formLogin(f -> {
            f.loginPage("/member/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler());
        });


        http.logout(f -> {
            f.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/member/login");

        });
        /* 로그인, 로그아웃 E */

        /*인가(접근 통제) 설정 S*/
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/mypage/**").authenticated() //회원전용
                    .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")//어드민전용
                    .anyRequest().permitAll(); //모든페이지를 열고 일부만 회원 or 어드민
        });

        //로그인 회원가입만 전체 허용하고, 그 외 페이지는 모두 회원만 가능
        /*
        http.authorizeHttpRequests(c -> {
            c.requestMatchers("/member/**").anonymous()
                    .requestMatchers("/admin/**").hasAnyAuthority()
                    .anyRequest().authenticated();
                }
         */

        http.exceptionHandling(c-> {
           c.authenticationEntryPoint(new MemberAuthenticationEntryPoint());
        });


        /*인가(접근 통제) 설정 E*/





        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}