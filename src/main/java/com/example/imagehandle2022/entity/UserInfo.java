package com.example.imagehandle2022.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author zhaotong
 * @Description 用户对象实体
 * @Date 2023/7/10
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
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
    private Date birthDate;
    /**
     * 用户性别
     */
    private Integer gender;
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
}
