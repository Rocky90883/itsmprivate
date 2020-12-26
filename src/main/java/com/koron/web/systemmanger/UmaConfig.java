package com.koron.web.systemmanger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UmaConfig {

    @Value("${uma.umaOrgNode.url}")
    public static String UMA_ORGNODE_URL;

    @Value("${uma.umaStaff.url}")
    public static String UMA_STAFF_URL;

    public static String getUmaOrgnodeUrl() {
        return UMA_ORGNODE_URL;
    }

    public static void setUmaOrgnodeUrl(String umaOrgnodeUrl) {
        UMA_ORGNODE_URL = umaOrgnodeUrl;
    }

    public static String getUmaStaffUrl() {
        return UMA_STAFF_URL;
    }

    public static void setUmaStaffUrl(String umaStaffUrl) {
        UMA_STAFF_URL = umaStaffUrl;
    }
}
