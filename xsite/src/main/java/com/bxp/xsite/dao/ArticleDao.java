package com.bxp.xsite.dao;

import com.bxp.xsite.entity.ArticleModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleDao extends BaseDao<ArticleModel>{
    public List<ArticleModel> listTitleByLabel(String labelUuid);
}
