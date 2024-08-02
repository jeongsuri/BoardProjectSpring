package org.oreochex.global.advices;

import lombok.RequiredArgsConstructor;
import org.oreochex.member.MemberUtil;
import org.oreochex.member.entities.Member;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@ControllerAdvice("org.oreochex")
public class CommonControllerAdvice {

    private final MemberUtil memberUtil;

    @ModelAttribute("loggedMember")
    public Member loogedMember(){
        return memberUtil.getMember();
    }

    @ModelAttribute("isLogin")
    public boolean isLogin(){
        return memberUtil.isLogin();
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin(){
        return memberUtil.isAdmin();
    }
}
