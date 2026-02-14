package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //1.获取请求路径
//        String requestURI = request.getRequestURI(); //URI不包含协议、IP、端口，只包含后面的请求路径
//
//        //2.判断是否为登录请求（是否包含login），是则放行
//        if (requestURI.contains("/login")){
//            log.info("登录请求，放行");
//            return true;
//        }

        //3.不是则获取请求头中的 token
        String token = request.getHeader("token");

        //4.判断token是否存在，不存在则返回错误信息（状态码401）
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401状态码");
            response.setStatus(401); //设置状态码
            return false;
        }

        //5.token存在则校验令牌 ，如果失败则响应错误信息（状态码401）
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401状态码");
            response.setStatus(401); //设置状态码
            return false;
        }

        //6.校验成功则放行
        log.info("令牌合法，放行");
        return true;

    }
}
