package com.rodrigo.courses.core;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    public SimpleCorsFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String origin = request.getHeader("origin");
        addHeaderIfNotExist(response, "Access-Control-Allow-Origin", StringUtils.isEmpty(origin) ? "*" : origin);

        addHeaderIfNotExist(response, "Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        addHeaderIfNotExist(response, "Access-Control-Max-Age", "3600");
        addHeaderIfNotExist(response, "Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authorization, x-csrf-token, X-XSRF-TOKEN");
        addHeaderIfNotExist(response, "Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.getWriter().print("OK");
            response.getWriter().flush();
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    private void addHeaderIfNotExist(HttpServletResponse response, String key, String value) {
        if (response.getHeader(key) == null) {
            response.setHeader(key, value);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}
