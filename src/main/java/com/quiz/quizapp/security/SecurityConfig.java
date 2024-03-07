package com.quiz.quizapp.security;

import com.quiz.quizapp.filter.AuthTokenFilter;
import com.quiz.quizapp.filter.JwtAuthenticationEntryPoint;
import com.quiz.quizapp.exception.InternalServerErrorException;
import com.quiz.quizapp.util.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final AuthTokenFilter authTokenFilter;

    /**
     * Instantiates a new Security config.
     *
     * @param unauthorizedHandler the unauthorized handler
     * @param authTokenFilter     the auth token filter
     */
    public SecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler, AuthTokenFilter authTokenFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.authTokenFilter = authTokenFilter;
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication manager authentication manager.
     *
     * @param authenticationConfiguration the authentication configuration
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * Filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        try {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(cors -> cors
                            .configurationSource(request -> {
                                CorsConfiguration corsConfiguration = new CorsConfiguration();
                                corsConfiguration.applyPermitDefaultValues();
                                corsConfiguration.addAllowedMethod(HttpMethod.PUT.name());
                                corsConfiguration.addAllowedMethod(HttpMethod.DELETE.name());
                                return corsConfiguration;
                            }))
                    .authorizeHttpRequests(auth ->
                            auth
                                    .requestMatchers("/users", "/auth/token").permitAll()
                                    .requestMatchers("/users/**", "/categories/**", "/questions/**", "/quizzes/**", "/bot/**")
                                    .hasAnyAuthority(Constant.USER_ROLE, Constant.ADMIN_ROLE)
                                    .anyRequest().authenticated()
                    ).exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
