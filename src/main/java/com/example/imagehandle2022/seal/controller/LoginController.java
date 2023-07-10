package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.seal.service.ISealService;
import com.example.imagehandle2022.seal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.imagehandle2022.entity.UserDTO;
import com.example.imagehandle2022.entity.ResultEntity;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResultEntity login(UserDTO userDTO){
        ResultEntity result = userService.login(userDTO);
        return result;
    }
}
