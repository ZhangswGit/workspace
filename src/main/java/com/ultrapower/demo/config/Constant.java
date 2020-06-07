package com.ultrapower.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {
	public static long cahceExpired_in;

	@Value(value = "${cahceExpired_in:30}")
	public void setCahceExpired_in(long cahceExpired_in) {
		Constant.cahceExpired_in = cahceExpired_in;
	}

}
