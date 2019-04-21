package me.qebs.restdemo.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 获取uuid 去掉中横线
     *
     * @return
     */
    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

}
