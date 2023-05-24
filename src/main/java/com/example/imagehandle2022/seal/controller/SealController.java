package com.example.imagehandle2022.seal.controller;

import com.example.imagehandle2022.entity.SchoolSealInfo;
import com.example.imagehandle2022.seal.service.ISealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.core.env.Environment;

@RestController
@Slf4j
public class SealController {
    @Autowired
    private ISealService sealService;

    @Autowired
    private Environment env;

    @GetMapping("/seal")
    public String equals() {
        log.info("this is test for server.");
        return "This is a seal project2.";
    }
    /**
     * Query seal and it's path info with seal code and school code.
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/query-seal-info")
    public SchoolSealInfo querySealInfo(HttpServletResponse response, HttpServletRequest request){
        String sealCode=request.getHeader("gzou-seal-code");
        String schoolCode=request.getHeader("gzou-school-code");
        SchoolSealInfo schoolSealInfo=sealService.querySchoolSealInfo(schoolCode,sealCode);

        return schoolSealInfo;
    }

    /**
     * Query seal and it's path info with seal code and school code.
     * @param
     * @param request
     * @return
     */
    @PostMapping("/insert-seal-info")
    public int insertSealInfo(HttpServletRequest request, @Valid @RequestBody SchoolSealInfo schoolSealInfo){
        String sealCode=request.getHeader("gzou-seal-code");
        String schoolCode=request.getHeader("gzou-school-code");
        String envInfo=env.getProperty("spring.profiles.active");
        if(envInfo.equals("local")) {
            schoolSealInfo.setSealPath("D:\\GZKF\\code\\pdf-services\\src\\main\\resources\\pdf\\chapter.png");
        }else{
            schoolSealInfo.setSealPath("seal\\image\\"+sealCode+".png");
        }
        schoolSealInfo.setSealCode(sealCode);
        schoolSealInfo.setSchoolCode(schoolCode);
        schoolSealInfo.setSealName(schoolSealInfo.getSealName());
        schoolSealInfo.setSchool(schoolSealInfo.getSchool());
        log.info("Insert seal info to DB.");
        return sealService.insertSchoolSealInfo(schoolSealInfo);
    }
}
