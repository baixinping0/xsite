package com.bxp.xsite.service.impl;

import com.bxp.xsite.common.utils.num.UUIDUtils;
import com.bxp.xsite.dao.ArticleDao;
import com.bxp.xsite.dao.LabelDao;
import com.bxp.xsite.entity.ArticleModel;
import com.bxp.xsite.entity.LabelModel;
import com.bxp.xsite.entity.MenuModel;
import com.bxp.xsite.service.ArticleEbi;
import com.bxp.xsite.service.LabelEbi;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArticleEbo implements ArticleEbi{

    @Resource
    ArticleDao articleDao;
    @Resource
    LabelDao labelDao;

    @Override
    public List<ArticleModel> list() {
        return articleDao.list();
    }

    @Override
    public ArticleModel get(Serializable id) {
        return articleDao.get(id);
    }

    @Override
    public String insert(ArticleModel articleModel) {
        String uuid = UUIDUtils.getUuid();
        articleModel.setUuid(uuid);
        articleModel.setCreateTime(System.currentTimeMillis());
        articleModel.setUpdateTime(System.currentTimeMillis());
        articleDao.insert(articleModel);
        return uuid;
    }

    @Override
    public String update(ArticleModel articleModel) {
        articleModel.setUpdateTime(System.currentTimeMillis());
        articleDao.update(articleModel);
        return articleModel.getUuid();
    }

    @Override
    public void deleteById(Serializable id) {
        articleDao.deleteById(id);
    }

    @Override
    public List<ArticleModel> listTitleByLabel(String labelUuid) {
        return articleDao.listTitleByLabel(labelUuid);
    }

    @Override
    public String insert(Map<String, String> map) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setUuid(map.get("uuid"));
        articleModel.setTitle(map.get("title"));
        articleModel.setContent(map.get("content"));
        articleModel.setLabelUuid(map.get("label"));
        return StringUtils.isEmpty(articleModel.getUuid()) ? insert(articleModel)
                : update(articleModel);
    }

    @Override
    public List<MenuModel> listLabelMenu() {
        List<MenuModel> menuList = new ArrayList<>();
        List<LabelModel> labelList = labelDao.list();
        for (LabelModel label : labelList){
            MenuModel menu = new MenuModel();
            menu.setId(label.getUuid());
            menu.setText(label.getName());
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    public List<MenuModel> listArticleLookMenu(String root) {
        List<MenuModel> menuList = new ArrayList<>();
        List<ArticleModel> articleList = listTitleByLabel(root);
        for (ArticleModel article : articleList){
            MenuModel menu = new MenuModel();
            menu.setUrlLookText(article.getTitle(),article.getUuid());
            menu.setClasses("file");
            menu.setHasChildren(false);
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    public List<MenuModel> listArticleUpdateMenu(String root) {
        List<MenuModel> menuList = new ArrayList<>();
        List<ArticleModel> articleList = listTitleByLabel(root);
        for (ArticleModel article : articleList){
            MenuModel menu = new MenuModel();
            menu.setUrlUpdateText(article.getTitle(),article.getUuid());
            menu.setClasses("file");
            menu.setHasChildren(false);
            menuList.add(menu);
        }
        return menuList;
    }
}
