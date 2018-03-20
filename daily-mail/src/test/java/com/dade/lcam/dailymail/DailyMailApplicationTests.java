package com.dade.lcam.dailymail;

import com.dade.lcam.dailymail.crawel.CrawelServices;
import com.dade.lcam.dailymail.mail.MailServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

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
        String result = crawelServices.crawelInfo();

        File file = new File("D:\\tmp\\html.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.print(result);
        pw.close();

    }


}
