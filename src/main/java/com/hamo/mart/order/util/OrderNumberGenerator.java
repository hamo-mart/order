package com.hamo.mart.order.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumberGenerator {
    private static long sequence = 0L;

    public static synchronized String generateOrderNumber() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        sequence++;
        return date + String.format("-%06d", sequence); // 6자리 시퀀스
    }

}