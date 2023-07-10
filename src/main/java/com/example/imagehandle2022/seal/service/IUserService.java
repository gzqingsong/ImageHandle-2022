package com.example.imagehandle2022.seal.service;

import com.example.imagehandle2022.entity.UserCriteria;
import com.example.imagehandle2022.entity.UserDTO;
import com.example.imagehandle2022.entity.ResultEntity;

/**
 * @Author Tong Zhao
 * @Description TODO
 * @Date 2023/7/10
 * @Version 1.0
 */
public interface IUserService {

    ResultEntity getUserById(Long id);

    ResultEntity saveOrUpdate(UserDTO userDTO);

    ResultEntity findUserByCriteria(UserCriteria criteria);

    ResultEntity login(UserDTO userDTO);

    ResultEntity deleteUserById(Long id);

    String getFullName(Long id);
}
