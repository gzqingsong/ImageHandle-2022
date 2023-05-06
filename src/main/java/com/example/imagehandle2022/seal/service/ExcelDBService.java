package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.StudentDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExcelDBService {
    int insertDB(List<StudentDO> studentDOList);
}
