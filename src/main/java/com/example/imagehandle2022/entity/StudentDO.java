package com.example.imagehandle2022.entity;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDO {
    private String studentId;
    private String studentName;
    private String cardId;
    private String cardType;
    private String branchCode;
    private String branchName;
    private String entryDate;
    private String genderCode;
    private String gender;
    private String birthday;
    private String nationCode;
    private String examineeNumber;
    private String phoneNumber;
    private String studentStatusCode;
    private String studentStatus;
    private String studentTypeCode;
    private String studentType;
    private String academyCode;
    private String academy;
    private String centerCode;
    private String center;
    private String classCode;
    private String className;
    private String majorLevelCode;
    private String majorLevel;
    private String majorCode;
    private String major;
}
