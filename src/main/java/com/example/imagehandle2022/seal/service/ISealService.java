package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.entity.UploadInfo;

public interface ISealService {
    public SchoolSealInfo querySchoolSealInfo(String school, String sealCode);
    int insertSchoolSealInfo(SchoolSealInfo schoolSealInfo);
}
