package com.example.imagehandle2022.seal.util;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

@Service
@Slf4j
public class RSAEncryptUtil {
    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;
    /**
     * 公钥
     */
    private RSAPublicKey publicKey;
    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    /**
     * 获取私钥
     * @return 当前的私钥对象
     */
    private RSAPrivateKey getPrivateKey() {
        return privateKey;
    }
    /**
     * 获取公钥
     * @return 当前的公钥对象
     */
    private RSAPublicKey getPublicKey() {
        return publicKey;
    }
    @Value("${RSAPublicKeyPath}")
    private String RSAPublicKeyPath;
    @Value("${RSAPrivateKeyPath}")
    private String RSAPrivateKeyPath;

    public RSAEncryptUtil(){
        try {
            loadKey();
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }
    /**
     * 随机生成密钥对
     */
    public void genKeyPair(){
        KeyPairGenerator keyPairGen= null;
        try {
            keyPairGen= KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGen.initialize(1024, new SecureRandom());
        KeyPair keyPair= keyPairGen.generateKeyPair();
        this.privateKey= (RSAPrivateKey) keyPair.getPrivate();
        this.publicKey= (RSAPublicKey) keyPair.getPublic();
    }

    private void loadKey() throws Exception{
        loadPublicKey(new FileInputStream(RSAPublicKeyPath));
        loadPrivateKey(new FileInputStream(RSAPrivateKeyPath));
    }
    /**
     * 从文件中输入流中加载公钥
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(InputStream in) throws Exception{
        try {
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String readLine= null;
            StringBuilder sb= new StringBuilder();
            while((readLine= br.readLine())!=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else{
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("Stream of public key hits error when reading");
        } catch (NullPointerException e) {
            throw new Exception("Stream of public key is null");
        }
    }

    /**
     * Loading public key from string
     * @param publicKeyStr
     * @throws Exception
     */
    public void loadPublicKey(String publicKeyStr) throws Exception{
        try {
            BASE64Decoder base64Decoder= new BASE64Decoder();
            byte[] buffer= base64Decoder.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);
            this.publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("this algorithm can't be found");
        } catch (InvalidKeySpecException e) {
            throw new Exception("illegal public key");
        } catch (IOException e) {
            throw new Exception("Reading public key error");
        } catch (NullPointerException e) {
            throw new Exception("Data of public key is null");
        }
    }

    /**
     * Loading private key from file.
     * @param  in
     * @return void
     * @throws Exception
     */
    public void loadPrivateKey(InputStream in) throws Exception{
        try {
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String readLine= null;
            StringBuilder sb= new StringBuilder();
            while((readLine= br.readLine())!=null){
                if(readLine.charAt(0)=='-'){
                    continue;
                }else{
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("Reading private key error.");
        } catch (NullPointerException e) {
            throw new Exception("The stream of private key is null.");
        }
    }
    public void loadPrivateKey(String privateKeyStr) throws Exception{
        try {
            BASE64Decoder base64Decoder= new BASE64Decoder();
            byte[] buffer= base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");
            this.privateKey= (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Can't be found algorithm");
        } catch (InvalidKeySpecException e) {
            throw new Exception("Illegal private key.");
        } catch (IOException e) {
            throw new Exception("Reading private key Exception.");
        } catch (NullPointerException e) {
            throw new Exception("Private key is null.");
        }
    }

    /**
     * Encrypted String.
     * @param
     * @param plainTextData
     * @return
     * @throws Exception
     */
    public String encrypt(String plainTextData) throws Exception{
        if(publicKey== null){
            throw new Exception("public key is null.");
        }
        Cipher cipher= null;
        try {
            //cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher= Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
            byte[] output= cipher.doFinal(base64String2ByteFun(plainTextData));
            return byte2Base64StringFun(output);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("The algorithm can't be found.");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }
    /**
     * 解密过程
     * @param
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public String decrypt(String cipherData) throws Exception{
        if (this.privateKey== null){
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher= null;
        try {
            cipher= Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
            byte[] output= cipher.doFinal(base64String2ByteFun(cipherData));
            return byte2Base64StringFun(output);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }
    /**
     * 字节数据转十六进制字符串
     * @param data 输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data){
        StringBuilder stringBuilder= new StringBuilder();
        for (int i=0; i<data.length; i++){
        //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);
        //取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i<data.length-1){
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

    //base64字符串转byte[]
    public static byte[] base64String2ByteFun(String base64Str){
        return Base64.decodeBase64(base64Str);
    }
    //byte[]转base64
    public static String byte2Base64StringFun(byte[] b){
        return Base64.encodeBase64String(b);
    }

    public static void main(String[] args){
        String filePubKPath="D:\\GZKF\\code\\rsa_public_key.txt";
        String filePriKPath="D:\\GZKF\\code\\rsa_private_key.txt";
        RSAEncryptUtil rsaEncrypt= new RSAEncryptUtil();
        //rsaEncrypt.genKeyPair();
        //加载公钥
        try {
            InputStream in = new FileInputStream(filePubKPath);
            rsaEncrypt.loadPublicKey(in);
            //rsaEncrypt.loadPublicKey(rsaEncrypt.loadPublicKey(new FileInputStream(filePath)));
            System.out.println("loading public key successful");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("loading public key failure");
        }
        //加载私钥
        try {
            rsaEncrypt.loadPrivateKey(new FileInputStream(filePriKPath));
            //rsaEncrypt.loadPrivateKey(RSAEncryptUtil.DEFAULT_PRIVATE_KEY);
            System.out.println("loading private key successful");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("loading private key failure");
        }
        //测试字符串
        String encryptStr= "Test String chaijunkun";
        try {
            //加密
            String cipher = rsaEncrypt.encrypt(encryptStr);
            //解密
            String plainText = rsaEncrypt.decrypt(cipher);
            log.info("cipher text length:"+ cipher.length());
            log.info("plainText length:"+ plainText.length());
            log.info("encrypted string: "+cipher);
            log.info("decrypted string: "+plainText);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
