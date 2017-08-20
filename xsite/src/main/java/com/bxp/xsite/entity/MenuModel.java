package com.bxp.xsite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuModel {
    private String id;
    private String text;
    private String classes = "folder";
    private boolean hasChildren = true;

    public void setUrlUpdateText(String text,  String uuid){
        this.text = "<a class='article' target='_blank'  href='/xsite/article/toUpdate?uuid="+uuid+"' target='blog' >"+text+"</a>";
    }
    public void setUrlLookText(String text,  String uuid){
        this.text = "<a class='article'  href='/xsite/article/toLook?uuid="+uuid+"' target='blog' >"+text+"</a>";
    }
}
