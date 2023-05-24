package com.example.imagehandle2022.seal.service.impl;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.entity.SealApplyInfo;
import com.example.imagehandle2022.seal.mapper.ApplyInfoMapper;
import com.example.imagehandle2022.seal.mapper.SchoolSealMapper;
import com.example.imagehandle2022.seal.service.ApplySealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplySealServiceImpl implements ApplySealService {
    @Autowired
    private ApplyInfoMapper applyInfoMapper;

    /**
     * Query apply info with applyid.
     * @param sealApplyInfo
     * @return
     */
    @Override
    public SealApplyInfo queryApplyInfo(SealApplyInfo sealApplyInfo){
        return applyInfoMapper.queryApplyInfo(sealApplyInfo);
    }


    /**
     * Insert into seal apply info to DB.
     * @param sealApplyInfo
     * @return
     */
    @Override
    public int insertApplyInfo(SealApplyInfo sealApplyInfo){
        return applyInfoMapper.insertApplyInfo(sealApplyInfo);
    }
}
