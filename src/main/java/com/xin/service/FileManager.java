package com.xin.service;

/*
@DATE 2021/10/23 17:11
@PACKAGE_NAME com.xin.service
@USER A
*/

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileManager {

    /**
     *
     * @param file 要上传的文件
     * @return
     * @throws IOException
     */

    String uploadFile(MultipartFile file) throws IOException;

    /**
     *
     * @param flag 根据唯一标识去文件找要下载的对象
     * @param response
     */
    String downloadFile(@PathVariable String flag, HttpServletResponse response);

}
