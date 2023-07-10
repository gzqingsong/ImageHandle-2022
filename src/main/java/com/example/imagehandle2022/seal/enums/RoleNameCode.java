package com.example.imagehandle2022.seal.enums;

/**
 * @Author 陈俊源
 * @Description TODO
 * @Date 2021/4/14
 * @Version 1.0
 */
public enum  RoleNameCode {
    Student(0,"学员"),Teacher(1,"教师"),Admin(2,"管理员"),Other(3,"其他");
    private Integer key;
    private String value;

    RoleNameCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public static String getValue(Integer key) {
        switch (key){
            case 0:return "管理员";
            case 1:return "教师";
            case 2:return "学员";
            case 3:return "其他";
        }
        return null;
    }


    public void setValue(String value) {
        this.value = value;
    }
}
