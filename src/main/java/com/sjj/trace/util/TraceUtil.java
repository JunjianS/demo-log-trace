package com.sjj.trace.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class TraceUtil {

	public static final String DEFAULTSOURCE = "unknow";

	public static String genUUID() {
		return UUID.randomUUID().toString();
	}

	public static String genUUIDPrefixSource(String source) {
		StringBuilder sb = new StringBuilder(StringUtils.isNotBlank(source) ? source : DEFAULTSOURCE);
		sb.append("-").append(genUUID());
		return sb.toString();
	}
}
