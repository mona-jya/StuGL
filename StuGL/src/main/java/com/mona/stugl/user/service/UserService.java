package com.mona.stugl.user.service;

import com.mona.stugl.user.domain.User;
import com.mona.stugl.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Author mona
 * @Date 2024/4/23 15:33
 * @Version 1.0
 * @Description TODO
 **/
@Service
public class UserService {
    @Autowired
     UserMapper userMapper;

    public  List<User> selectUserPage(int pageIndex, int pageSize) {

        return userMapper.selectUserPage(pageIndex, pageSize);
    }

    public List<User> selectUserList(){
        List<User> Users = userMapper.selectUserList();
        return Users;
    }
    public int addUser(User User){
        int i = userMapper.addUser(User);
        return i;

    }
    public int deleteUserById(int id){
        return userMapper.deleteUserById(id);
    }
    public int updateUser(User User){
        return userMapper.updateUser(User);
    }

    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    public List<User> selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    public int deleteUserByList(Integer[] ids) {
        return userMapper.deleteUserByList(ids);
    }

    public User selectuserByIdAndName(int id, String name) {
        return userMapper.selectuserByIdAndName(id, name);
    }
}
