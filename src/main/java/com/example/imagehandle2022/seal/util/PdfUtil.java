package com.example.imagehandle2022.seal.util;

import com.google.common.collect.Maps;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @Description: pdf生成工具类
 * @Author: juny
 * @CreateDate: 2020-12-09 15:57
 */
@Slf4j
public class PdfUtil {

    /**
     * html转为pdf
     * @param basepath
     * @param tempPath
     * @param htmlContent
     * @return
     */
    public static String createPDF(String basepath,String tempPath,String htmlContent){
        try {
            String ttcPath = basepath + "static"+File.separator; // 字体路径
            ITextRenderer renderer = new ITextRenderer();
            //renderer.setDocumentFromString(htmlContent);
            ITextFontResolver fontResolver = renderer.getFontResolver(); //中文支持
            fontResolver.addFont(ttcPath+"msyh.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//微软雅黑
            //fontResolver.addFont(ttcPath+"msyhbd.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//微软雅黑-字体加粗支持
            //fontResolver.addFont(ttcPath+"simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//宋体
            // 解决图片的相对路径问题
            String imppath = (new File(basepath)).toURI().toURL().toString();
            renderer.getSharedContext().setBaseURL(imppath);
            renderer.layout();
            long nowTime = System.currentTimeMillis();// 获得当前系统时间序号
            String pdfTempFile = tempPath + File.separator + nowTime + ".pdf";
            OutputStream pdfOS = new FileOutputStream(pdfTempFile);
            renderer.createPDF(pdfOS);
            renderer.finishPDF();
            pdfOS.close();
            return pdfTempFile;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成一个带有目录(可跳转)的pdf文件(支持中文).
     *
     * @param sourceFile 源pdf文件
     * @param targetFile 处理后的pdf文件
     * @param catalogs   目录数据map<标题,目录等级> 一级和二级
     * @param fontPath   使用字体
     * @param reportNumber   报告编码
     * @return targetFile
     * @throws IOException
     */
    public static String createPdfWithCatalog(String sourceFile, String targetFile,
                                              String fontPath, String reportNumber,
                                              LinkedHashMap<String, Integer> catalogs,
                                              String or,String basePath,String waterName) throws IOException {
        File file = new File(targetFile);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        // 读取原始pdf
        log.info("开始获取原始pdf文件");
        PdfReader pdfReader = new PdfReader(sourceFile);
        PdfDocument firstSourcePdf = new PdfDocument(pdfReader);
        int numberOfPages = firstSourcePdf.getNumberOfPages();
        log.info("原始pdf文件成功，文件总页数：{}",numberOfPages);
        //创建新的pdf文件
        log.info("开始创建新的pdf文件");
        PdfDocument newPdfDoc = new PdfDocument(new PdfWriter(targetFile));
        Document document = new Document(newPdfDoc);
        document.setMargins(40,20,40,20);
        //PdfFont font = PdfFontFactory.createFont("F:\\syx\\syx_credit_api_platform\\credit-admin\\admin-starter\\src\\main\\resources\\static\\simsun.ttc"+",0", PdfEncodings.IDENTITY_H, false);
        PdfFont font = PdfFontFactory.createTtcFont(fontPath,0, PdfEncodings.IDENTITY_H, false,true);
        String catalogTitle = "目录";
        document.add(new Paragraph(new Text(catalogTitle))
                .setTextAlignment(TextAlignment.CENTER).setFont(font));
        // 获取目录页数
        int catalogPage = catalogs.size() % 30 > 0 ? catalogs.size() / 30 + 1 : catalogs.size() / 30;
        // 如果当前页数catalogPage大于1 则添加空白目录页catalogPage-1页
        log.info("pdf生成目录信息，获取当前目录总页数：{}",catalogPage);
        if (catalogPage>1) {
            for (int i = 1; i < catalogPage; i++) {
                newPdfDoc.addNewPage();
            }
        }
        // 计算出声明页+目录页总页码
        int startPageNumber = catalogPage+1;
        // 遍历原始pdf文件，从第三页开始，首页和说明页不遍历，后面单独加入
        log.info("遍历原始pdf文件，从正文开始，设置目录页，页眉，页码等信息，并保存到新的pdf文件中");
        for (int i = startPageNumber + 1; i <= numberOfPages; i++) {
            int pageNumber = i;
            //将原文件复制到新的bpf中
            PdfPage page = firstSourcePdf.getPage(i).copyTo(newPdfDoc);
            String textFromPage = PdfTextExtractor.getTextFromPage(page);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas canvas = new PdfCanvas(page);
            log.info("开始设置页眉");
            PdfCanvasUtil.setHeader(canvas,pageSize,font); // 设置页眉
            log.info("开始设置页码,当前页页码为：{}",pageNumber);
            PdfCanvasUtil.setPageNumber(canvas,pageSize,pageNumber); //设置页码
            newPdfDoc.addPage(page);
            for (Map.Entry<String, Integer> entry : catalogs.entrySet()) {
                if (textFromPage.contains(entry.getKey())) {
                    // 设置跳转位置
                    String destinationKey = entry.getKey() + i;
                    PdfArray destinationArray = new PdfArray();
                    destinationArray.add(page.getPdfObject());
                    destinationArray.add(PdfName.XYZ);
                    destinationArray.add(new PdfNumber(page.getMediaBox().getHeight()));
                    try {
                        // 添加跳转位置
                        newPdfDoc.addNamedDestination(destinationKey, destinationArray);
                    } catch (Exception e) {
                        log.info("设置跳转位置失败，错误信息"+e.getMessage());
                    }
                    Paragraph p = new Paragraph();
                    p.setFont(font);
                    //p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
                    if (entry.getValue() == 2) {
                        p.addTabStops(new TabStop(540, TabAlignment.RIGHT, new DottedLine()));
                        p.setPaddingLeft(8);
                        p.add("     " + entry.getKey());
                    } else {
                        p.addTabStops(new TabStop(549, TabAlignment.RIGHT, new DottedLine()));
                        p.add(entry.getKey());
                    }
                    p.add(new Tab());
                    p.add(String.valueOf(pageNumber));
                    // 设置跳转位置(跳转到当前页)
                    p.setProperty(Property.ACTION, PdfAction.createGoTo(destinationKey));
                    document.add(p);
                }
            }
        }
        // 首页的说明页添加到新文档
        log.info("将原始页的首页和说明页添加到新的pdf页面作为首页和说明页");
        newPdfDoc.addPage(1, firstSourcePdf.getPage(1).copyTo(newPdfDoc));
        newPdfDoc.addPage(2, firstSourcePdf.getPage(2).copyTo(newPdfDoc));
        log.info("遍历新的pdf文件，设置说明页和目录页 页眉、页码，添加水印");
        for (int i = 0; i < newPdfDoc.getNumberOfPages()-1; i++) {
            PdfPage page = newPdfDoc.getPage(i + 2);
            Rectangle pageSize = page.getPageSize();
            PdfCanvas canvas = new PdfCanvas(page);
            if (i==0){
                log.info("首页添加二维码图片",reportNumber);
                PdfCanvasUtil.setORImage(newPdfDoc.getPage(i + 1),reportNumber,or);
                log.info("添加logo");
                PdfCanvasUtil.setLogo(newPdfDoc.getPage(i + 1),basePath);
            }
            // 设置说明页和目录页 页眉、页码
            log.info("开始设置页眉、页码,当前页:{}",i+2);
            if (i<startPageNumber) {
                PdfCanvasUtil.setHeader(canvas, pageSize, font);
                PdfCanvasUtil.setPageNumber(canvas, pageSize, i + 1);
            }
            // 添加背景图片
            log.info("开始添加水印，当前页:{}",i+2);
            PdfCanvasUtil.setWatermark(canvas,pageSize,font,i+2,document,waterName);
        }
        firstSourcePdf.close();
        document.close();
        newPdfDoc.close();
        return targetFile;
    }
}
