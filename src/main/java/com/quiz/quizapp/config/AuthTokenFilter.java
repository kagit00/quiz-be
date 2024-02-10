package com.quiz.quizapp.config;

import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.service.UserDetailsServiceImpl;
import com.quiz.quizapp.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

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
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String requestTokenHeader = request.getHeader("Authorization");
            String username = null;
            String jwtToken = null;

            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
                jwtToken = requestTokenHeader.substring(7);
                username = this.jwtUtils.getUsernameFromToken(jwtToken);
            } else {
                logger.info("Token is invalid");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
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
        } catch (IllegalArgumentException e) {
            logger.info(e.getMessage());
            throw new InternalServerErrorException("Illegal Argument while fetching the username.");
        } catch (ExpiredJwtException e) {
            logger.info(e.getMessage());
            throw new InternalServerErrorException("Given jwt token is expired.");
        } catch (MalformedJwtException e) {
            logger.info(e.getMessage());
            throw new InternalServerErrorException("Invalid Token");
        } catch (ServletException | IOException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
