package com.quiz.quizapp.model;

import org.springframework.http.HttpStatus;


public class NoContent {
    private HttpStatus status;
    private String msg;

    public NoContent(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
