package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.entity.SealApplyInfo;

public interface ISealService {
    public SchoolSealInfo querySchoolSealInfo(SchoolSealInfo schoolSealInfo);
    int insertSchoolSealInfo(SchoolSealInfo schoolSealInfo);
    int insertSealApplyInfo(SealApplyInfo sealApplyInfo);
}
