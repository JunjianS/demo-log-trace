package com.cmcc.zeus.sp.trace.wrapper;

import com.cmcc.zeus.sp.trace.constant.TraceType;
import com.cmcc.zeus.sp.trace.util.MDCUtil;

public class RunnableWrapper implements Runnable {
    
    private Runnable runnable;
    private String   traceId;

    public RunnableWrapper(Runnable runnable) {
        this.runnable = runnable;
        String traceId = MDCUtil.get(TraceType.REQUEST);
        this.traceId = null == traceId ? "" : traceId;
    }

    public void run() {
        MDCUtil.put(TraceType.REQUEST, traceId);
        runnable.run();
        MDCUtil.clear();
    }
}
