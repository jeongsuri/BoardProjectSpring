package org.oreochex.global.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter @Setter
public class CommonException extends RuntimeException {
    private boolean errorCode;
    private HttpStatus status;
    private Map<String, List<String>> errorMessages;

    public CommonException(String message){
        this(message, HttpStatus.INTERNAL_SERVER_ERROR); //기본 응답 에러코드 500
    }

    public CommonException(String meesage, HttpStatus status) {
        super(meesage);
        this.status = status;
    }
}
