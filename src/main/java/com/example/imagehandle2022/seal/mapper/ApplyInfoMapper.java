package com.example.imagehandle2022.seal.mapper;

import com.example.imagehandle2022.entity.SealApplyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ApplyInfoMapper {
     int insertApplyInfo(SealApplyInfo sealApplyInfo);
    SealApplyInfo querySealApplyInfo(SealApplyInfo sealApplyInfo);
    List<SealApplyInfo> querySealApplyInfoList(SealApplyInfo sealApplyInfo);
}
