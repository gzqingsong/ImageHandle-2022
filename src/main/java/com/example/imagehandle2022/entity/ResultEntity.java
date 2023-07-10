package com.example.imagehandle2022.entity;

import com.example.imagehandle2022.seal.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 陈俊源
 * @Description TODO
 * @Date 2021/4/1
 * @Version 1.0
 */
@Data
public class ResultEntity<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public static <T> ResultEntity<T> fail(String message){
        ResultEntity<T> result = new ResultEntity<T>();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> ResultEntity<T> fail(T data, String message){
        ResultEntity<T> result = new ResultEntity<T>();
        result.setCode(ResultCode.FAIL.getCode());
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static <T> ResultEntity<T> success(){
        ResultEntity<T> result = new ResultEntity<T>();
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }

    public static <T> ResultEntity<T> success(T data){
        ResultEntity<T> result = new ResultEntity<T>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static Boolean isSuccess(ResultEntity result){
        if (result == null){
            return false;
        }
        if (result.getCode()!=null && result.getCode().equals(ResultCode.SUCCESS.getCode())){
            return true;
        }else {
            return false;
        }
    }




}
