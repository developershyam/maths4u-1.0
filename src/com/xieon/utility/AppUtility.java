package com.xieon.utility;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Random;

import android.content.Context;
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

	public static String loadJSON(Context context, String fileName) {
		String json = null;
		try {
			InputStream is = context.getAssets().open(fileName);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");
		} catch (Exception e) {
			System.out.println("Exception: while loading json " + e);
		}
		return json;
	}

	public static HashSet<Integer> generateRandom(int size, int max) {
		int value;
		Random rand = new Random();
		HashSet<Integer> randomNumbers = new HashSet<Integer>();
		for (int i = 0; i < size; i++) {
			value = rand.nextInt(max);
			randomNumbers.add(value);
		}
		System.out.println("generatedv random num: " +randomNumbers );
		return randomNumbers;
	}
}
