package com.example.imagehandle2022.seal.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Row;
import java.text.DecimalFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


@Slf4j
@Service
public class ExcelServiceImpl {

    /**
     * 获取Excel文件的数据并将数据以List<map<String,String>>的格式返回数据，每一个map代表一行数据
     * @Param workbook 需要操作的Excel文件
     * @Param colNames 列名数组，需要该Excel表的每一列所对应的信息（将作为map的key值）
     * @param SheetNum 需要使用的是Excel的第几张表
     * @return
     */
    public List<Map<String,String>> getExcelValues(Workbook workbook,String[] colNames,int SheetNum){
        //初始化基础数据
        List<Map<String,String>> list = new ArrayList<>();    //用于存储最终结果
        Map<String,String> map = new HashMap<>();    //用于逐一存储表格的每一行数据
        String cellData;
        Sheet sheet = workbook.getSheetAt(SheetNum);    //获取Excel文件的表格
        int rowNum = sheet.getPhysicalNumberOfRows();    //获取当前表格最大行数
        //循环遍历表格的每一行，获取每一行的值(i的初始值决定从表格的第几行开始)
        for(int i = 0 ; i < rowNum ; i ++){
            map = new HashMap<>();    //清除map中的数据
            Row row = sheet.getRow(i);    //获取表格中第i行的数据
            //循环遍历表格第i行的每一个单元格的值(j的初始值决定从表格的第几列开始）
            for(int j = 0 ; j < colNames.length ; j ++){
                try{    //这里可能会报错，try{}catch{}一下
                    cellData = (String)row.getCell(j).getStringCellValue();
                }catch(Exception e){
                    try{    //如果某一个单元格为纯数字的字符串时会报错，需特殊处理
                        String[] a = new DecimalFormat().format(row.getCell(j).getNumericCellValue()).split(",");
                        cellData = a[0];
                        for(int k = 1; k < a.length ; k++){
                            cellData += a[k];
                        }
                    }catch(Exception e1){
                        cellData = null;
                    }
                }
                map.put(colNames[j],cellData);        //将第i行第j个单元格数据存入map中
            }
            list.add(map);    //将第i行数据存入list中
        }
        return list;
    }

    /**
     * 将EXCEL文件转化为java可操作的Workbook类型
     * @param file Excel文件的流对象
     * @return Workbook workbook java可以操纵的文件类型
     */
    public Workbook getWorkbook(MultipartFile file){
        //获取文件类型，即文件名后缀，通过获取文件名并用.分割文件名，并取用返回值的第二个下标1
        String fileType = file.getOriginalFilename().split("\\.")[1];
        Workbook workbook = new XSSFWorkbook();
        try{
            if(fileType.equals("xls")){
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if(fileType.equals("xlsx")){
                workbook = new XSSFWorkbook(file.getInputStream());
            } else {
                return null;
            }
        }catch(Exception e){
            return null;
        }

        return workbook;
    }


    public List<Map<String,String>> uploadExcel(MultipartFile file){
        Workbook workbook = getWorkbook(file);
        String[] colNames = {"","","","","","",};	//表格从第一列开始的每一列列名，这个参数会作为map的key值，不可重复！！！
        List<Map<String,String>> list = getExcelValues(workbook,colNames,1);//这里具体的参数请看1.3解析部分方法前的注释

        return list;
    }


}
