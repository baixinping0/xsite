package com.bxp.xsite.service;

import com.bxp.xsite.entity.LabelModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface LabelEbi {
    public List<LabelModel> list();

    public LabelModel get(Serializable id);

    public String insert(LabelModel entity);

    public void update(LabelModel entity);

    public void deleteById(Serializable id);
    //批量删除
    public void delete(Serializable[] ids);

    public String insert(Map<String, String> map);
}
