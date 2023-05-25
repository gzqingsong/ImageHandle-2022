package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.SealApplyInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ISignPDFService {
    void signPdf(HttpServletRequest request, SealApplyInfo sealApplyInfo);
}
