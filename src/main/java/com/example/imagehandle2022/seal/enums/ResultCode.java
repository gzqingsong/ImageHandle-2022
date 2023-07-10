package com.example.imagehandle2022.seal.enums;

public enum ResultCode {
    //成功
    SUCCESS("0","成功"),
    //失败
    FAIL("-1","失败");

    private String Code;
    private String message;

    ResultCode(String code, String message) {
        Code = code;
        this.message = message;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
