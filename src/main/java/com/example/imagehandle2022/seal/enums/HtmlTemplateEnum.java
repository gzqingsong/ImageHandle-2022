package com.example.imagehandle2022.seal.enums;

public enum HtmlTemplateEnum {
    REPORT_CREDIT_BASE_TEST("/pdftemp/base_temp.html","测试模板地址");

    private String localUrl;

    private String  des;

    HtmlTemplateEnum(String localUrl, String des) {
        this.localUrl = localUrl;
        this.des = des;
    }

    public String getLocalUrl(){
        return localUrl;
    }

    public String getDes() {
        return des;
    }
}
