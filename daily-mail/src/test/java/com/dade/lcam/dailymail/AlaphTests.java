package com.dade.lcam.dailymail;

import com.dade.lcam.dailymail.crawel.NewsEntity;
import com.dade.lcam.dailymail.crawel.dal.NewsDAO;
import com.dade.lcam.dailymail.mail.MailServices;
import com.dade.lcam.dailymail.util.MailUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlaphTests {

    @Autowired
    NewsDAO newsDAO;

    @Test
    public void testMyBatisRun(){
        List<NewsEntity> news = newsDAO.queryAllNews();
        news.forEach(System.out::println);
    }

    @Test
    public void testMailUtil(){
        List<NewsEntity> news = newsDAO.queryAllNews();
        String htmlByNews = MailUtil.getHtmlByNews(news);
        System.out.println(htmlByNews);
    }

    @Test
    public void testInsertNews(){
        List<NewsEntity> news = Lists.newArrayList();
        NewsEntity enetity = NewsEntity.builder().link("testLink").title("testTile").build();
        news.add(enetity);

        newsDAO.insertIntoNews(news);
    }

    @Autowired
    MailServices mailServices;

    @Test
    public void testSendBTLMail() throws UnsupportedEncodingException, MessagingException {
        String html = "<div><br /><a href=\"https://baijia.baidu.com//s?id=1595796777545616645\" target=\"_blank\">今日头条能“无限发文”了！自媒体可以体面地去死了</a><br /><a href=\"https://baijia.baidu.com//s?id=1595778627338644193\" target=\"_blank\">广电新规致影视剧发行受阻，B站抖音快手也要凉凉？这是最全指南</a><br /><a href=\"https://baijia.baidu.com//s?id=1595778707942448877\" target=\"_blank\">“东南亚支付宝”ICO陷罗生门，95后王凯歆走上维权前线？</a><br /><a href=\"https://baijia.baidu.com//s?id=1595712676470736380\" target=\"_blank\">那边B站准备上市 这边总局又双叒叕出新规定了</a><br /><a href=\"https://baijia.baidu.com//s?id=1595863273985487493\" target=\"_blank\">滴滴“杀熟”既不真实也无必要，炒作时机却很巧合</a><br /><a href=\"https://baijia.baidu.com//s?id=1595773378431090202\" target=\"_blank\">美团估值能达到100亿吗？打车上线就喊IPO的醉翁之意</a><br /><a href=\"https://baijia.baidu.com//s?id=1595781931243981233\" target=\"_blank\">美团VS滴滴：决战上海滩</a><br /><a href=\"https://baijia.baidu.com//s?id=1595789310303839224\" target=\"_blank\">短视频战火不熄 今天聊聊复活的微视与尴尬的快手</a><br /><a href=\"https://baijia.baidu.com//s?id=1595721322517631576\" target=\"_blank\">继“苹果税”的大数据杀熟！网友爆料称苹果用户打车比安卓贵</a><br /><a href=\"https://baijia.baidu.com//s?id=1595714465091654827\" target=\"_blank\">传美团估值600亿美元，将在港股上市？</a><br /></div>";
        mailServices.sendHtmlMail(html);
    }

}
