package cn.unicom.microservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler
    public String ErrorHandler(AuthorizationException e) {
        log.info("没有通过权限验证！");
        return "redirect:/403";
    }
}