package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.seal.service.PdfToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: juny
 * @CreateDate: 2021-03-02 9:39
 */
@RequestMapping
@RestController
public class PdfToolController {

    @Autowired
    PdfToolService pdfToolService;

    @RequestMapping("/test")
    public void test(HttpServletResponse response, HttpServletRequest request){
        pdfToolService.generatePdf(response,request);
    }
}
