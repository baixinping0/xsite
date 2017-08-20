package com.bxp.xsite.service;

import com.bxp.xsite.entity.ArticleModel;
import com.bxp.xsite.entity.MenuModel;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface ArticleEbi {
    public List<ArticleModel> list();

    public ArticleModel get(Serializable id);

    public String insert(ArticleModel entity);

    public String update(ArticleModel entity);

    public void deleteById(Serializable id);

    public List<ArticleModel> listTitleByLabel(String labelUuid);

    public String insert(Map<String, String> map);

    public List<MenuModel> listLabelMenu();

    public List<MenuModel> listArticleLookMenu(String root);

    public List<MenuModel> listArticleUpdateMenu(String root);
}
