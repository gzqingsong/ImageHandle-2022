package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.entity.SealApplyInfo;
import com.example.imagehandle2022.seal.service.ApplySealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Description:
 * @Author: Tong
 * @CreateDate: 2023-05-24
 */
@RequestMapping
@RestController
@Slf4j
public class SealApplyController {
    @Autowired
    private ApplySealService applySealService;

    @Autowired
    private Environment env;
    /**
     * Query seal and it's path info with seal code and school code.
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/query-seal-info")
    public SealApplyInfo querySealInfo(HttpServletRequest request){
        String sealCode=request.getHeader("gzou-seal-code");
        String schoolCode=request.getHeader("gzou-school-code");
        SealApplyInfo schoolSealInfoReq=new SealApplyInfo();
        schoolSealInfoReq.setSealCode(sealCode);
        SealApplyInfo schoolSealInfo=applySealService.queryApplyInfo(schoolSealInfoReq);

        return schoolSealInfo;
    }

    /**
     * Query seal and it's path info with seal code and school code.
     * @param
     * @param request
     * @return
     */
    @PostMapping("/insert-seal-info")
    public int insertSealInfo(HttpServletRequest request, @Valid @RequestBody SealApplyInfo sealApplyInfo){
        String sealCode=request.getHeader("gzou-seal-code");
        String schoolCode=request.getHeader("gzou-school-code");
        String envInfo=env.getProperty("spring.profiles.active");
        sealApplyInfo.setSealCode(sealCode);
        sealApplyInfo.setApplyId("");
        sealApplyInfo.setApplyPersonId("");
        sealApplyInfo.setInstitutionCode(schoolCode);

        log.info("Insert seal apply info to DB.");
        return applySealService.insertApplyInfo(sealApplyInfo);
    }
}
