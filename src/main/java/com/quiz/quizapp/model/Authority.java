package com.quiz.quizapp.model;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

@ToString
public class Authority implements GrantedAuthority {
    private String authority;
    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
