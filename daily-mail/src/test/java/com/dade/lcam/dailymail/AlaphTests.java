package com.dade.lcam.dailymail;

import com.dade.lcam.dailymail.crawel.NewsEntity;
import com.dade.lcam.dailymail.crawel.dal.NewsDAO;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testInsertNews(){
        List<NewsEntity> news = Lists.newArrayList();
        NewsEntity enetity = NewsEntity.builder().link("testLink").title("testTile").build();
        news.add(enetity);

        newsDAO.insertIntoNews(news);
    }

}
