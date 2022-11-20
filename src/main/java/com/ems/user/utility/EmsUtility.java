package com.ems.user.utility;

public class EmsUtility {

	public static boolean isNullOrEmpty(String s) {
		if (s.equals("") || s == null)
			return true;
		return false;
	}

}
