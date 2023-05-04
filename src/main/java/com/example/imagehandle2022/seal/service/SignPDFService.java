package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.constant.Constant;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.imagehandle2022.entity.SignatureInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

/**
 * @Description: Sign PDF document with seal.
 * @Author: Tong
 * @CreateDate: 2023-05-04
 */
@Service
@Slf4j
public class SignPDFService {

    @Value("${srcPDFPath}")
    private String srcPDFPath;
    @Value("${pkpath}")
    private String pkpath;

    @Value("${signedPDFPath}")
    private String targetPDFPath;

    @Value("${sealStudyingImagePath}")
    private String sealStudyingImagePath;

    /**
     * Sign PDF method with seal.
     * @param response
     * @param request
     */
    public void signPdf(HttpServletResponse response, HttpServletRequest request){
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            KeyStore ks = KeyStore.getInstance(Constant.PKCS12);
            ks.load(new FileInputStream(pkpath), Constant.PASSWORD);
            String alias = ks.aliases().nextElement();
            PrivateKey pk = (PrivateKey) ks.getKey(alias, Constant.PASSWORD);
            // 得到证书链
            Certificate[] chain = ks.getCertificateChain(alias);
            // 封装签章信息
            SignatureInfo signInfo = new SignatureInfo();
            signInfo.setReason("理由");
            signInfo.setLocation("位置");
            signInfo.setPk(pk);
            signInfo.setChain(chain);
            signInfo.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            signInfo.setDigestAlgorithm(DigestAlgorithms.SHA1);
            signInfo.setFieldName("demo");
            // 签章图片
            signInfo.setImagePath(sealStudyingImagePath);
            signInfo.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);

            inputStream = new FileInputStream(srcPDFPath);
            ByteArrayOutputStream tempArrayOutputStream = new ByteArrayOutputStream();
            PdfReader reader = new PdfReader(inputStream);
            // 创建签章工具PdfStamper ，最后一个boolean参数是否允许被追加签名
            // false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
            // true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
            PdfStamper stamper = PdfStamper.createSignature(reader,
                    tempArrayOutputStream, '\0', null, true);
            // 获取数字签章属性对象
            PdfSignatureAppearance appearance = stamper
                    .getSignatureAppearance();
            appearance.setReason(signInfo.getReason());
            appearance.setLocation(signInfo.getLocation());
            // 设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样 图片大小受表单域大小影响（过小导致压缩）
            // 签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
            // 四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
            //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y Rectangle(xPos, yPos, xPos + width, yPos + heith)
            appearance.setVisibleSignature(new Rectangle(280, 220, 610, 370), 1, "sig1");
            // 读取图章图片
            Image image = Image.getInstance(signInfo.getImagePath());
            image.setAbsolutePosition(500,0);
            appearance.setSignatureGraphic(image);
            appearance.setCertificationLevel(signInfo
                    .getCertificationLevel());
            // 设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
            appearance.setRenderingMode(signInfo.getRenderingMode());
            // 这里的itext提供了2个用于签名的接口，可以自己实现，后边着重说这个实现
            // 摘要算法
            ExternalDigest digest = new BouncyCastleDigest();
            // 签名算法
            ExternalSignature signature = new PrivateKeySignature(
                    signInfo.getPk(), signInfo.getDigestAlgorithm(),
                    null);
            // 调用itext签名方法完成pdf签章 //数字签名格式，CMS,CADE
            MakeSignature.signDetached(appearance, digest, signature,
                    signInfo.getChain(), null, null, null, 0,
                    MakeSignature.CryptoStandard.CADES);

            inputStream = new ByteArrayInputStream(
                    tempArrayOutputStream.toByteArray());
            // 定义输入流为生成的输出流内容，以完成多次签章的过程
            result = tempArrayOutputStream;

            outputStream = new FileOutputStream(new File(targetPDFPath));
            outputStream.write(result.toByteArray());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != result) {
                    result.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
