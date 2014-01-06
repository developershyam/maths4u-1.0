package com.xieon.utility;

import com.xieon.constant.AppConstants;

public class AppUtility {

	public static String shrink(String str) {
		if (str != null)
			str = str.replaceAll("\\s+", "");

		return str;
	}

	public static String createHeaderURL(String str) {

		if (str != null) {

			str = str.replaceAll(AppConstants.HEADER_URL_SUFFIX,
					AppConstants.HEADER_URL_SUFFIX_HTML);
		}

		return str;
	}

	public static String addHeaderURLSuffix(String str) {

		if (str != null) {

			str = str.concat(AppConstants.HEADER_URL_SUFFIX);
		}
		return str;
	}
}
