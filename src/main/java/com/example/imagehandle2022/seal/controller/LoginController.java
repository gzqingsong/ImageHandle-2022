package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.seal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.imagehandle2022.entity.UserDTO;
import com.example.imagehandle2022.entity.ResultEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResultEntity login(@RequestBody UserDTO userDTO){
        ResultEntity result = userService.login(userDTO);
        return result;
    }

    @RequestMapping("/getUserById")
    public ResultEntity getUserById(Long token){
        ResultEntity result = userService.getUserById(token);
        return result;
    }

}
