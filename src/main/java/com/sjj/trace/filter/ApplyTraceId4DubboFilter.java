package com.sjj.trace.filter;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.sjj.trace.constant.TraceType;
import com.sjj.trace.util.MDCUtil;
import com.sjj.trace.util.TraceUtil;

public class ApplyTraceId4DubboFilter implements Filter {
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		
		Object group = RpcContext.getContext().get("group");
		
		if (RpcContext.getContext().isConsumerSide()) {
			String traceId = MDCUtil.get(TraceType.REQUEST);
			if (StringUtils.isBlank(traceId)) {
				traceId = TraceUtil.genUUID();
			}
			RpcContext.getContext().setAttachment(TraceType.REQUEST.getName(), traceId);
		}
		if (RpcContext.getContext().isProviderSide()) {
			String traceId = RpcContext.getContext().getAttachment(TraceType.REQUEST.getName());
			if (StringUtils.isBlank(traceId)) {
				traceId = TraceUtil.genUUID();
			}
			MDCUtil.put(TraceType.REQUEST, traceId);
		}
		return invoker.invoke(invocation);
	}
}
