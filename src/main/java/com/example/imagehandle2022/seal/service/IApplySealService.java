package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.SealApplyInfo;

import java.util.List;

public interface IApplySealService {
    int insertApplyInfo(SealApplyInfo sealApplyInfo);
    SealApplyInfo queryApplyInfo(SealApplyInfo sealApplyInfo);
    List<SealApplyInfo> querySealApplyInfoList(SealApplyInfo sealApplyInfo);
}
