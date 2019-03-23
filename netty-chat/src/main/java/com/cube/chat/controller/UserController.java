package com.cube.chat.controller;

import com.cube.chat.pojo.User;
import com.cube.chat.pojo.vo.CommonCode;
import com.cube.chat.pojo.vo.ResponseResult;
import com.cube.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登陆
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){

        try{
            User _user = userService.login(user.getUsername(), user.getPassword());
            if(_user!=null){
                return new ResponseResult(CommonCode.SUCCESS,_user);
            }else{
                return new ResponseResult(CommonCode.LOGIN_FALL);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseResult(CommonCode.FAIL);
        }


    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public ResponseResult registry(@RequestBody User user){
        return userService.registry(user);
    }


    /**
     * 上传头像
     * @param file
     * @param userId
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file,String userId){
       return userService.upload(file,userId);
    }

    /**
     * 更新头像
     * @param userId
     * @param fileId
     * @return
     */
    @PutMapping("/updatePic")
    public ResponseResult updatePic(String userId,String fileId){
        return userService.updatePic(userId,fileId);
    }

    /**
     * 更改名称
     * @param user
     * @return
     */
    @PutMapping("/updateNickname")
    public ResponseResult updateNickname(@RequestBody User user){
        return userService.updateNickname(user.getId(),user.getNickname());
    }

    /**
     * 重新加载用户信息
     * @param userId
     * @return
     */
    @GetMapping("/findById/{userId}")
    public ResponseResult findById(@PathVariable("userId") String userId){
        return userService.findById(userId);
    }

    @PostMapping("/findByUsername/{username}")
    public ResponseResult findByUidAndUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }






}
