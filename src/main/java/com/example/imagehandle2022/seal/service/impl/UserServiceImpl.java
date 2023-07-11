package com.example.imagehandle2022.seal.service.impl;

import com.example.imagehandle2022.entity.UserCriteria;
import com.example.imagehandle2022.entity.UserDTO;
import com.example.imagehandle2022.entity.UserInfo;
import com.example.imagehandle2022.seal.enums.GenderCode;
import com.example.imagehandle2022.seal.enums.RoleNameCode;
import com.example.imagehandle2022.seal.util.BaseUtils;
import com.example.imagehandle2022.seal.util.DataUtils;
import com.example.imagehandle2022.entity.ResultEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.imagehandle2022.seal.service.IUserService;
import com.example.imagehandle2022.seal.util.StringUtils;
import com.example.imagehandle2022.seal.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 陈俊源
 * @Description TODO
 * @Date 2021/4/10
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultEntity login(UserDTO userDTO) {
        UserInfo userParameter = BaseUtils.copyProperties(userDTO, UserInfo.class);
        UserInfo user = userMapper.login(userParameter);
        if(user==null){
            log.info("Fail to login for the error of account or password");
            return ResultEntity.fail("登录失败，用户账号或密码错误");
        }
        UserDTO dto = bulidUserDTO(user);
        return ResultEntity.success(dto);
    }

    @Override
    public ResultEntity getUserById(Long id) {
        if (id == null){
            return ResultEntity.fail("用户id为空，无法获取用户信息");
        }
        UserInfo user = userMapper.getUserById(id);
        if (user==null){
            return ResultEntity.fail("无法获取该id对应的用户信息");
        }
        UserDTO userDTO = bulidUserDTO(user);
        return ResultEntity.success(userDTO);
    }

    @Transactional
    @Override
    public ResultEntity deleteUserById(Long id){
        if (id == null){
            return ResultEntity.fail("用户id为空，无法执行删除操作");
        }
        UserInfo user = userMapper.getUserById(id);
        if (user == null){
            return ResultEntity.fail("未找到该id对应的用户信息，无法执行删除操作");
        }
        switch (user.getRoleName()){
            case 1:
                //teacherMapper.deleteTeacherById(id);
                break;
            case 2:
                //studentMapper.deleteStudentById(id);
                break;
            default:
                break;
        }
        userMapper.deleteUserById(id);
        return ResultEntity.success(id);
    }

    @Transactional
    @Override
    public ResultEntity saveOrUpdate(UserDTO userDTO) {
        ResultEntity result = checkUserInfo(userDTO);
        if (!ResultEntity.isSuccess(result)){
            return result;
        }
        Long id = DataUtils.assignId();
        UserInfo user = BaseUtils.copyProperties(userDTO, UserInfo.class);
        if (userDTO.getId()==null){
            user.setId(id);
            Boolean uniqueFlag = verifyUnique(user.getUsername());
            if (!uniqueFlag){
                return ResultEntity.fail("用户账号已存在");
            }
            userMapper.saveUser(user);
        }else {
            userMapper.updateUser(user);
        }
        switch (userDTO.getRoleName()){
            case 1:
//                Teacher teacher = BaseUtils.copyProperties(userDTO, Teacher.class);
//                if (userDTO.getId()==null){
//                    teacher.setId(id);
//                    teacherMapper.saveTeacher(teacher);
//                }else{
//                    Teacher teacherById = teacherMapper.getTeacherById(teacher.getId());
//                    BaseUtils.copyProperties(teacher,teacherById,true);
//                    teacherMapper.updateTeacher(teacherById);
//                }
                break;
            case 2:
//                Student student = BaseUtils.copyProperties(userDTO, Student.class);
//                if (userDTO.getId()==null){
//                    student.setId(id);
//                    studentMapper.saveStudent(student);
//                }else{
//                    Student studentById = studentMapper.getStudentById(student.getId());
//                    BaseUtils.copyProperties(student,studentById,true);
//                    studentMapper.updateStudent(studentById);
//                }
                break;
            default:
                break;
        }
        return ResultEntity.success(user.getId());
    }

    @Override
    public ResultEntity findUserByCriteria(UserCriteria criteria) {
        PageHelper.startPage(criteria.getPageNum(),criteria.getPageSize(),false);
        List<UserInfo> userList = userMapper.findUserByCriteria(criteria);
        Integer count = userMapper.getCount(criteria);
        List<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (UserInfo user :userList ) {
            UserDTO userDTO = bulidUserDTO(user);
            userDTOList.add(userDTO);
        }
        PageInfo<UserDTO> pageInfo = new PageInfo<UserDTO>(userDTOList);
        pageInfo.setTotal(count.longValue());
        return ResultEntity.success(pageInfo);
    }

    public UserDTO bulidUserDTO(UserInfo user){
        UserDTO userDTO = BaseUtils.copyProperties(user, UserDTO.class);
        userDTO.setGenderDec(GenderCode.getValue(user.getGender()));
        userDTO.setRoleNameDec(RoleNameCode.getValue(user.getRoleName()));
        return userDTO;
    }


    /**
     * check the account if it is unique.
     * @param username
     * @return
     */
    public Boolean verifyUnique(String username){
        UserInfo user = userMapper.getUserByUsername(username);
        if (user==null){
            return true;
        }
        return false;
    }

    /**
     * 根据id获取姓名
     * @param id
     * @return
     */
    @Override
    public String getFullName(Long id){
        UserInfo userById = userMapper.getUserById(id);
        if (userById==null){
            return null;
        }
        return userById.getFullName();
    }



    //TODO varify the info.
    private ResultEntity checkUserInfo(UserDTO userDTO) {
        if (userDTO==null){
            return ResultEntity.fail("数据传输对象不能为空！");
        }
        if (!StringUtils.isNotNUllAndNotEntry(userDTO.getFullName())){
            return ResultEntity.fail("姓名不能为空！");
        }
        if (!StringUtils.isNotNUllAndNotEntry(userDTO.getUsername())){
            return ResultEntity.fail("用户账号不能为空！");
        }
        if(!StringUtils.isNotNUllAndNotEntry(userDTO.getPassword())){
            return ResultEntity.fail("用户密码不能为空！");
        }
        if (userDTO.getBirthDate()==null){
            return ResultEntity.fail("生日不能为空");
        }
        if (!StringUtils.isNotNUllAndNotEntry(userDTO.getContactWay())){
            return ResultEntity.fail("联系方式不能为空！");
        }
        if (userDTO.getRoleName()==null){
            return ResultEntity.fail("用户角色不能为空");
        }
        if(userDTO.getAge()==null){
            return ResultEntity.fail("用户年龄不能为空");
        }
//        if (userDTO.getBirthDay()==null){
//            return Result.fail("用户出生年月不能为空！");
//        }
        return ResultEntity.success();
    }
}
