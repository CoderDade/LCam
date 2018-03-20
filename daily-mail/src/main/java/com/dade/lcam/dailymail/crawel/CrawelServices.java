package com.dade.lcam.dailymail.crawel;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@PropertySource("classpath:person.properties")
public class CrawelServices {

    @Value("${person.url}")
    private String url;

    public String crawelInfo() {
        try {
            String result = crawelByUrl(url);
            return result;
        }catch (Exception e){
            return null;
        }
    }

    private String crawelByUrl(String url) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
        httpget.setHeader("Content-Type", "text/html;charset=utf-8");
        CloseableHttpResponse response = httpClient.execute(httpget);
        HttpEntity httpEntity= response.getEntity();
        return EntityUtils.toString(httpEntity);
    }

}
