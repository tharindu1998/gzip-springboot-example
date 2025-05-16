package com.student.gzip;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GzipResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Specify the endpoints where Gzip should be applied
        String requestURI = request.getServletContext().getContextPath();
        if (requestURI.startsWith("/api/students")) {
            httpResponse.addHeader("Content-Encoding", "gzip");
            GZIPResponseWrapper gzipResponseWrapper = new GZIPResponseWrapper(httpResponse);
            chain.doFilter(request, gzipResponseWrapper);
            gzipResponseWrapper.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}