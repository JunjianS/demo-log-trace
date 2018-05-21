package com.sjj.trace.constant;

/**
 * 
 * 跟踪id类型，默认是请求记录ID
 *
 */
public enum TraceType {

    REQUEST("reqId");

    private String name;

    public String getName() {
        return name;
    }

    TraceType(String name) {
        this.name = name;
    }

}
