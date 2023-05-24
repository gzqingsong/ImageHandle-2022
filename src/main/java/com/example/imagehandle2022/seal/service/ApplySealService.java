package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.SealApplyInfo;

public interface ApplySealService {
    int insertApplyInfo(SealApplyInfo sealApplyInfo);
    SealApplyInfo queryApplyInfo(SealApplyInfo sealApplyInfo);
}
