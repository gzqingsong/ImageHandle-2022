package com.example.imagehandle2022.seal.impl;

import com.example.imagehandle2022.entity.StudentDO;
import com.example.imagehandle2022.seal.mapper.StudentMapper;
import com.example.imagehandle2022.seal.service.ExcelDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class ExcelDBServiceImpl implements ExcelDBService {
    @Autowired
    private StudentMapper studentMapper;
    public int insertDB(List<StudentDO> studentDOList){
        return studentMapper.insertStudentList(studentDOList);
    }
}
