package com.bxp.xsite.controller;

import com.bxp.xsite.common.utils.str.ParseRequestBody;
import com.bxp.xsite.service.LabelEbi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class LabelController {

    @Resource
    LabelEbi labelEbi;
    @RequestMapping(value = "/label/insert", method = RequestMethod.POST)//
    public void insert(@RequestBody String data, HttpServletResponse response) throws IOException {
        Map<String, String> map = ParseRequestBody.parse(data);
        String uuid = labelEbi.insert(map);
        response.getWriter().write(uuid);
    }
}
