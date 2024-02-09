package com.quiz.quizapp.model;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * The type Authority.
 */
@ToString
public class Authority implements GrantedAuthority {
    private String authority;

    /**
     * Instantiates a new Authority.
     *
     * @param authority the authority
     */
    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
