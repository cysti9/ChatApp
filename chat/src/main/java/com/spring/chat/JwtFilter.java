package com.spring.chat;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
            throws ServletException, IOException {
        String authreader = request.getHeader("Authorization");
        if (authreader != null && authreader.startsWith("Bearer ")) {
            String token = authreader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                String username = jwtUtil.extractUsername(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterchain.doFilter(request, response);
    }
}