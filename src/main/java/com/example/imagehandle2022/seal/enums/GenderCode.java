package com.example.imagehandle2022.seal.enums;

/**
 * @Author 陈俊源
 * @Description TODO
 * @Date 2021/4/14
 * @Version 1.0
 */
public enum GenderCode {
    MALE(1,"男"),FEMALE(0,"女");

    private  Integer key;
    private  String value;

    GenderCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public static String getValue(Integer key) {
        switch (key){
            case 0:
                return "女";
            case 1:
                return "男";
        }
        return null;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
