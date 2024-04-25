package com.mona.stugl.user.controller;

import com.mona.stugl.config.MyTokenUtil;
import com.mona.stugl.student.domain.Student;
import com.mona.stugl.user.domain.User;
import com.mona.stugl.user.service.UserService;

import com.mona.stugl.user.service.UserService;
import com.mona.stugl.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;
@CrossOrigin
@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestBody User user){
        if(user.getName()!=null && user.getPassword() != null){
            //加密密码
            //盐值
            String salt = "mona come up !";
            String md5Password = DigestUtils.md5DigestAsHex((user.getPassword()+salt).getBytes());
            user.setPassword(md5Password);
            //更具name查询用户
           List<User> users= userService.selectUserByName(user.getName());
           if(users.get(0).getPassword().equals(user.getPassword())){
               //生成token
               String token = MyTokenUtil.createToken(user);
               return token;
           }

        }else {
            return "error 500";
        }
        return "error 500";
    }


    @GetMapping("/selectUser/list")
    public List<User> selectUserList(){
        List<User> users = userService.selectUserList();
        return users;
    }

    @PostMapping("/selectUserPage")
    public List<User> selectStuPage(@RequestBody PageInfo pageInfo){
        int pageIndex = (pageInfo.getPageNo()-1)*pageInfo.getPageSize();
        List<User> userPage = userService.selectUserPage(pageIndex,pageInfo.getPageSize());
        return userPage;
    }
    @GetMapping("/selectUserById/{id}")
    public User selectUserById(@PathVariable("id") Integer id){

        return  userService.selectUserById(id);

    }

//后面改name唯一了，懒的改了
    @GetMapping("/selectUserByName/{name}")
    public List<User> selectUserByName(@PathVariable("name") String name){

        return  userService.selectUserByName(name);

    }

    @PostMapping("/deleteUserByList")
    public int deleteUserByList(@RequestBody Integer[] ids){


        return userService.deleteUserByList(ids);
    }

    @PostMapping("/addUser")
    public int addUser(@RequestBody User user){

//        String token = MyTokenUtil.createToken(user);
//加密密码
        //盐值
        String salt = "mona come up !";
        String md5Password = DigestUtils.md5DigestAsHex((user.getPassword()+salt).getBytes());
        user.setPassword(md5Password);
//        user.setPassword(token.split("\\.")[2]);
        return userService.addUser(user);
//        return users;
    }
    @DeleteMapping("/deleteUserById/{id}")
    public int deleteUserById(@PathVariable("id") Integer id){
       return userService.deleteUserById(id);
    }
    @PutMapping("/updateUser")
    public int updateUser(@RequestBody User user){
        //加密密码
        //盐值
        //更新时加密
        String salt = "mona come up !";
        String md5Password = DigestUtils.md5DigestAsHex((user.getPassword()+salt).getBytes());
        user.setPassword(md5Password);
       return userService.updateUser(user);
    }




}
