package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.StudentDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelDBService {
    void insertDB(StudentDO studentDO);
}
