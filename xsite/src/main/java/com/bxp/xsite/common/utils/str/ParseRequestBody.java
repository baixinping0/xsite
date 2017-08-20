package com.bxp.xsite.common.utils.str;

import org.springframework.util.StringUtils;

import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ParseRequestBody {
    public static Map<String, String> parse(String parameter) throws UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>();
        String[] datas = parameter.split("&");
        for (String data:datas){
            String[] d = data.split("=");
            String key = StringUtils.isEmpty(d[0]) ? "" : URLDecoder.decode(d[0], "UTF-8");
            if (d.length == 1){
                map.put(key, "");
                continue;
            }
            String value = StringUtils.isEmpty(d[1]) ? "" : URLDecoder.decode(d[1], "UTF-8");
            map.put(key, value);
        }
        return map;
    }
}
