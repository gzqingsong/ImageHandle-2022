package com.example.imagehandle2022.entity;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolSealInfo {
    private String school;
    private String schoolCode;
    private String sealName;
    private String sealCode;
    private String sealPath;
    private String applyDate;
}
