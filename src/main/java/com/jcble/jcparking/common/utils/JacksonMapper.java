package com.jcble.jcparking.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonMapper {
    private static Logger log = LoggerFactory.getLogger(JacksonMapper.class);
    public static final ObjectMapper mapper = new ObjectMapper();

    private JacksonMapper() {
        // to enable standard indentation ("pretty-printing"):
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // to allow coercion of JSON empty String ("") to null Object value:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(MapperFeature.USE_ANNOTATIONS);
    }

    public static ObjectMapper getInstance() {
        return mapper;
    }

    public static String bean2Json(Object bean) throws RuntimeException {
        String json = null;
        try {
            json = mapper.writeValueAsString(bean);
        } catch (Exception ex) {
            log.error("", ex);
            throw new RuntimeException(ex);
        }
        return json;
    }

    public static String list2Json(List<?> list) throws RuntimeException {
        StringBuffer json = new StringBuffer("");
        try {
            if (list != null && list.size() > 0) {
                json.append("[\r\n");
                for (int i = 0; i < list.size() - 1; i++) {
                    json.append(bean2Json(list.get(i)) + ",\r\n");
                }
                json.append(bean2Json(list.get(list.size() - 1)));
                json.append("\r\n]");
            }

        } catch (Exception ex) {
            log.error("", ex);
            throw new RuntimeException(ex);
        }
        return json.toString();
    }

    public static List<?> json2List(String json, Class<?> clazz) throws RuntimeException {
        List<?> list = null;
        try {
            list = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonParseException ex) {
            log.error("", ex);
            throw new RuntimeException(ex);
        } catch (JsonMappingException ex) {
            log.error("", ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            log.error("", ex);
            throw new RuntimeException(ex);
        }
        return list;
    }

    public static Object json2Bean(String json, Class<?> clazz) throws RuntimeException {
        Object obj = null;
        try {
            obj = mapper.readValue(json, clazz);
        } catch (Exception ex) {
            log.error("", ex);
            throw new RuntimeException(ex);
        }
        return obj;
    }

    public static Object inputStream2Bean(InputStream is, Class<?> clazz) throws RuntimeException {
        Object obj = null;
        try {
            obj = mapper.readValue(is, clazz);
        } catch (Exception ex) {
            log.error("", ex);
            throw new RuntimeException(ex);
        }
        return obj;
    }

    /**
     * 将字符串json转为map
     * 
     * @param json
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> json2Map(String json) throws RuntimeException {
        try {
            return mapper.readValue(json, Map.class);
        } catch (Exception e) {
            log.error("A json string to the map fail", e);
            throw new RuntimeException(e);
        }
    }
}
