package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.entity.StudentDO;
import com.example.imagehandle2022.seal.service.ExcelDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping
@RestController
public class ExcelController {
    @Autowired
    ExcelDBService excelDBService;

    @RequestMapping("/insert-db")
    public int insertDB(){
        List<StudentDO> studentDOList=new ArrayList<>();
        StudentDO studentDO = new StudentDO();
        studentDO.setStudentId("1844101413363");
        studentDO.setStudentName("刘虎");
        studentDO.setCardId("413026200012287512");
        studentDO.setCardType("身份证");
        studentDO.setEntryDate("2018春季");
        studentDO.setGenderCode("1");
        studentDO.setGender("男");
        studentDO.setBirthday("1988.07.12");
        studentDO.setNationCode("01");
        studentDO.setExamineeNumber("01");
        studentDO.setPhoneNumber("15875721573");
        studentDO.setStudentStatusCode("01");
        studentDO.setStudentStatus("在籍");
        studentDO.setStudentTypeCode("01");
        studentDO.setStudentType("開放");
        studentDO.setAcademyCode("44191");
        studentDO.setAcademy("直属学院");
        studentDO.setCenterCode("");
        studentDO.setCenter("");
        studentDO.setClassCode("184419101014060");
        studentDO.setClassName("18春工商企专S班(高新)");
        studentDO.setMajorLevelCode("4");
        studentDO.setMajorLevel("专科");
        studentDO.setMajorCode("11020102");
        studentDO.setMajor("工商企业管理");

        studentDOList.add(studentDO);

        return excelDBService.insertDB(studentDOList);
    }
}
