package org.oreochex.global.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.oreochex.global.exceptions.script.AlertException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public interface ExceptionProcessor {
    @ExceptionHandler
    default ModelAndView errorHandler(Exception e, HttpServletRequest request){

        ModelAndView mv = new ModelAndView();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String tpl = "error/error";

        if(e instanceof CommonException commonException) {
            status = commonException.getStatus();

            if(e instanceof AlertException){
                tpl="common/_execute_script";
                String script = String.format("alert('%s');", e.getMessage());
            }

        }

        String url = request.getRequestURI();
        String qs = request.getQueryString();

        if(StringUtils.hasText(qs)) url += "?" + qs;

        mv.addObject("message", e.getMessage());
        mv.addObject("status", status.value());
        mv.addObject("method", request.getMethod());
        mv.addObject("path", url);
        mv.setStatus(status);
        mv.setViewName(tpl);

        return mv;
    }
}
