package cn.itsource.filer;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@Slf4j
public class LoginFilter extends HttpFilter{
   /* @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //log.info("来了老弟...");
        filterChain.doFilter(servletRequest,servletResponse);
    }*/
}
