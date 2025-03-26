package com.ajackus.dlbms.utils;

public class CommonUtils {

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj.toString().trim().isEmpty();
    }
}
