package com.example.imagehandle2022.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author Tong zhao
 * @Description TODO
 * @Date 2023/7/10
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户姓名
     */
    private String fullName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户出生日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthDate;
    /**
     * 用户性别
     */
    private Integer gender;
    /**
     * 用户性别描述详解
     */
    private String genderDec;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户联系方式
     */
    private String contactWay;
    /**
     * 用户角色
     */
    private Integer roleName;
    /**
     * 用户角色描述详解
     */
    private String roleNameDec;

    /**
     * 所属班级id
     */
    private Long classId;
    /**
     *所处年级
     */
    private String grade;
    /**
     * 所属学校
     */
    private String school;
    /**
     * 已学课时
     */
    private Double usedClassHours;
    /**
     * 剩余课时
     */
    private Double remainClassHours;
    /**
     * 教师职称
     */
    private String teachersTitle;
    /**
     * 工资
     */
    private Double salary;
    /**
     * 请假天数
     */
    private Double leaveDays;
    /**
     * 加班天数
     */
    private Double overDays;
    /**
     * 相关描述
     */
    private String description;
}
