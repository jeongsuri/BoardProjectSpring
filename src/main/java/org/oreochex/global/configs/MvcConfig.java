package org.oreochex.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing //Spring Boot 애플리케이션에서 JPA 감사(Auditing) 기능을 활성화합니다. 주로 @CreatedDate와 @LastModifiedDate와 같은 필드를 자동으로 관리하기 위해 사용됩니다.
public class MvcConfig implements WebMvcConfigurer {

    /**
     * <inpu type="hidden" name="_method" value="PATCH"></inpu> -> PATCH 방식으로 요청
     * ?_method=DELETE
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
