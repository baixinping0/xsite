package com.bxp.xsite.dao;

import com.bxp.xsite.entity.LabelModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LabelDao extends BaseDao<LabelModel>{
}
