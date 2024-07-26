package org.oreochex.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //security 설정 해체
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //인코더 방식 설정
        return new BCryptPasswordEncoder(); //BCrypt형태로 Encoder수행
    }
}
