package com.xin.service;

/*
@data 2021/9/19 20:20
@PACKAGE_NAME com.xin.service
*/






import cn.hutool.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

import java.util.Map;

public class TianqiCheck {
    public static void main(String[] args) {


        String host = "https://ali-weather.showapi.com";
        String path = "/ip-to-weather";
        String method = "GET";
        String appcode = "242e593357a943438eb70e13003fe211";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
//        querys.put("ip", "223.5.5.5");
        querys.put("area","莱芜");
        querys.put("need3HourForcast", "0");
        querys.put("needAlarm", "0");
        querys.put("needHourData", "0");
        querys.put("needIndex", "1");
        querys.put("needMoreDay", "1");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println("结果1"+response.toString());
            //获取response的body
            System.out.println("结果2"+EntityUtils.toString(response.getEntity()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
