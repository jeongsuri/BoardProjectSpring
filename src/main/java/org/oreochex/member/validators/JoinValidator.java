package org.oreochex.member.validators;

import org.oreochex.member.controllers.RequestJoin;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class JoinValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) { //검증하고자 하는 커맨드 객체를 제한함.
        return clazz.isAssignableFrom(RequestJoin.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        /**
         * 1. 이미 가입된 회원인지 체크
         * 2. 비밀번호, 비밀번호 확인 일치 여부
         * 3. 비밀번호 복잡성 체크
         * 4. 연락처 형식 체크
         *
         */

        RequestJoin form = (RequestJoin) target;
        String email = form.getEmail();
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String mobile = form.getMobile();

        //2. 비밀번호, 비밀번호 확인 일치 여부
        if(!password.equals(confirmPassword)) {
            errors.rejectValue("confirmPassword", "Mismatch.password");
        }

        //3. 비밀번호 복잡성 체크 - 알파벳 대소문자 각각1개 이상, 숫자1개 이상, 특수문자 1개 이상.

    }
}
