package com.Qpay.costumer.util;

public class StringUtils {
    public static boolean isNullOrEmpty(String pData) {
        return pData == null || pData.trim().isEmpty();
    }

    public static String capitalizeFirstLetter(String pData) {
        if (isNullOrEmpty(pData)) {
            return pData;
        }
        return pData.substring(0, 1).toUpperCase() + pData.substring(1);
    }
}
