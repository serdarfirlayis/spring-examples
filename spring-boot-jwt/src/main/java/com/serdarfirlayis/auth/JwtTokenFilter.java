package com.serdarfirlayis.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException, ServletException {

        /**
         * "Bearer 123hab2355"
         */
        final String authHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String token = null;

        if (authHeader != null && authHeader.contains("Bearer")) {
            token = authHeader.substring(7);
            try {
                // expiration can be checked here too
                username = tokenManager.getUserFromToken(token);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // SecurityContextHolder.getContext().getAuthentication() == null means that the user is not logged in
        if (username != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // If the user is not logged in, the user is logged in
            if (tokenManager.tokenValidate(token)){
                UsernamePasswordAuthenticationToken upassToken
                        = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(upassToken);
            }
        }

        // If the user is logged in, the request is forwarded to the next filter
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
