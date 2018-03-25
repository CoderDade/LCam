package com.dade.lcam.dailymail.crawel.dal;

import com.dade.lcam.dailymail.crawel.NewsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface NewsDAO {

    @Select("<script>" +
            "select distinct link, title, createTime from qp_news order by createTime desc limit 10 " +
            "</script>")
    List<NewsEntity> queryAllNews();

    @Insert("<script>" +
            "insert ignore into qp_news(link, title, createTime) " +
            "<foreach collection='news' index='index' item='item' open='value ' " +
            "separator=',' close='' >" +
            "(#{item.link}, #{item.title}, now())" +
            "</foreach>" +
            "</script>")
    void insertIntoNews(@Param("news") List<NewsEntity> news);

}
