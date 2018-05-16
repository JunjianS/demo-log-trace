package com.cmcc.zeus.sp.trace.constant;

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
