package com.cmcc.zeus.sp.trace.util;

import org.slf4j.MDC;

import com.cmcc.zeus.sp.trace.constant.TraceType;

public class MDCUtil {

    public static void put(TraceType type, String value) {
        MDC.put(type.getName(), value);
    }

    public static String get(TraceType type) {
        return MDC.get(type.getName());
    }

    public static void remove(TraceType type) {
        MDC.remove(type.getName());
    }

    public static void clear() {
        MDC.clear();
    }

}
