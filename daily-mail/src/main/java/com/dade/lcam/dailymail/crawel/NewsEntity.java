package com.dade.lcam.dailymail.crawel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsEntity {

    String link;
    String title;
    Date createTime;

}
