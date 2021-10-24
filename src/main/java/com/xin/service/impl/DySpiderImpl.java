package com.xin.service.impl;

/*
@data 2021/9/5 20:14
@PACKAGE_NAME com.xin.service.impl
*/

import cn.hutool.core.map.MapUtil;
import com.xin.service.DySpider;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;


import javax.servlet.http.HttpUtils;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

public class DySpiderImpl implements DySpider {
    @Test
    @Override
    public void spider() throws IOException {
        String url = "https://www.douyin.com/search/girl";

        //创建httpClient对象；
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发起get请求
        HttpGet httpGet = new HttpGet(url);

        //模拟回车的操作；
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //解析响应，拿到数据；
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }

    }
    @Test
    public void sss() throws IOException {
        URL url = new URL("https://i.snssdk.com/slardar/sdk_setting?bid=douyin_website");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("authority", "i.snssdk.com");
        httpConn.setRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36");
        httpConn.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
        httpConn.setRequestProperty("accept", "*/*");
        httpConn.setRequestProperty("origin", "https://www.douyin.com");
        httpConn.setRequestProperty("sec-fetch-site", "cross-site");
        httpConn.setRequestProperty("sec-fetch-mode", "cors");
        httpConn.setRequestProperty("sec-fetch-dest", "empty");
        httpConn.setRequestProperty("referer", "https://www.douyin.com/");
        httpConn.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
        httpConn.setRequestProperty("cookie", "MONITOR_WEB_ID=dabe7d83-fcff-4791-bb58-b4ce711f017e; msToken=j2kEs4mrbH-ect76abBybh1O0DcxL8IZu8FsdMqIrdBbZtn3zRr1Nr114_bF0YQjP5CrumjViUjxjccOZ_jscFlisfHN7XJ56qVZNOy3JicP");

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}
