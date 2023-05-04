package com.example.imagehandle2022.seal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PdfToolService {
    void generatePdf(HttpServletResponse response, HttpServletRequest request);
}
