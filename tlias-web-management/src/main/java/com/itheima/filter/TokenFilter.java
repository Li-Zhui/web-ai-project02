package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //强转为请求对象和响应对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = request.getRequestURI(); //URI不包含协议、IP、端口，只包含后面的请求路径

        //2.判断是否为登录请求（是否包含login），是则放行
        if (requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(request, response);
            return;
        }

        //3.不是则获取请求头中的 token
        String token = request.getHeader("token");

        //4.判断token是否存在，不存在则返回错误信息（状态码401）
        if (token == null || token.isEmpty()){
            log.info("令牌为空，响应401状态码");
            response.setStatus(401); //设置状态码
            return;
        }

        //5.token存在则校验令牌 ，如果失败则响应错误信息（状态码401）
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = (Integer) claims.get("id");
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录员工id为：{} ， 将其存入ThreadLocal", empId);
        } catch (Exception e) {
            log.info("令牌非法，响应401状态码");
            response.setStatus(401); //设置状态码
            return;
        }

        //6.校验成功则放行
        log.info("令牌合法，放行");
        filterChain.doFilter(request, response);

        //7.删除ThreadLocal中的数据
        CurrentHolder.remove();

    }


}
