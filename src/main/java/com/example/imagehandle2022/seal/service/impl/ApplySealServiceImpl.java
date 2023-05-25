package com.example.imagehandle2022.seal.service.impl;

import com.example.imagehandle2022.entity.SealApplyInfo;
import com.example.imagehandle2022.seal.mapper.ApplyInfoMapper;
import com.example.imagehandle2022.seal.service.IApplySealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class ApplySealServiceImpl implements IApplySealService {
    @Autowired
    private ApplyInfoMapper applyInfoMapper;

    /**
     * Query apply info with applyId.
     * @param sealApplyInfo
     * @return
     */
    @Override
    public SealApplyInfo queryApplyInfo(SealApplyInfo sealApplyInfo){
        return applyInfoMapper.querySealApplyInfo(sealApplyInfo);
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

    /**
     * Insert into seal apply info to DB.
     * @param sealApplyInfo
     * @return
     */
    @Override
    public List<SealApplyInfo> querySealApplyInfoList(SealApplyInfo sealApplyInfo){
        return applyInfoMapper.querySealApplyInfoList(sealApplyInfo);
    }
}
