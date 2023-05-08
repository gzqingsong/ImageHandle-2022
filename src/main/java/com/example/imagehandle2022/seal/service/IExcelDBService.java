package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.StudentDO;
import java.util.List;

public interface IExcelDBService {
    int insertDB(List<StudentDO> studentDOList);
    StudentDO queryStudent(String studentId);
}
