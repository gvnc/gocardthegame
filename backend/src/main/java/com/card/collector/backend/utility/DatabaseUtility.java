package com.card.collector.backend.utility;

import java.sql.Date;
import java.sql.Timestamp;

public class DatabaseUtility {

    public static Date getNow(){
        return new java.sql.Date(System.currentTimeMillis());
    }

    public static Timestamp getNowInTimestamp(){
        return new java.sql.Timestamp(System.currentTimeMillis());
    }
}
