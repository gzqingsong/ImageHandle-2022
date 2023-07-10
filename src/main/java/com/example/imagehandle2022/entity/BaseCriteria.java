package com.example.imagehandle2022.entity;

import lombok.Data;

/**
 * @Author 陈俊源
 * @Description TODO
 * @Date 2021/4/14
 * @Version 1.0
 */
@Data
public abstract class BaseCriteria {
    private Integer pageNum;
    private Integer pageSize;
}
