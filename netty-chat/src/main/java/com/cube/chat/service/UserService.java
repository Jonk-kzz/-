package com.cube.chat.service;

import com.cube.chat.pojo.User;
import com.cube.chat.pojo.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User login(String username, String password);

    ResponseResult registry(User user);

    ResponseResult upload(MultipartFile file, String userId);

    ResponseResult updatePic(String userId,String url);

    ResponseResult updateNickname(String id, String nickname);

    ResponseResult findById(String userId);


    ResponseResult findByUsername(String username);
}
