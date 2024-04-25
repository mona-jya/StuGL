package com.mona.stugl.user.mapper;

import com.mona.stugl.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
@Mapper
public interface UserMapper {
    public List<User> selectUserList();
    public int addUser(User user);
    public int deleteUserById(int id);
    public int updateUser(User user);

    List<User> selectUserPage(int pageIndex, int pageSize);

    User selectUserById(Integer id);

    List<User> selectUserByName(String name);

    int deleteUserByList(Integer[] ids);

    User selectuserByIdAndName(int id, String name);
}
