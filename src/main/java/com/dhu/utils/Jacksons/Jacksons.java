package com.dhu.utils.Jacksons;

/**
 * Created by demerzel on 2018/4/12.
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Jacksons {
    private ObjectMapper objectMapper;

    public static Jacksons me() {
        return new Jacksons();
    }

    private Jacksons() {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }

    public Jacksons filter(String filterName, String... properties) {
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName,
                SimpleBeanPropertyFilter.serializeAllExcept(properties));
        objectMapper.setFilters(filterProvider);
        return this;
    }

    public Jacksons setDateFormate(DateFormat dateFormat) {
        objectMapper.setDateFormat(dateFormat);
        return this;
    }

    public <T> T json2Obj(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析json错误");
        }
    }

    public String readAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析对象错误");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> json2List(String json) {
        try {
            return objectMapper.readValue(json, List.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("解析json错误");
        }
    }
}
