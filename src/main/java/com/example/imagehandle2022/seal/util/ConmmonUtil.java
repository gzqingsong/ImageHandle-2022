package com.example.imagehandle2022.seal.util;

import com.example.imagehandle2022.entity.SealApplyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.Calendar;
import java.text.ParseException;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

/**
 * @Description: 通用工具类
 * @Author: juny
 * @CreateDate: 2020-12-09 15:57
 */
@Component
@Slf4j
public class ConmmonUtil {
    public String getApplyId(String studentId){
        long currentTime=System.currentTimeMillis();
        String currentTimeSubString=String.valueOf(currentTime).substring(String.valueOf(currentTime).length()-6);

        return studentId+currentTimeSubString;
    }

    /**
     *
     * 计算两个日期相差的月份数
     * @param date1 日期1
     * @param pattern 日期1和日期2的日期格式
     * @return 相差的月份数
     * @throws ParseException
     */
    public static int countMonths(String date1,String pattern) throws ParseException{
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date.toString()));
        int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
        //开始日期若小月结束日期
        if(year==0){
            year=-year;
            return year*12+c1.get(Calendar.MONTH)-c2.get(Calendar.MONTH);
        }

        return year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
    }

    public List<SealApplyInfo> filterSealApplyInfoList(List<SealApplyInfo> list){
        list.stream().filter(sealApplyInfo -> {
            try {
                return countMonths(sealApplyInfo.getApplyDate(),"yyyy-MM-dd HH:mm:ss")<3;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        return list;
    }
}
