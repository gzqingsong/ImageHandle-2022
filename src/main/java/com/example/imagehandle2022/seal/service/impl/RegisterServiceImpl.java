package com.example.imagehandle2022.seal.service.impl;

import com.example.imagehandle2022.constant.Constant;
import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.entity.UploadInfo;
import com.example.imagehandle2022.seal.service.IRegisterService;
import com.example.imagehandle2022.seal.service.ISealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;

@Service
@Slf4j
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private ISealService sealService;
    @Value("sealBasePath")
    private String sealBasePath;

    @Override
    public void register(UploadInfo uploadInfo) throws Exception{
        SchoolSealInfo schoolSealInfoQuery=new SchoolSealInfo();
        schoolSealInfoQuery.setSealName(uploadInfo.getSealName());
        schoolSealInfoQuery.setSchool(uploadInfo.getSchoolName());
        SchoolSealInfo schoolSealInfoResp=sealService.querySchoolSealInfo(schoolSealInfoQuery);
        if(!StringUtils.isEmpty(schoolSealInfoResp.getSchool())&&!StringUtils.isEmpty(schoolSealInfoResp.getSealName())){
            throw new Exception("注册重复数据");
        }
        if(null==schoolSealInfoResp){
            SchoolSealInfo schoolSealInfo=new SchoolSealInfo();
            schoolSealInfo.setSchool(uploadInfo.getSchoolName());
            schoolSealInfo.setSchoolCode(Constant.SEAL_START_NUMBER);
            schoolSealInfo.setSealName(uploadInfo.getSealName());
            schoolSealInfo.setSealCode(Constant.SCHOOL_START_NUMBER);

            String sealPath= sealBasePath+File.separator + Constant.SCHOOL_START_NUMBER;
            schoolSealInfo.setSealPath(sealPath);

            sealService.insertSchoolSealInfo(schoolSealInfo);
        }else{

        }

    }
}
