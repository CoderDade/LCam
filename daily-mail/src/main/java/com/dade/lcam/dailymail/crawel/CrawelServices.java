package com.dade.lcam.dailymail.crawel;

import com.dade.lcam.dailymail.crawel.dal.NewsDAO;
import com.dade.lcam.dailymail.log.LogUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@PropertySource("classpath:person.properties")
public class CrawelServices {

    @Value("${person.url}")
    private String url;

    @Autowired
    NewsDAO newsDAO;

    public void crawelInfo() {
        try {
            String result = crawelByUrl(url);
            if (StringUtils.isBlank(result)){
                return;
            }
            List<NewsEntity> infos = Lists.newArrayList();
            Document parse = Jsoup.parse(result);
            Elements elements = parse.getElementsByTag("a");
            Iterator<Element> iterator = elements.iterator();
            while (iterator.hasNext()){
                Element next = iterator.next();
                NewsEntity news = getNewsByString(next.attr("href"), next.text());
                if (news != null){
                    infos.add(news);
                }
            }
            newsDAO.insertIntoNews(infos);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private NewsEntity getNewsByString(String link, String title){
        if (StringUtils.isBlank(link) || StringUtils.isBlank(title)){
            return null;
        }

        if (title.equals("查看详情 >")){
            return null;
        }

        // todo those could be detach
        // this means that i want article only
        if (link.startsWith("/s")){
            return NewsEntity.builder().link(url+link).title(title).build();
        }
        return null;
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
