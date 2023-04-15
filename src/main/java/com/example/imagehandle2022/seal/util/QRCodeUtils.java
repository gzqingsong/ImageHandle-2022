package com.example.imagehandle2022.seal.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * 二维码生成工具
 */
public class QRCodeUtils {

    /**
     * 生成
     *
     * @param content 二维码生成内容
     * @param width   宽
     * @param height  高
     */
    public static BitMatrix encode(String content, int width, int height) {
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);// 空白

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);
            return bitMatrix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void encodeToPath(String content, int width, int height, String format, String path) {
        BitMatrix bitMatrix = encode(content, width, height);
        try {
            MatrixToImageWriter.writeToPath(bitMatrix, format, Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
