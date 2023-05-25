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
    private String apply_person_id;
    private String institutionCode;
    private String sealName;
    private String sealCode;
    private String applyDate;
}
