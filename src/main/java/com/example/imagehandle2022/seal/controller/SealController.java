package com.example.imagehandle2022.seal.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class SealController {
    @GetMapping("/seal")
    public String equals() {
        log.info("this is test for server.");
        return "This is a seal project1.";
    }
}
