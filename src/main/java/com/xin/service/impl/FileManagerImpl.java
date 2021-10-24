package com.xin.service.impl;

/*
@DATE 2021/10/23 17:58
@PACKAGE_NAME com.xin.service.impl
@USER A
*/

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.xin.service.FileManager;
import com.xin.service.FindFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FileManagerImpl implements FileManager {


    @Value("${server.port}")
    private String serverPort;

    @Value("${file.ip}")
    private  String ip;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        //获取files绝对路径；
        String flag = IdUtil.fastSimpleUUID();//定义唯一标识；
        String rootFilePath = System.getProperty("user.dir")+"/src/main/resources/files/"+flag+originalFilename;
        System.out.println("文件上传成功，路径为："+rootFilePath);
        //将从前端获取到的对象，写入到指定地址；
        FileUtil.writeBytes(file.getBytes(),rootFilePath);
//        ="http://localhost"
        String fileUrl = "http://"+ip + ":" + serverPort +"/"+ flag;
        return fileUrl;
    }

    @Override
    public String downloadFile(String flag, HttpServletResponse response) {

        OutputStream os; //新建输出流对象；
        String basePath = System.getProperty("user.dir")+"/src/main/resources/files/";//定义文件上传的根路径
//        获取到所有的文件名称
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if(StrUtil.isNotEmpty(fileName)){
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                String targetFilePath = basePath + fileName; //targetFilePath,目标文件
                byte[] bytes = FileUtil.readBytes(targetFilePath);//通过文件路径获取文件字节流
                os= response.getOutputStream();//通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
                System.out.println("文件下载成功");
                return targetFilePath;
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
        return "文件下载失败";

    }

}
