package com.dade.lcam.dailymail.timer;

import com.dade.lcam.dailymail.crawel.CrawelServices;
import com.dade.lcam.dailymail.crawel.NewsEntity;
import com.dade.lcam.dailymail.crawel.dal.NewsDAO;
import com.dade.lcam.dailymail.mail.MailServices;
import com.dade.lcam.dailymail.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DailyMail {

    @Autowired
    MailServices mailServices;

    @Autowired
    NewsDAO newsDAO;

    @Autowired
    CrawelServices crawelServices;

    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void timerRate() {
        System.out.println(dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void crawelInfoSchduled(){
        crawelServices.crawelInfo();
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendMailScheduled() throws UnsupportedEncodingException, MessagingException {
        List<NewsEntity> news = newsDAO.queryAllNews();
        String htmlByNews = MailUtil.getHtmlByNews(news);
        mailServices.sendHtmlMail(htmlByNews);
    }
}
