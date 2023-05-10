package com.example.imagehandle2022.seal.impl;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.seal.mapper.SchoolSealMapper;
import com.example.imagehandle2022.seal.service.ISealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SealServiceImpl implements ISealService {
    @Autowired
    private SchoolSealMapper schoolSealMapper;

    /**
     * Query scholl and corresponding seal with school and sealCode.
     * @param school
     * @param sealCode
     * @return
     */
    @Override
    public SchoolSealInfo querySchoolSealInfo(String school, String sealCode){
        return schoolSealMapper.querySchoolSealInfo(school,sealCode);
    }


    /**
     * Insert into school and seal info to DB.
     * @param schoolSealInfo
     * @return
     */
    @Override
    public int insertSchoolSealInfo(SchoolSealInfo schoolSealInfo){
        return schoolSealMapper.insertSchoolSealInfo(schoolSealInfo);
    }
}
