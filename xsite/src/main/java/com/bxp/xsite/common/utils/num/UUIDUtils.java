package com.bxp.xsite.common.utils.num;

import java.util.UUID;

public class UUIDUtils {
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
