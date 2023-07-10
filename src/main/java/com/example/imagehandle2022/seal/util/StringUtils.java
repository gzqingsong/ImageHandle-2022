package com.example.imagehandle2022.seal.util;

/**
 * @Author zhao tong
 * @Description TODO
 * @Date 2023/7/10
 * @Version 1.0
 */
public class StringUtils {

    public static boolean isNotNUllAndNotEntry(String value){
        return value != null && value.trim().length() > 0;
    }
}
