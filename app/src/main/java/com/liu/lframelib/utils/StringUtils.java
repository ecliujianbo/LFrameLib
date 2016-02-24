package com.liu.lframelib.utils;

public class StringUtils {
	/**
	 * 字符串排序的任何情况
	 * @param input
	 */
	public static void permutation(String input) {
		permutation("", input);
	}

	private static void permutation(String perm, String word) {
		if (word.isEmpty()) {
			System.err.println(perm + word);
		} else {
			for (int i = 0; i < word.length(); i++) {
				permutation(perm + word.charAt(i), word.substring(0, i) + word.substring(i + 1, word.length()));
			}
		}
	}

}
