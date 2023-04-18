package com.example.imagehandle2022.seal.util;


import com.google.common.base.Strings;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.IOException;

/**
 * @Description: pdf页眉 页码设置
 * @Author: juny
 * @CreateDate: 2020-12-09 17:30
 */
public class PdfCanvasUtil {

    /**
     * 设置页眉
     *
     * @param canvas
     * @param pageSize
     * @param font
     * @throws IOException
     */
    public static void setHeader(PdfCanvas canvas, Rectangle pageSize, PdfFont font) throws IOException {
        canvas.beginText().setFontAndSize(font, 10)
                .moveText(10, pageSize.getHeight() - 15)
                .showText("广州开放大学")
                .endText();
        canvas.beginText().setFontAndSize(font, 10)
                .moveText(pageSize.getWidth() - 120, pageSize.getHeight() - 15)
                .showText("联系电话：020-83481387")
                .endText();
        //设置横线
        canvas.setStrokeColor(Color.DARK_GRAY)
                .setLineWidth(.2f)
                .moveTo(0, pageSize.getHeight() - 20)
                .lineTo(pageSize.getWidth(), pageSize.getHeight() - 20).stroke();
    }

    /**
     * 设置页码
     *
     * @param canvas
     * @param pageSize
     * @throws IOException
     */
    public static void setPageNumber(PdfCanvas canvas, Rectangle pageSize, Integer pageNumber) throws IOException {
        canvas.beginText().setFontAndSize(
                PdfFontFactory.createFont(FontConstants.HELVETICA), 10)
                .moveText(pageSize.getWidth() / 2 - 7, 10)
                .showText(String.valueOf(pageNumber))
                /*.showText(" of ")
                .showText(String.valueOf(13))*/
                .endText();
    }

    /**
     * 设置水印
     *
     * @param canvas
     * @param pageSize
     * @param font
     * @param pageNumber
     * @param document
     * @param waterName
     */
    public static void setWatermark(PdfCanvas canvas, Rectangle pageSize, PdfFont font,
                                    Integer pageNumber, Document document, String waterName) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 15; i++) {
            buffer.append(waterName + "      ");
        }
        Paragraph p1 = new Paragraph(buffer.toString().trim()).setFontSize(15).setFont(font);
        canvas.saveState();
        PdfExtGState gs1 = new PdfExtGState().setFillOpacity(0.2f);
        canvas.setExtGState(gs1);
        for (int i = 0; i < 13; i++) {
            if (i % 2 > 0) {
                document.showTextAligned(p1,
                        pageSize.getWidth() / 2 + 200, pageSize.getHeight() * 2 / 13 * i,
                        pageNumber,
                        TextAlignment.CENTER, VerticalAlignment.MIDDLE, 120);
            } else {
                document.showTextAligned(p1,
                        pageSize.getWidth() / 2 + 200 - 100, pageSize.getHeight() * 2 / 13 * i - 100 * pageSize.getWidth() / pageSize.getHeight(),
                        pageNumber,
                        TextAlignment.CENTER, VerticalAlignment.MIDDLE, 120);
            }
        }
        canvas.restoreState();
    }

    /**
     * 首页插入二维码图片及报告编号
     * @param page
     * @param text 二维码下方显示文字
     */
    public static void setORImage(PdfPage page,String text,String or) throws IOException {
        Rectangle pageSize = page.getPageSize();
        PdfCanvas canvas = new PdfCanvas(page);
        ImageData imageData = ImageDataFactory.create(or);
        canvas.addImage(imageData,pageSize.getWidth()-100-25,pageSize.getHeight()-100-25,false);
//        if (!Strings.isNullOrEmpty(text)) {
//            canvas.beginText().setFontAndSize(PdfFontFactory.createFont(FontConstants.HELVETICA), 6)
//                    .moveText(pageSize.getWidth()-100-25, pageSize.getHeight()-100-25-6)
//                    .showText(text)
//                    .endText();
//        }
    }

    /**
     * 设置首页logo
     * @param page
     * @param basePath
     * @throws IOException
     */
    public static void setLogo(PdfPage page,String basePath) throws IOException {

        Rectangle pageSize = page.getPageSize();
        PdfCanvas canvas = new PdfCanvas(page);
        ImageData imageData = ImageDataFactory.create(basePath+ "static/img/pdf_logo.jpg");
        canvas.addImage(imageData,50,pageSize.getHeight()-100-50,false);
    }
}
