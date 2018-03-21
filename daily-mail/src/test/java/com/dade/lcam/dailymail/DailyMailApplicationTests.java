package com.dade.lcam.dailymail;

import com.dade.lcam.dailymail.crawel.CrawelServices;
import com.dade.lcam.dailymail.log.LogUtil;
import com.dade.lcam.dailymail.mail.MailServices;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.util.HtmlUtils;

import javax.mail.MessagingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyMailApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
    MailServices mailServices;

	@Test
    public void testSendHtmlMail() throws UnsupportedEncodingException, MessagingException {
	    String html = "<a href='#'>This should be a link!</a>";
	    mailServices.sendHtmlMail(html);
    }


    @Autowired
    CrawelServices crawelServices;

    @Test
    public void testCrawelUrl() throws FileNotFoundException {
        crawelServices.crawelInfo();
    }

    @Test
    public void testLog(){
        LogUtil.plainFile("test");
    }

    @Test
    public void testAssert(){
        String str = null;
//        assert str != null;
        Assert.notNull(str, "this should be null");
    }

    @Test
    public void testHtml(){
        String s = HtmlUtils.htmlEscape("<div>hello world</div><p>&nbsp;</p>");
        System.out.println(s);
        String s2 = HtmlUtils.htmlUnescape(s);
        System.out.println(s2);
    }

    @Test
    public void testHtmlParse(){
        String html = "<p>this is p </p>";
        Document document = Jsoup.parseBodyFragment(html);
//        Element body = document.body();
//        System.out.println(body.val());
        Elements p = document.getElementsByTag("p");
        System.out.println(p.text());

    }


}
