package com.dade.lcam.dailymail.test.crawel;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

public class TestCrawelService {

    public static String crawelInfo() {
        try {
            CloseableHttpClient httpClient= HttpClients.createDefault();
            HttpGet httpget = new HttpGet("https://baijia.baidu.com/");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
            httpget.setHeader("Content-Type", "text/html;charset=utf-8");
            CloseableHttpResponse response = httpClient.execute(httpget);
            HttpEntity httpEntity= response.getEntity();
            String strResult= EntityUtils.toString(httpEntity);
            return strResult;

        }catch (Exception e){
            return null;
        }
    }

    public static String crawelSpecial() {
        try {
            CloseableHttpClient httpClient= HttpClients.createDefault();
            HttpGet httpget = new HttpGet("https://baijia.baidu.com/s?id=1594932969136601573&wfr=pc&fr=idx_top");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
            httpget.setHeader("Content-Type", "text/html;charset=utf-8");
            CloseableHttpResponse response = httpClient.execute(httpget);
            HttpEntity httpEntity= response.getEntity();
            String strResult= EntityUtils.toString(httpEntity);
            return strResult;

        }catch (Exception e){
            return null;
        }
    }

}
