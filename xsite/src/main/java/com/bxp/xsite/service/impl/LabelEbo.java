package com.bxp.xsite.service.impl;

import com.bxp.xsite.common.utils.num.UUIDUtils;
import com.bxp.xsite.dao.LabelDao;
import com.bxp.xsite.entity.LabelModel;
import com.bxp.xsite.service.LabelEbi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class LabelEbo implements LabelEbi {

    @Resource
    LabelDao labelDao;

    @Override
    public List<LabelModel> list() {
        return labelDao.list();
    }

    @Override
    public LabelModel get(Serializable id) {
        return labelDao.get(id);
    }

    @Override
    public String insert(LabelModel labelModel) {
        String uuid = UUIDUtils.getUuid();
        labelModel.setUuid(uuid);
        labelDao.insert(labelModel);
        return uuid;
    }

    @Override
    public void update(LabelModel labelModel) {
        labelDao.update(labelModel);
    }

    @Override
    public void deleteById(Serializable id) {
        labelDao.deleteById(id);
    }

    @Override
    public void delete(Serializable[] ids) {
        labelDao.delete(ids);
    }

    @Override
    public String insert(Map<String, String> map) {
        LabelModel labelModel = new LabelModel();
        labelModel.setName(map.get("name"));
        return insert(labelModel);
    }
}
