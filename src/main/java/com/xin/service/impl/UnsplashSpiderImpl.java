package com.xin.service.impl;

/*
@data 2021/9/14 23:09
@PACKAGE_NAME com.xin.service.impl
*/

import com.xin.service.UnsplashSpider;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UnsplashSpiderImpl implements UnsplashSpider {


    @Override
    public void spider()  {

    }

    @Test
    public void test() throws IOException {
        String url = "https://unsplash.com/";

//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        //发起get请求
//        HttpPost httpPost = new HttpPost(url);
//
//        //模拟回车的操作；
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        System.out.println(response);
//
//        //解析响应，拿到数据；
//        if (response.getStatusLine().getStatusCode()==200){
//            HttpEntity entity = response.getEntity();
//            String content = EntityUtils.toString(entity, "utf8");
//            System.out.println(content);
//        }
        Connection connect = Jsoup.connect(url);
        Connection conheader = connect.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36");
        Document document = conheader.get();
        System.out.println(document);

        Elements g5Lu__2gKr0 = document.getElementsByClass("_1g5Lu _2gKr0");
        for (Element element : g5Lu__2gKr0) {
            String attr = element.getElementsByTag("img").attr("src");
            System.out.println(attr);

        }
    }
}
