package com.unicorn.sxmobileoa.app.mess;

import java.util.Random;

public class RandomGeneter {
	private static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	private static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	public static final String numberChar = "0123456789";

	/***************************************************************************
	 * * 返回一个定长的随机字符串(只包含大小写字母、数字) *
	 * 
	 * @param length *
	 *            随机字符串长度 *
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuilder stringBuffer = new StringBuilder();
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < length; i++) {
			stringBuffer.append(allChar.charAt(random.nextInt(allChar.length())));
		}

		return stringBuffer.toString();
	}

//	/**
//	 * * 返回一个定长的随机纯字母字符串(只包含大小写字母) * *
//	 *
//	 * @param length *
//	 *            随机字符串长度 *
//	 * @return 随机字符串
//	 */
//	private static String generateMixString(int length) {
//		StringBuilder stringBuffer = new StringBuilder();
//		Random random = new Random(System.currentTimeMillis());
//		for (int i = 0; i < length; i++) {
//			stringBuffer.append(allChar.charAt(random.nextInt(letterChar.length())));
//		}
//		return stringBuffer.toString();
//	}


//	public static String generateLowerString(int length) {
//		return generateMixString(length).toLowerCase();
//	}


//	public static String generateUpperString(int length) {
//		return generateMixString(length).toUpperCase();
//	}

//	/**
//	 * * 生成一个定长的纯0字符串 * *
//	 *
//	 * @param length *
//	 *            字符串长度 *
//	 * @return 纯0字符串
//	 */
//	private static String generateZeroString(int length) {
//		StringBuilder stringBuffer = new StringBuilder();
//		for (int i = 0; i < length; i++) {
//			stringBuffer.append('0');
//		}
//		return stringBuffer.toString();
//	}


//	public static String toFixdLengthString(long num, int fixdlenth) {
//		StringBuffer sb = new StringBuffer();
//		String strNum = String.valueOf(num);
//		if (fixdlenth - strNum.length() >= 0) {
//			sb.append(generateZeroString(fixdlenth - strNum.length()));
//		} else {
//			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
//					+ "的字符串发生异常！");
//		}
//		sb.append(strNum);
//		return sb.toString();
//	}


//	public static String toFixdLengthString(int num, int fixdlenth) {
//		StringBuffer sb = new StringBuffer();
//		String strNum = String.valueOf(num);
//		if (fixdlenth - strNum.length() >= 0) {
//			sb.append(generateZeroString(fixdlenth - strNum.length()));
//		} else {
//			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
//					+ "的字符串发生异常！");
//		}
//		sb.append(strNum);
//		return sb.toString();
//	}

}
