package com.example.imagehandle2022.seal.service;

import com.google.common.collect.Maps;
import com.example.imagehandle2022.seal.enums.HtmlTemplateEnum;
import com.example.imagehandle2022.seal.util.PdfUtil;
import com.example.imagehandle2022.seal.util.QRCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import com.example.imagehandle2022.seal.util.CharConvertHtmlUtil;

/**
 * @Description:
 * @Author: juny
 * @CreateDate: 2021-03-01 11:22
 */
@Service
@Slf4j
public class PdfToolService {

    @Autowired
    private SpringTemplateEngine templateEngine;

    /**
     * 渲染html文件
     * 将html生成pdf，并设置目录，添加页眉，水印，背景
     * @param response
     * @param request
     */
    public void generatePdf(HttpServletResponse response, HttpServletRequest request){
        Map<String, Object> param = htmlParam();
        log.info("获取到模板内容，开始渲染html模板");
        WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale());
        ctx.setVariables(param);
        String htmlContent = templateEngine.process(HtmlTemplateEnum.REPORT_CREDIT_BASE_TEST.getLocalUrl(), ctx);
        log.info("模板内容渲染完成，开始转换为临时pdf文件");
        String basepath = this.getClass().getClassLoader().getResource("./").getPath();
        String tempPath = basepath + "temp";
        File filedir = new File(tempPath);
        if (!filedir.exists()) {// 文件夹不存在则创建
            filedir.mkdirs();
        }
        String sourceFile = PdfUtil.createPDF(basepath, tempPath, htmlContent);
        log.info("临时pdf文件生成成功");
        // 新的pdf地址
        String pdfNumber = "PDF" + System.currentTimeMillis();
        // 二维码图片地址
        String orImgPath = tempPath + File.separator + pdfNumber + ".png";
        // 新pdf地址
        String targetFile = tempPath + File.separator + pdfNumber + ".pdf";
        String fontPath = basepath + "static" + File.separator + "simsun.ttc";
        String fontPathTitle = basepath + "static" + File.separator + "DFST-M8.ttc";
        // 二维码图片内容
        String content = "蓝威龙，2144101207936，申请时间：20230118 14：24：34";
        try {
            QRCodeUtils.encodeToPath(content, 65, 65, "png", new File(orImgPath).getPath());
            // 生成新的pdf
            PdfUtil.createPdf(sourceFile, targetFile, fontPath, pdfNumber, this.catalogs(), "", basepath, "");
            log.info("pdf文件生成成功，文件地址为:"+targetFile);
        }catch (Exception e){
            log.info("pdf生成出错，错误信息："+e.getMessage());
        }finally {
            // 删除创建的临时文件
            File file = new File(sourceFile);
            if (file.exists()) {
                file.delete();
            }
            /*File file1 = new File(targetFile);
            if (file1.exists()) {
                file1.delete();
            }*/
            File file2 = new File(orImgPath);
            if (file2.exists()) {
                file2.delete();
            }
        }

    }

    /**
     * 获取渲染页面参数
     * @return
     */
    private Map<String, Object> htmlParam(){
        Map<String, Object> param = Maps.newHashMap();
        param.put("companyName", "广州市广播电视大学教务处");
        param.put("certificateType", "在读证明");
        param.put("generateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return param;
    }

    /**
     * 获取目录
     * @return
     */
    private LinkedHashMap<String, Integer> catalogs() {
        // 目录名称
        LinkedHashMap<String, Integer> catalogs = Maps.newLinkedHashMap();
        //catalogs.put("1.综合概述", 1);
//        catalogs.put("2.基本信息", 1);
//        catalogs.put("2.1.基本信息1", 2);
        return catalogs;
    }
}
