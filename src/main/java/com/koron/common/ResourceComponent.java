package com.koron.common;

import com.koron.main.App;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.regex.Pattern;

/**
 * @author lr
 */
@Component
@Data
public class ResourceComponent {


    public static String fileUploadPath;

    @Value("${storage.winLocation}")
    private String winLocation;
    @Value("${storage.linuxLocation}")
    private String linuxLocation;

    @PostConstruct
    public void init() {
        if (isWindowsSystem()) {
            fileUploadPath = winLocation;
        } else {
            fileUploadPath = linuxLocation;
        }
        File file = new File(fileUploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 从web访问路径转换成本地路径
     *
     * @param filePth
     * @return
     */
    public static Boolean checkFileExists(String filePth) {
        File file = new File(filePth);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * 从web访问路径转换成本地路径
     *
     * @param webPath
     * @return
     */
    public static String getLocation(String webPath) {
        webPath = StringUtils.substringAfter(webPath, App.PREVIEW);
        return fileUploadPath + webPath;
    }

    /**
     * 根据文件名获取文件资源
     *
     * @param fileName
     * @return
     */
    public static String getLocationPath(String fileName) {
        return fileUploadPath + fileName;
    }

    /**
     * 根据文件夹、文件名获取文件资源
     * @param floder
     * @param fileName
     * @return
     */
    public static String getLocationPath(String floder ,String fileName) {
        File file = new File(fileUploadPath + floder);
        if (!file.exists()) {
            file.mkdirs();
        }
        return fileUploadPath + floder + "/" +fileName;
    }

    /**
     * 从本地路径转换成web访问路径
     *
     * @param location
     * @return
     */
    public static String getWebPath(String location) {
        location = StringUtils.substringAfter(location, fileUploadPath);
        return App.PREVIEW + location;
    }

    /**
     * 判断系统是win linux
     *
     * @return
     */
    public static boolean isWindowsSystem() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Windows.*", osName)) {
            return true;
        }
        return false;
    }

}