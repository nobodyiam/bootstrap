package com.nobodyiam.web.filter;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * Serve as the authentication check
 */
public class AuthFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String prefix = "";
    private String excludeRegex = "";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (needAuthCheck(request)) {
            //do auth check
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

    private boolean needAuthCheck(HttpServletRequest request) {
        String path = getRequestPath(request);
        return !Pattern.matches(excludeRegex, path);
    }

    private String getRequestPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (this.prefix.isEmpty()) {
            return uri;
        }
        int pos = uri.indexOf(this.prefix);
        return uri.substring(pos + this.prefix.length());
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        @SuppressWarnings("unchecked")
        Enumeration<String> collection = config.getInitParameterNames();
        Collection<String> resultList = Lists.newArrayList();
        while (collection.hasMoreElements()) {
            String name = collection.nextElement();
            if ("targetFilterLifecycle".equals(name)) {
                continue;
            }
            String value = config.getInitParameter(name);
            if ("prefix".equals(name)) {
                this.prefix = value;
                continue;
            }
            if ("exclude".equals(value)) {
                resultList.add("(" + name + ")");
            }
        }

        excludeRegex = "^" + Joiner.on("|").join(resultList) + "$";
    }
}
