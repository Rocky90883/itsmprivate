package com.koron.web;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Util {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJsonByObject(Object obj){
        String jsonStr = null;
        try {
            jsonStr =  mapper.writeValueAsString(obj);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }

}
