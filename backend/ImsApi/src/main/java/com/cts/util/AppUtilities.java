package com.cts.util;

import org.springframework.stereotype.Component;

@Component
public class AppUtilities {
	
	public static String getRoles(boolean decider) {
		if (decider) {
			return "ROLE_ADMIN,ROLE_EMPLOYEE";
		} else {
			return "ROLE_EMPLOYEE";
		}
	}

}
