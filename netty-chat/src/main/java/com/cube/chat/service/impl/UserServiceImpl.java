package com.cube.chat.service.impl;

import com.cube.chat.dao.FriendReqMapper;
import com.cube.chat.dao.UserMapper;
import com.cube.chat.exception.ExceptionCast;
import com.cube.chat.pojo.User;
import com.cube.chat.pojo.UserExample;
import com.cube.chat.pojo.vo.CommonCode;
import com.cube.chat.pojo.vo.ResponseResult;
import com.cube.chat.service.UserService;
import com.cube.chat.utils.IdWorker;
import com.cube.chat.utils.QRCodeUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${chat.fastdfs.tracker_servers}")
    String tracker_servers;
    @Value("${chat.fastdfs.connect_timeout_in_seconds}")
    int connect_timeout_in_seconds;
    @Value("${chat.fastdfs.network_timeout_in_seconds}")
    int network_timeout_in_seconds;
    @Value("${chat.fastdfs.charset}")
    String charset;
    @Autowired
    private Environment env;
    @Autowired
    private QRCodeUtils qrCodeUtils;
    @Autowired
    UserMapper userMapper;

    @Autowired
    FriendReqMapper friendReqMapper;

    @Autowired
    IdWorker idWorker;
    @Override
    public User login(String username, String password) {
        if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)){

            User user = userMapper.findByUsername(username);
            if(user !=null){
                //MD5加密认证
                String encodingPassword = DigestUtils.md5DigestAsHex(password.getBytes());
                if(encodingPassword.equals(user.getPassword())){
                    User reUser = new User();
                    BeanUtils.copyProperties(user,reUser);
                    return user;
                }

            }
        }

        return null;
    }

    @Override
    public ResponseResult registry(User user) {
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());

        List<User> listUser = userMapper.selectByExample(example);
        if(listUser!= null && listUser.size()<0){
            return new ResponseResult(CommonCode.USER_EXIST);
        }else{
            user.setId(idWorker.nextId());
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setPicSmall("");
            user.setPicNormal("");
            user.setUsername(user.getUsername());

            user.setCreatetime(new Date());
            //生成二维码,并且将二维码的路径保存到数据库中
            //要生产二维码中的字符串
            String qrcodeStr="hichat://"+user.getUsername();
            //获取一个临时目录,用来保持临时的二维码图片
            String tempDir=env.getProperty("hcat.tmpdir");
            String qrCodeFilePath = tempDir + user.getUsername() + ".png";
            qrCodeUtils.createQRCode(qrCodeFilePath,qrcodeStr);
            //将临时保存的二维码上传到FastDFS
            try {
                File file = new File(qrCodeFilePath);
                InputStream in=new FileInputStream(file);
                byte[] bytes = IOUtils.toByteArray(in);
                //保存二维码
                String qRCode = fdfs_upload(bytes, file.getName());
                user.setQrcode(qRCode);
            } catch (Exception e) {
                e.printStackTrace();
            }



            userMapper.insertSelective(user);
        }
        return new ResponseResult(CommonCode.REGISTER_SUCCESS);
    }

    @Override
    public ResponseResult upload(MultipartFile file, String userId) {
        //用户信息
        User user = userMapper.selectByPrimaryKey(userId);
        //文件id
        String fileId = null;
        try {
            fileId = fdfs_upload(file.getBytes(),file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setId(userId);
        user.setPicSmall(fileId);
        user.setPicNormal(fileId);

        userMapper.updateByPrimaryKeySelective(user);
        return new ResponseResult(CommonCode.SUCCESS,user);
    }

    //更改头像
    @Override
    public ResponseResult updatePic(String userId, String fileId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPicSmall(fileId);
        user.setPicNormal(fileId);
        return new ResponseResult(CommonCode.SUCCESS);
    }
    //修改名称
    @Override
    public ResponseResult updateNickname(String id, String nickname) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setNickname(nickname);
        userMapper.updateByPrimaryKeySelective(user);

        return new ResponseResult(CommonCode.SUCCESS);
    }
    //个人信息
    @Override
    public ResponseResult findById(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return new ResponseResult(CommonCode.SUCCESS,user);
    }

    @Override
    public ResponseResult findByUsername(String username) {
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        return new ResponseResult(CommonCode.SUCCESS,users);
    }


    //加载fdfs的配置
    private void initFdfsConfig(){
        try{
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
        }catch (Exception e){
            e.printStackTrace();
            //初始化文件系统出错
            ExceptionCast.cast(new ResponseResult(CommonCode.INIT_FILE_FALL));
        }
    }

    //上传文件到fdfs, 返回文件id
   public String fdfs_upload(byte[] bytes,String fileName){


        try{
            //加载fdfs的配置
            initFdfsConfig();
            //创建tracker client
            TrackerClient trackerClient=new TrackerClient();
            //获取trackerServer
            TrackerServer trackerServer=trackerClient.getConnection();
            //获取storage
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            //创建storage client
            StorageClient1 storageClient1=new StorageClient1(trackerServer,storeStorage);
            //上传文件
            //文件字节
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
            //文件id
            String fileId=storageClient1.upload_file1(bytes,extName,null);
            return fileId;
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
   }



}
