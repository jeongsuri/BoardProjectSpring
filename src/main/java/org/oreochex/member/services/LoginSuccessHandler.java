package org.oreochex.member.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    //로그인 성공시에 유입되는 메서드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //세션에 남아 있는 requestLogin삭제
        HttpSession session = request.getSession();
        session.removeAttribute("requestLogin");

        //로그인 성공시 - redirectUrl 이 있으면 해당 주소로 이동, 아니면 메인 페이지 이동
        String redirectUrl = request.getParameter("redirectUrl");
        redirectUrl = StringUtils.hasText(redirectUrl) ? redirectUrl.trim() : "/";

        response.sendRedirect(request.getContextPath() + redirectUrl);
    }
}
