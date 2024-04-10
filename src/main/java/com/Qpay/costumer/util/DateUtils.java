package com.Qpay.costumer.util;

import java.time.Instant;

public class DateUtils {

    public static Boolean timestampIsExpired(long pTimestamp) {
        // Convert the long timestamp to an Instant
        Instant timestampInstant = Instant.ofEpochMilli(pTimestamp * 1000);

        // Get the current time
        Instant currentInstant = Instant.now();
        if (timestampInstant.isBefore(currentInstant)) {
            return true;
        } else {
            return false;
        }
    }
}
