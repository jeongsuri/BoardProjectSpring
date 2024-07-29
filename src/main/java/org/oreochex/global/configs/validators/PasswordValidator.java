package org.oreochex.global.configs.validators;

public interface PasswordValidator {

    /**
     * 알파벳 복잡성 체크
     * @param password
     * @param caseInsensitive - false : 대소문자 각각 1개씩 이상 포함, true : 대소문자 구분 X
     * @return
     */
    default boolean alphaCheck(String password, boolean caseInsensitive) {
        if (caseInsensitive) { //대소문자 구분없이 알파벳 체크
            return password.matches(".*[a-zA-Z]+.*");
        }
        return password.matches(".*[a-z]+.*") && password.matches(".*[A-Z]+.*");
    }

    /**
     * 숫자 복잡성 체크
     * @param password
     * @return
     */
    default boolean numberCheck(String password){
        return password.matches(".*\\d+.*");
    }

    /**
     * 특수문자 복잡성 체크
     * @param password
     * @return
     */
    default boolean specialCharCheck(String password){
        String pattern = "[^\\w\\s]";
        return password.matches(pattern);
    }
}
