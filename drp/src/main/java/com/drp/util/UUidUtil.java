package com.drp.util;

import java.util.UUID;

/**
 * 生成uuid的方法 生成的字符串长度为10
 * @author curry
 *
 */
public class UUidUtil {
	public static String getUUID() {
        return UUID.randomUUID().toString().substring(0,10);
    }
}
