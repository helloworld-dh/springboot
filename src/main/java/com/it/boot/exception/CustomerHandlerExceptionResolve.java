package com.it.boot.exception;

import lombok.SneakyThrows;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Order(value = Ordered.HIGHEST_PRECEDENCE)    //优先级，数字越小优先级越高
@Component
public class CustomerHandlerExceptionResolve implements HandlerExceptionResolver {
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        httpServletResponse.sendError(511,"自定义错误状态码");
        return new ModelAndView();
    }
}
