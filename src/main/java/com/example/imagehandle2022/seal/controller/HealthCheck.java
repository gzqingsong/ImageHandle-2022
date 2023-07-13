package com.example.imagehandle2022.seal.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HealthCheck {
    @GetMapping("/health")
    public String health(){
        return "start: UP";
    }
}
