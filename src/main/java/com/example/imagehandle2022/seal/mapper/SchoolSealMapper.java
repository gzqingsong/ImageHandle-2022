package com.example.imagehandle2022.seal.mapper;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.entity.SealApplyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SchoolSealMapper {
    SchoolSealInfo querySchoolSealInfo(SchoolSealInfo schoolSealInfo);
    int insertSchoolSealInfo(SchoolSealInfo schoolSealInfo);

    int insertSealApplyInfo(SealApplyInfo sealApplyInfo);
}
