package com.pratice.movieapp.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		List<String> apiList= List.of("/login","/register");
		System.out.println("JWT request Filter" +request.getRequestURI());
		if (apiList.contains(RequestURI)) {
			System.out.println("Skipped Authorization Check");
			 filterChain.doFilter(request, response);
			
		}else {
			System.out.println("Authorization Check");
			final String authorizationHeader = request.getHeader("Authorization");
			System.out.println("Authorization Header :" + authorizationHeader);
			if (authorizationHeader == null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}else if( authorizationHeader.startsWith("Bearer ")){
				  String userId = authorizationHeader.substring(7);
				  System.out.println("user Id :"+userId);
				  filterChain.doFilter(request, response);
				
			}
			
			else {
				  response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		

	}
}
