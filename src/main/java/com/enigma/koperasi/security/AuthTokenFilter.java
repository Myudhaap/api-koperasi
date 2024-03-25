package com.enigma.koperasi.security;

import com.enigma.koperasi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
  private final JwtUtil jwtUtil;
  private final UserService userService;
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try{
      String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

      String token = null;

      if(headerAuth != null && headerAuth.startsWith("Bearer ")){
        token = headerAuth.substring(7);
      }

      if(token != null && jwtUtil.verifyJwtToken(token)){
        Map<String, String> userInfo = jwtUtil.getUserInfoByToken(token);
        UserDetails user = userService.loadById(userInfo.get("userId"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    filterChain.doFilter(request, response);
  }
}
