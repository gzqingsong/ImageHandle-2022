package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.seal.impl.PdfToolServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.imagehandle2022.seal.impl.SignPDFServiceImpl;

/**
 * @Description:
 * @Author: Tong
 * @CreateDate: 2023-05-04
 */
@RequestMapping
@RestController
public class PdfToolController {

    @Autowired
    PdfToolServiceImpl pdfToolService;
    @Autowired
    SignPDFServiceImpl signPDFService;

    /**
     * This method generate PDF file with html.
     * @param response
     * @param request
     */
    @RequestMapping("/generate-pdf")
    public void generatePdf(HttpServletResponse response, HttpServletRequest request){
        pdfToolService.generatePdf(response,request);
    }

    @RequestMapping("/sign-pdf")
    public void signPDF(HttpServletResponse response, HttpServletRequest request){
        signPDFService.signPdf(response,request);
    }
}
