package com.alkemy.disney.disney.auth.filter;

import com.alkemy.disney.disney.auth.services.JwtUtils;
import com.alkemy.disney.disney.auth.services.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter
{
    private UserDetailsCustomService userDetailsCustomService;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    @Lazy
    @Autowired
    public JwtRequestFilter(UserDetailsCustomService userDetailsCustomService, JwtUtils jwtUtils, AuthenticationManager authenticationManager){
        this.userDetailsCustomService = userDetailsCustomService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtils.extractUsername(jwt);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsCustomService.loadUserByUsername(username);

            if(jwtUtils.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword());

                Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request,response);
    }
}
