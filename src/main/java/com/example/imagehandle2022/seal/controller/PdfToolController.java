package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.seal.service.PdfToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.imagehandle2022.seal.service.SignPDFService;

/**
 * @Description:
 * @Author: Tong
 * @CreateDate: 2023-05-04
 */
@RequestMapping
@RestController
public class PdfToolController {

    @Autowired
    PdfToolService pdfToolService;
    @Autowired
    SignPDFService signPDFService;

    @RequestMapping("/test")
    public void test(HttpServletResponse response, HttpServletRequest request){
        pdfToolService.generatePdf(response,request);
    }

    @RequestMapping("/sign")
    public void signPDF(HttpServletResponse response, HttpServletRequest request){
        signPDFService.signPdf(response,request);
    }
}
