package com.example.imagehandle2022.entity;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SealApplyInfo {
    private String applyId;
    private String applyPersonId;
    private String institutionCode;
    private String sealName;
    private String sealCode;
}
