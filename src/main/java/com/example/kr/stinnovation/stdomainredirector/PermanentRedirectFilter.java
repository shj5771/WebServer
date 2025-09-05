package com.example.kr.stinnovation.stdomainredirector;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class PermanentRedirectFilter extends OncePerRequestFilter {

    private static final String TARGET = "https://stinnovation.co.kr";

    @Override
    protected void doFilterInternal(
            HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String host = req.getHeader("Host");

        if (host != null && (
                host.equalsIgnoreCase("st-research.co.kr") ||
                        host.equalsIgnoreCase("www.st-research.co.kr"))) {

            String uri = req.getRequestURI();
            String qs  = req.getQueryString();
            String location = TARGET + uri + (qs == null ? "" : "?" + qs);

            res.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY); // 301
            res.setHeader("Location", location);
            return;
        }

        chain.doFilter(req, res);
    }
}
