package com.example.imagehandle2022.seal.mapper;

import com.example.imagehandle2022.entity.SealApplyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ApplyInfoMapper {
     int insertApplyInfo(SealApplyInfo sealApplyInfo);
    SealApplyInfo queryApplyInfo(SealApplyInfo sealApplyInfo);
}
