package com.example.imagehandle2022.seal.util;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Log
public class CharConvertHtmlUtil {

        public void char2Html(String str, String modelName) throws Exception {
            String path = "src/main/resources/template";
            File fileDoc = new File(path);
            //如果文件夹不存在
            if (!fileDoc.exists()) {
                //创建文件夹
                fileDoc.mkdirs();
            }
            String htmlAbsolutePath = fileDoc.getAbsolutePath();
            File outFile = new File(htmlAbsolutePath+ "/" + modelName + ".html");
            BufferedWriter output = null;
            try {
                output = new BufferedWriter(new FileWriter(outFile));
                output.write(str);
            } catch (
                    IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
