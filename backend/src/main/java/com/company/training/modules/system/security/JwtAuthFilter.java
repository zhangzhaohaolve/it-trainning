package com.company.training.modules.system.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.startsWith("/api/system/login")) { filterChain.doFilter(request, response); return; }
        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ") && tokenProvider.validate(auth.substring(7))) {
            filterChain.doFilter(request, response);
            return;
        }
        response.setStatus(401);
        response.getWriter().write("Unauthorized");
    }
}
