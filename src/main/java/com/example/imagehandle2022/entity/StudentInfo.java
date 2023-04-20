package com.example.imagehandle2022.entity;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {
    public String stuName;
    public String studentId;
    public String stuCardNo;
    public String stuMajor;
    public String yearDate;
    public String monthDate;
    private String sex;
    private String education;
}
