package com.xin.service;

/*
@DATE 2021/10/22 8:23
@PACKAGE_NAME com.xin.service
@USER A
*/

import cn.hutool.core.lang.UUID;
import com.xin.service.impl.FfmpegVideoManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class FfmpegVideoManagerImpl implements FfmpegVideoManager {
//    private final static String INPUTPATH = "E:\\GoogleDownload\\science\\huoyuanjia.mp4";

    private final static String base = System.getProperty("user.dir");
    private final static String OUTPATH = base+"/src/main/resources/saveFiles/";
    private final static String FFMPEGPATH = base+"/src/main/resources/ffmpeg.exe  ";


    /**
     *
     * @param INPUTPATH 视频输入路径
     * @throws IOException
     */
    @Override
    public void addTextForVideo(String INPUTPATH) throws IOException {
        UUID uuid = UUID.randomUUID();//生成uuid，用来防重；
        String addTextForVideo=INPUTPATH+" -vf "+"\"drawtext=text='那些人生中一定要去一次的地方':x=(w-text_w)/2:y=(h-text_h)/2:enable='lte(t\\,5)':fontfile=msyh.ttc:fontsize=50:fontcolor=white\" "+" -c:a copy "+OUTPATH +uuid+ ".mp4";
        System.out.println(addTextForVideo);
        Runtime.getRuntime().exec(addTextForVideo);
    }

    /**
     *
     * @param INPUTPATH 输入视频
     * @param OUTPATH 输出视频
     * @param numOfVideo 剪辑成n份
     */
    @Override
    public void cutForVideo(String INPUTPATH, String OUTPATH, int numOfVideo) {

            int start = 0;
            for (int i = 0; i < numOfVideo; i++) {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    int videoLength = start + 150;
                    String cutVideo = FFMPEGPATH + "-ss  " + start + " -to " + videoLength + " -i   " + INPUTPATH + "   -c   copy " + OUTPATH +i+ ".mp4";
                    System.out.println(cutVideo);
                    runtime.exec(cutVideo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    /**
     *
     * @param INPUTPATH 视频输入路径
     * @throws IOException
     */
    @Override
    public String videoToMusic(String INPUTPATH) throws IOException {
        UUID uuid = UUID.randomUUID();
        Runtime runtime = Runtime.getRuntime();
        String musicPath = OUTPATH + uuid + ".m4a";
        String videoToMusic = FFMPEGPATH + "-i " + INPUTPATH + " -vn -codec copy " + musicPath;
        System.out.println("音频已生成，地址："+OUTPATH + uuid + ".m4a");
        System.out.println(videoToMusic);
        runtime.exec(videoToMusic);
        return musicPath;
    }

    @Override
    public String videoToMp3(String INPUTPATH) throws IOException {

        UUID uuid = UUID.randomUUID();
        Runtime runtime = Runtime.getRuntime();
        String musicPath = OUTPATH + uuid + ".mp3";
        String videoToMusic = FFMPEGPATH + "-i " + INPUTPATH + "  -f mp3 -vn " + musicPath;
        System.out.println("音频已生成，地址："+OUTPATH + uuid + ".m4a");
        System.out.println(videoToMusic);
        runtime.exec(videoToMusic);
        return musicPath;

    }
}
