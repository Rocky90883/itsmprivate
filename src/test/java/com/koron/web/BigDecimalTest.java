package com.koron.web;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BigDecimalTest {



    public void test(){
        BigDecimal a  = new BigDecimal(100);
        BigDecimal b  = new BigDecimal(10);
        BigDecimal c  = new BigDecimal(20);
        System.out.println(a.add(b));
        System.out.println(a.subtract(b).subtract(c));
        System.out.println(a);
    }


    public void test2(){
        Map<String,String> map = new HashMap<>();
        map.put("我爱你","幅度萨芬");
        map.put("飞洒发的","将很快归还借款");
        map.put("法动手","烦得很");
        map.put("飞洒啊","阿斯弗");
        System.out.println(map);
    }



     public void test3(){
        BigDecimal a = new BigDecimal(".00");
        System.out.println(a);
    }
}
