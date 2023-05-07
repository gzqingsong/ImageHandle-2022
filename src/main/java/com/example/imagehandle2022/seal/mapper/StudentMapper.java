package com.example.imagehandle2022.seal.mapper;

import com.example.imagehandle2022.entity.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {
    int insertStudentList(List<StudentDO> studentDOList);
    StudentDO queryStudent(String studentId);
}
