package com.example.imagehandle2022.seal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SignPDFService {
    void signPdf(HttpServletResponse response, HttpServletRequest request);
}
