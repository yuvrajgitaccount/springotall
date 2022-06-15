package com.example.Springbootblogapi.config.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Springbootblogapi.service.UserServiceImpl;
import com.example.Springbootblogapi.utili.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
	@Autowired
	private UserDetailsService  userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String token = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            
            
            
            try {
				
            	username = jwtUtil.extractUsername(token);
			} catch (ExpiredJwtException e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("jwt token is falied");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("error");
			}
            
        }
        else {
			System.out.println("Invlid token,not start with bear string");
		}

        
        
        //valiedted
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        
        else {
			System.out.println("token is not valied");
		}
        
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }	
}