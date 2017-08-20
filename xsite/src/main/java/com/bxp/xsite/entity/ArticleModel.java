package com.bxp.xsite.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ArticleModel {
    private String uuid;
    private String title;
    private String content;
    private String labelUuid;
    private Long createTime;
    private Long updateTime;
}
