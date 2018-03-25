package com.dade.lcam.dailymail.util;

import com.dade.lcam.dailymail.crawel.NewsEntity;

import java.util.List;

public class MailUtil {

    public static String getHtmlByNews(List<NewsEntity> news){
        StringBuilder sb = new StringBuilder();
        sb.append("<div>");
        sb.append("<br />");
        news.forEach(info->{
            sb.append("<a href=\"").append(info.getLink())
                    .append("\" target=\"_blank\">").append(info.getTitle()).append("</a>").append("<br />");
        });
        sb.append("</div>");
        return sb.toString();
    }

}
