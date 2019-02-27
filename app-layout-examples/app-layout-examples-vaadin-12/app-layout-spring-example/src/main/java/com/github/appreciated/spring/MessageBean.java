package com.github.appreciated.spring;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class MessageBean {

    public String getMessage() {
        return "Button was clicked at " + LocalTime.now();
    }
}
