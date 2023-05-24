package com.example.imagehandle2022.seal.service.impl;

import com.example.imagehandle2022.entity.StudentDO;
import com.example.imagehandle2022.seal.mapper.StudentMapper;
import com.example.imagehandle2022.seal.service.IExcelDBService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExcelDBServiceImpl implements IExcelDBService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * Insert DB according to student list.
     * @param studentDOList
     * @return
     */
    public int insertDB(List<StudentDO> studentDOList){
        return studentMapper.insertStudentList(studentDOList);
    }

    /**
     * Query student info with studentID.
     * @param studentId
     * @return StudentDO
     */
    public StudentDO queryStudent(String studentId){
        return studentMapper.queryStudent(studentId);
    }
}
