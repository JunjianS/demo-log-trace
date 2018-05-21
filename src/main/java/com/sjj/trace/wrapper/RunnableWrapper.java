package com.sjj.trace.wrapper;

import com.sjj.trace.constant.TraceType;
import com.sjj.trace.util.MDCUtil;

/**
 * 封装Runnable类，用于使用线程池的场景，设置traceId
 *
 */
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
