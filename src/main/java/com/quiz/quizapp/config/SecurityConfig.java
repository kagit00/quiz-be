package com.quiz.quizapp.config;

import com.quiz.quizapp.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final AuthTokenFilter authTokenFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, JwtAuthenticationEntryPoint unauthorizedHandler, AuthTokenFilter authTokenFilter) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authTokenFilter = authTokenFilter;
    }

    /**
     * The function returns the authentication manager.
     * 
     * @return The method is returning an instance of the AuthenticationManager.
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * The function returns a BCryptPasswordEncoder object, which is used for encoding passwords in
     * Java.
     * 
     * @return The method is returning an instance of the BCryptPasswordEncoder class, which is a
     * PasswordEncoder implementation provided by the Spring Security framework.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().authorizeRequests()
                .antMatchers("/auth/token", "/users").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
