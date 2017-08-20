package com.bxp.xsite.controller;

import com.alibaba.fastjson.JSON;
import com.bxp.xsite.common.utils.str.ParseRequestBody;
import com.bxp.xsite.entity.ArticleModel;
import com.bxp.xsite.entity.LabelModel;
import com.bxp.xsite.entity.MenuModel;
import com.bxp.xsite.service.ArticleEbi;
import com.bxp.xsite.service.LabelEbi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ArticleController {
    @Resource
    ArticleEbi articleEbi;
    @Resource
    LabelEbi labelEbi;


    @RequestMapping("/")
    public String lookList(){
        return "article/lookList";
    }
    @RequestMapping("/admin")
    public String updateList(){
        return "article/updateList";
    }

    @RequestMapping("/article/lookMenu")
    public String lookMenu(){
        return "menu/lookMenu";
    }
    @RequestMapping("/article/updateMenu")
    public String updateMenu(){
        return "menu/updateMenu";
    }

    @RequestMapping("/article/lookMenu/list")
    public void  lookMenuList(HttpServletResponse response, String root) throws IOException {
        List<MenuModel> menus = new ArrayList<>();
        if ("source".equals(root))
            menus = articleEbi.listLabelMenu();
        else
            menus = articleEbi.listArticleLookMenu(root);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(menus));
        writer.flush();
    }

    @RequestMapping("/article/updateMenu/list")
    public void  updateMenuList(HttpServletResponse response, String root) throws IOException {
        List<MenuModel> menus = new ArrayList<>();
        if ("source".equals(root))
            menus = articleEbi.listLabelMenu();
        else
            menus = articleEbi.listArticleUpdateMenu(root);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(menus));
        writer.flush();
    }

    /**
     * 跳转到创建文件的页面
     * @param model
     * @return
     */
    @RequestMapping("/article/toInput")
    public String input(Model model){
        List<LabelModel> labelLsist = labelEbi.list();
        model.addAttribute("labelList", labelLsist);

        ArticleModel article = new ArticleModel();
        model.addAttribute("article", article);
        return "article/input";
    }

    /**
     * 执行文章的插入操作
     * @param data
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/article/insert", method = RequestMethod.POST)//
    public void insert(@RequestBody String data, HttpServletResponse response) throws IOException {
        Map<String, String> map = ParseRequestBody.parse(data);
        String uuid = articleEbi.insert(map);
        response.getWriter().write(uuid);
    }

    @RequestMapping("/article/toLook")
    public String lookArticleById(String uuid, Model model){
        ArticleModel article = articleEbi.get(uuid);
        System.out.println(article);
        model.addAttribute("article", article);
        return "article/content";
    }

    /**
     * 跳转到文章更新页面
     * @param uuid
     * @param model
     * @return
     */
    @RequestMapping("/article/toUpdate")
    public String toUpdate(String uuid, Model model){
        ArticleModel article = articleEbi.get(uuid);
        model.addAttribute("article", article);

        LabelModel defaultLabel = labelEbi.get(article.getLabelUuid());
        model.addAttribute("defaultLabel", defaultLabel);

        List<LabelModel> labelList = labelEbi.list();
        model.addAttribute("labelList", labelList);
        return "article/input";
    }

}
