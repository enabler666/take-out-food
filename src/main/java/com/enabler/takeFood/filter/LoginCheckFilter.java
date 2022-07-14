package com.enabler.takeFood.filter;

import com.alibaba.fastjson.JSON;
import com.enabler.takeFood.common.R;
import com.enabler.takeFood.entity.Employee;
import com.enabler.takeFood.util.BaseContext;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Enabler
 */

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };
        boolean check = check(requestURI, urls);
        if(check){
            filterChain.doFilter(request, response);
            return ;
        }

        if(request.getSession().getAttribute("employee") != null){
            Long employee = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(employee);
            filterChain.doFilter(request, response);
            return;
        }

        if(request.getSession().getAttribute("user") != null){
            Long user = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(user);
            filterChain.doFilter(request, response);
            return;
        }

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 检查当此请求是非合法
     * @param requestURI,urls
     * @return
     */
    public boolean check(String requestURI, String[] urls){
        for (String uri:urls) {
            boolean match = PATH_MATCHER.match(uri, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
