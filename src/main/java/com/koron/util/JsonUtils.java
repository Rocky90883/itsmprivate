package com.koron.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;



public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Gson gson = new Gson();
//    private static final Gson nullGson  = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<String>()).create();
//
//    public static <T> T objectToPojoNullToOther(Object jsonData, Class<T> beanType) throws Exception {
//        T t = nullGson.fromJson(nullGson.toJson(jsonData), beanType);
//        return t;
//    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> stringToMap(String data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(data, map.getClass());
        return map;
    }

    public static JsonNode stringToJsonNode(String data) {
        try {
            JsonNode jsonNode = MAPPER.readTree(data);
            return jsonNode;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象转换成json字符串。
     * <p>
     * Title: pojoToJson
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData
     *            json数据
     * @param beanType
     *            对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) throws Exception {
        T t = MAPPER.readValue(jsonData, beanType);
        return t;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData
     *            json数据
     * @param beanType
     *            对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T objectToPojo(Object jsonData, Class<T> beanType) throws Exception {
        T t = MAPPER.readValue(MAPPER.writeValueAsString(jsonData), beanType);
        return t;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>
     * Title: jsonToList
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json结果集转化为对象,忽略json中多出的字段
     *
     * @param jsonData
     *            json数据
     * @param beanType
     *            对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T jsonToPojoIgnoreNone(String jsonData, Class<T> beanType) throws Exception {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T t = MAPPER.readValue(jsonData, beanType);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return t;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>
     * Title: jsonToList
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToListIgnoreNone(String jsonData, Class<T> beanType) {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @param jsonData
     *            json数据
     * @param beanType
     *            对象中的object类型
     * @return
     * @throws Exception
     */
    public static <T> T objectToPojoIgnoreNone(Object jsonData, Class<T> beanType) throws Exception {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T t = MAPPER.readValue(MAPPER.writeValueAsString(jsonData), beanType);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return t;
    }
}
