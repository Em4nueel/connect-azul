package com.connect.jpa.filter;

import jakarta.servlet.FilterChain; 
import jakarta.servlet.ServletException; 
import jakarta.servlet.http.HttpServletRequest; 
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; 
import org.springframework.security.core.context.SecurityContextHolder; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource; 
import org.springframework.stereotype.Component; 
import org.springframework.web.filter.OncePerRequestFilter;

import com.connect.jpa.service.JwtService;
import com.connect.jpa.service.UserInfoService;

import com.connect.jpa.service.LogoutService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter { 
    private final JwtService jwtService; 
    private final UserInfoService userDetailsService; 
    private final LogoutService logoutService;

    public JwtAuthFilter(JwtService jwtService, UserInfoService userDetailsService, LogoutService logoutService) { 
        this.jwtService = jwtService; 
        this.userDetailsService = userDetailsService; 
        this.logoutService = logoutService; 
    }

    
    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { 
        String authHeader = request.getHeader("Authorization"); 
        String token = null; 
        String username = null; 

        if (authHeader != null && authHeader.startsWith("Bearer ")) { 
            token = authHeader.substring(7); 
            try {
                username = jwtService.extractUsername(token); 
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido");
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { 
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
            
            if (logoutService.isTokenRevoked(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
                response.getWriter().write("Token revogado");
                return; 
            }
            
            if (jwtService.validateToken(token, userDetails)) { 
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                ); 
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
                SecurityContextHolder.getContext().setAuthentication(authToken); 
            }
        }

        filterChain.doFilter(request, response); 
    } 
}
