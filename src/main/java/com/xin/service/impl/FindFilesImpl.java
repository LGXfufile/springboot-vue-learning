package com.xin.service.impl;

/*
@DATE 2021/10/24 14:30
@PACKAGE_NAME com.xin.service.impl
@USER A
*/

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.xin.service.FindFiles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindFilesImpl implements FindFiles {
    @Override
    public String getAbsolutePath(String flag) {

        String basePath = System.getProperty("user.dir")+"/src/main/resources/files/";//定义文件上传的根路径
//        获取到所有的文件名称
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        if(StrUtil.isNotEmpty(fileName)){
            String targetFilePath = basePath + fileName; //targetFilePath,目标文件
            return targetFilePath;
        }
        return null;
    }
}
