package com.example.remind_me_server.auth.config;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.remind_me_server.auth.application.LoginUser;
import com.example.remind_me_server.global.error.exception.CustomJwtException;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // @LoginUser 어노테이션이 붙어 있고 타입이 Long인 경우에만 동작
        return parameter.hasParameterAnnotation(LoginUser.class) 
               && parameter.getParameterType().equals(Long.class);
    }
    
    @Override
    @Nullable
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Long) {
            return authentication.getPrincipal(); // 여기서 Long userId가 반환됨
        }
        throw new CustomJwtException("USER_NOT_FOUND");
    }
}