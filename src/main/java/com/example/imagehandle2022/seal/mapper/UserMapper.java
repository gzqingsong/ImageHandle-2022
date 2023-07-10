package com.example.imagehandle2022.seal.mapper;


import com.example.imagehandle2022.entity.UserCriteria;
import com.example.imagehandle2022.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhaotong
 * @Description TODO
 * @Date 2021/4/7
 * @Version 1.0
 */
@Repository
public interface UserMapper {

    void saveUser(UserInfo user);

    void updateUser(UserInfo user);

    UserInfo getUserById(Long id);

    List<UserInfo> findUserByCriteria(UserCriteria criteria);

    UserInfo login(UserInfo user);

    /**
     * 获取数据量
     * @return
     */
    Integer getCount(UserCriteria criteria);

    void deleteUserById(Long id);

    UserInfo getUserByUsername(String username);
}
