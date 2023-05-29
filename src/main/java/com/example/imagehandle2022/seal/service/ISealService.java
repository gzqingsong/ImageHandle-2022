package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.entity.SealApplyInfo;
import java.util.List;

public interface ISealService {
    SchoolSealInfo querySchoolSealInfo(SchoolSealInfo schoolSealInfo);
    List<SchoolSealInfo> querySchoolSealInfoList(SchoolSealInfo schoolSealInfo);
    int insertSchoolSealInfo(SchoolSealInfo schoolSealInfo);
    int insertSealApplyInfo(SealApplyInfo sealApplyInfo);
}
