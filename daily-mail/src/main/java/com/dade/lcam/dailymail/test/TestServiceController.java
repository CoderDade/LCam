package com.dade.lcam.dailymail.test;

import com.dade.lcam.dailymail.test.crawel.TestCrawelService;
import com.dade.lcam.dailymail.test.mail.TestMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestServiceController {

//    @RequestMapping("mail")
//    public String testMail(){
//        try {
//            TestMailService.testSendEmail();
//        } catch (Exception e) {
//            return "fail to send mail";
//        }
//        return "success!";
//    }

    @Autowired
    TestMailService testMailService;

    @RequestMapping("send/context")
    public String testSendContext(){
        try {
            String subject = "Daily News";
//            String content = TestCrawelService.crawelInfo();
            testMailService.testSendEmail(subject, "test");
        } catch (Exception e) {
            return "fail to send mail";
        }
        return "success!";
    }

    @RequestMapping("crawel/context")
    public String testCrawelContext(){
        try {
            return TestCrawelService.crawelInfo();
        } catch (Exception e) {
            return "fail to crawel Context.";
        }
    }

    @RequestMapping("crawel/special")
    public String testCrawelSpecial(){
        try {
            return TestCrawelService.crawelSpecial();
        } catch (Exception e) {
            return "fail to crawel Special.";
        }
    }



}
