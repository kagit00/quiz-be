package com.quiz.quizapp.filter;

import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.service.UserDetailsServiceImpl;
import com.quiz.quizapp.security.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Objects;

/**
 * The type Auth token filter.
 */
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Instantiates a new Auth token filter.
     *
     * @param jwtUtils           the jwt utils
     * @param userDetailsService the user details service
     */
    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        try {
            String requestTokenHeader = request.getHeader("Authorization");
            String username = null;
            String jwtToken = null;

            if (!StringUtils.isEmpty(requestTokenHeader) && requestTokenHeader.startsWith("Bearer")) {
                jwtToken = requestTokenHeader.substring(7);
                username = this.jwtUtils.getUsernameFromToken(jwtToken);
            } else {
                logger.info("Token is invalid");
            }

            if (!Objects.isNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (Boolean.TRUE.equals(this.jwtUtils.validateToken(jwtToken, userDetails))) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } else {
                logger.info("Token is invalid");
            }
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException | ServletException | IOException e) {
            logger.info(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
