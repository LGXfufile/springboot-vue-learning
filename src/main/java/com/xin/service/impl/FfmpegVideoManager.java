package com.xin.service.impl;

/*
@DATE 2021/10/22 8:19
@PACKAGE_NAME com.xin.service.impl
@USER A
*/

import org.springframework.stereotype.Service;

import java.io.IOException;


public interface FfmpegVideoManager {
    /**
     * 为视频添加文字
     * @param INPUTPATH 视频输入路径
     */
    void addTextForVideo(String INPUTPATH) throws IOException;

    /**
     * 将一个视频剪辑成n份
     * @param INPUTPATH 输入视频
     * @param OUTPATH 输出视频
     * @param numOfVideo 剪辑成n份
     */
    void cutForVideo(String INPUTPATH, String OUTPATH, int numOfVideo);

    /**
     * 将视频转化为音频
     * @param INPUTPATH 视频输入路径
     *
     */
    String videoToMusic(String INPUTPATH ) throws IOException;

    String videoToMp3(String INPUTPATH ) throws IOException;

}
