package com.gggw.util;

import java.util.UUID;

public class UuidUtil {

	private UuidUtil() {
	}

	public static String get32UUID() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
}

