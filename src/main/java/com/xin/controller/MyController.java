package com.xin.controller;

/*
@DATE 2021/10/23 0:02
@PACKAGE_NAME com.xin.controller
@USER A
*/

import cn.hutool.core.util.StrUtil;
import com.sun.mail.imap.protocol.FLAGS;
import com.xin.service.EmailAction;
import com.xin.service.FileManager;
import com.xin.service.FindFiles;
import com.xin.service.impl.FfmpegVideoManager;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
public class MyController {

    @Autowired
    FfmpegVideoManager ffmpegVideoManager;
    @Autowired
    FileManager fileManager;
    @Autowired
    EmailAction emailAction;
    @Autowired
    FindFiles findFiles;

    private String INPUTPATH = "E:\\GoogleDownload\\pornhub\\6.mp4";

    //上传接口
    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {

        String fileUrl = fileManager.uploadFile(file);
        return fileUrl;
    }
    /**
     *下载接口
     * @param flag 根据唯一标识去文件找要下载的对象；
     * @param response
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response){
        String imgSrcPath = fileManager.downloadFile(flag, response);
        String receiveEmailAddress = "401040990@qq.com";
        String emailContent = "文件唯一标识: "+flag;
        emailAction.sendHtmlWithImg(emailContent,receiveEmailAddress,imgSrcPath);
    }
    @GetMapping("/{flag}/videoToMusic")
    public String videoToMusic(@PathVariable String flag) throws IOException {

        String absolutePath = findFiles.getAbsolutePath(flag);
        if(StrUtil.isNotEmpty(absolutePath)){
            String musicPath = ffmpegVideoManager.videoToMusic(absolutePath);
            return "音频已生成，地址："+musicPath;
        }
        System.out.println("absolutePath is null");
        return "转换失败";
    }
}
