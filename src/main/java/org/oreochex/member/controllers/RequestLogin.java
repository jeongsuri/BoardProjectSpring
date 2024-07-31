package org.oreochex.member.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestLogin {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private boolean success = true;

    //에러처리를 위한 값
    private String code;
    private String defaultMessage;

    private String redirectUrl; //로그인 성공시에 이동할 주소
}
