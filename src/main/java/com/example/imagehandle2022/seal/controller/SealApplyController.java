package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.entity.SealApplyInfo;
import com.example.imagehandle2022.seal.service.IApplySealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.imagehandle2022.seal.util.ConmmonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Description: This class mainly includes two functionalities. One is inserting apply info to DB.
 * Another one is querying apply info from DB.
 * @Author: Tong
 * @CreateDate: 2023-05-24
 */
@RequestMapping
@RestController
@Slf4j
public class SealApplyController {
    @Autowired
    private IApplySealService applySealService;
    @Autowired
    private Environment env;
    @Autowired
    private ConmmonUtil conmmonUtil;

    /**
     * Query seal and it's path info with seal code and school code.
     * @param request
     * @return
     */
    @RequestMapping("/query-seal-apply-info")
    public SealApplyInfo querySealInfo(HttpServletRequest request){
        String applyId=request.getHeader("gzou-apply-id");

        SealApplyInfo schoolSealInfoReq=new SealApplyInfo();
        schoolSealInfoReq.setApplyId(applyId);
        SealApplyInfo schoolSealInfo=applySealService.queryApplyInfo(schoolSealInfoReq);

        return schoolSealInfo;
    }

    /**
     * Query seal and it's path info with seal code and school code.
     * @param request
     * @return
     */
    @PostMapping("/insert-seal-apply-info")
    public int insertSealInfo(HttpServletRequest request, @Valid @RequestBody SealApplyInfo sealApplyInfo){
        String sealCode=request.getHeader("gzou-seal-code");
        String studengId=request.getHeader("gzou-student-id");
        String schoolCode=request.getHeader("gzou-school-code");
        String envInfo=env.getProperty("spring.profiles.active");
        sealApplyInfo.setSealCode(sealCode);
        sealApplyInfo.setApplyId(conmmonUtil.getApplyId(studengId));
        sealApplyInfo.setApply_person_id(studengId);
        sealApplyInfo.setInstitutionCode(schoolCode);

        log.info("Insert seal apply info to DB.");

        return applySealService.insertApplyInfo(sealApplyInfo);
    }
}
