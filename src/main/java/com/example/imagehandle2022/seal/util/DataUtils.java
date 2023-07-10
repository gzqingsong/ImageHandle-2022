package com.example.imagehandle2022.seal.util;

import java.util.Date;

/**
 * @Author zhaotong
 * @Description TODO
 * @Date 2023/7/10
 * @Version 1.0
 */
public class DataUtils {

    /**
     * 生成id
     * @return
     */
    public static Long assignId(){
        Date date = new Date();
        long time = date.getTime();
        String stringBuilder = String.valueOf(time).substring(2);
        return Long.parseLong(stringBuilder);
    }
}
