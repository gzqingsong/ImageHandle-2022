package com.example.imagehandle2022.seal.service.impl;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.entity.SealApplyInfo;
import com.example.imagehandle2022.seal.mapper.SchoolSealMapper;
import com.example.imagehandle2022.seal.service.ISealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class SealServiceImpl implements ISealService {
    @Autowired
    private SchoolSealMapper schoolSealMapper;

    /**
     * Query scholl and corresponding seal with school and sealCode.
     * @param schoolSealInfo
     * @return
     */
    @Override
    public SchoolSealInfo querySchoolSealInfo(SchoolSealInfo schoolSealInfo){
        return schoolSealMapper.querySchoolSealInfo(schoolSealInfo);
    }

    /**
     * Query scholl list and corresponding seal with school and sealCode.
     * @param schoolSealInfo
     * @return
     */
    @Override
    public List<SchoolSealInfo> querySchoolSealInfoList(SchoolSealInfo schoolSealInfo){
        return schoolSealMapper.querySchoolSealInfoList(schoolSealInfo);
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

    /**
     * Insert into seal and seal apply info to DB.
     * @param sealApplyInfo
     * @return int
     */
    @Override
    public int insertSealApplyInfo(SealApplyInfo sealApplyInfo){
       return schoolSealMapper.insertSealApplyInfo(sealApplyInfo);
    }
}
