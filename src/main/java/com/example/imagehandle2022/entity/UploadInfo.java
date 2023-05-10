package com.example.imagehandle2022.entity;

import com.lowagie.text.Image;
import lombok.*;
import java.io.File;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadInfo {
    private String schoolName;
    private String sealName;
    private File file;
    private Image image;
}
