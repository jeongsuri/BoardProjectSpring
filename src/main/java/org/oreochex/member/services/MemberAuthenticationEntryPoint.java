package org.oreochex.member.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        /** 회원 전용 페이지로 접근한 경우 ->로그인 -> 그전에 요청했던 페이지로 이동
         * 관리자 페이지로 접근한 경우 - 응답 코드 401, 에러페이지 출력
         */
        String uri = request.getRequestURI(); //사용자가 접근한 uri 조회
        if(uri.contains("/admin")) { //관리자페이지로 접근하면
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED); //404에러
        }else{ //회원전용 페이지
            String qs = request.getQueryString(); //사용자가 요청한 쿼리스트링을 조회
            String redirectUrl = uri.replace(request.getContextPath(), "");
            if(StringUtils.hasText(qs)) {
                redirectUrl += "?" + qs;
            }

            response.sendRedirect(request.getContextPath() + "/member/login?redirectUrl=" + redirectUrl);
        }

    }
}
