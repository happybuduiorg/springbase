package com.happybudui.springbase.controller;

import com.happybudui.springbase.entity.UserExternEntity;
import com.happybudui.springbase.mapper.UserMapper;
import com.happybudui.springbase.wrapper.ResponseResult;
import com.happybudui.springbase.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.happybudui.springbase.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

//    // 根据id获取用户数据的json
//    @RequestMapping(value = "getUserId", method = RequestMethod.GET)
//    ResponseResult<UserMapper> getUserInfoJsonById(@RequestParam(name = "userid") String userId){
//        return adminService.getUserInfoJsonById(userId);
//    }

    // 插入用户数据
    @RequestMapping(value = "insertuser",method = RequestMethod.POST)
    ResponseResult<Integer>insertUser(@RequestParam(name="usermail")String usermail,@RequestParam(name="username")String username,@RequestParam(name="userpassword")String userpassword) {
        return adminService.insertUser(usermail,username,userpassword);
    }

    // 删除用户数据
    @RequestMapping(value = "deleteuser", method = RequestMethod.GET)
    ResponseResult<Integer> deleteUserById(@RequestParam(name = "username") String username){
        return adminService.deleteUserById(username);
    }

    // 更新用户数据
    @RequestMapping(value = "updateuser", method = RequestMethod.POST)
    ResponseResult<Integer>updateUserById(@RequestParam(name="usermail")String usermail,@RequestParam(name = "username") String username){
        return adminService.updateUserById(usermail,username);
    }

    //冻结用户
    @RequestMapping(value = "frozenuseraccount",method = RequestMethod.POST)
    ResponseResult<Integer> frozenUserById(@RequestParam(name = "userstatus") String userstatus){
        return adminService.frozenUserById(userstatus);
    }

    //上传文件接口
    @RequestMapping("upload")
    public ResponseResult<Integer> upLoad(HttpServletRequest request, MultipartFile file) {
        String upLoadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";

        File dir = new File(upLoadDir);
        if (dir.exists() == false) {
            dir.mkdir();
        }

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String saveName = UUID.randomUUID() + suffix;

        File fileToSave = new File(upLoadDir + saveName);

        try {
            file.transferTo(fileToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.success();
    }

}