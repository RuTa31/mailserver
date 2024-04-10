package com.Qpay.costumer.util;

public class ValidatorUtils {
    public static boolean isNull(Object pValue) {
        return pValue == null;
    }

    public static boolean isNotNull(Object pValue) {
        return !isNull(pValue);
    }
}
