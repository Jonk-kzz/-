package com.cube.chat;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest(classes = ChatApplication.class)
@RunWith(SpringRunner.class)
public class ChatApplicationTest {


    @Test
    public void testFastDfs(){
        try{
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
          /*  ClientGlobal.setG_charset();*/
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);
            //创建客户端
            TrackerClient tc=new TrackerClient();

            //连接tracker Server
            TrackerServer ts = tc.getConnection();
            if(ts==null){
                System.out.println("getConnection return null");
                return;
            }
            //获取一个storage server
            StorageServer ss = tc.getStoreStorage(ts);
            if (ss == null) {
                System.out.println("getStoreStorage return null");
            }
            StorageClient1 sc1 = new StorageClient1(ts, ss);

            NameValuePair[] meta_list = null;
            String item="C:\\Users\\Jonk\\Desktop\\timg.jpg";
            String fileId;
            fileId=sc1.upload_file1(item,"jpg",meta_list);

            System.out.println("Upload local file " + item + " ok, fileid=" + fileId);

        }catch (Exception e){
            e.printStackTrace();
        }



    }
    //查询文件
    @Test
    public void testQueryFile(){
        try{
            ClientGlobal.initByProperties("config/fastdfs-client.properties");

            TrackerClient tracker =new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();

            StorageServer storageServer=null;

            StorageClient1 sc=new StorageClient1(trackerServer,storageServer);

            FileInfo fileInfo =  sc.query_file_info("group1", "M00/00/00/rBCfEFyNQu2ADKR9AAQ-gdAh6Hc100.jpg");

            System.out.println(fileInfo);
            System.out.println(fileInfo.toString());
        }catch(Exception e){

        }
    }

    @Test
    public void testDownloadFile(){
        try {
            ClientGlobal.initByProperties("config/fastdfs-client.properties");

            TrackerClient trackerClient=new TrackerClient();

            TrackerServer trackerServer = trackerClient.getConnection();

            StorageServer storageServer=null;

            StorageClient1 storageClient1=new StorageClient1(trackerServer,storageServer);

            byte[] result = storageClient1.download_file1("group1/M00/00/00/rBCfEFyNQu2ADKR9AAQ-gdAh6Hc100.jpg");

            File file=new File("d:/jjy.jpg");
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(result);
            fileOutputStream.close();

        }catch (Exception e){

        }


    }
}
