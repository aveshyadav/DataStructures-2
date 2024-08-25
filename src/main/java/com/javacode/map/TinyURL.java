package com.javacode.map;

import java.util.HashMap;
import java.util.Map;

public class TinyURL {

	public static void main(String[] args) {

		TinyURL url = new TinyURL();
		String u1 = url.encode("https://leetcode.com/problems/design-tinyurl1");
		String u2 = url.encode("https://leetcode.com/problems/design-tinyurl2");
		String u3 = url.encode("https://leetcode.com/problems/design-tinyurl3");
		String u4 = url.encode("https://leetcode.com/problems/design-tinyurl4");
		String u5 = url.encode("https://leetcode.com/problems/design-tinyurl5");

		url.decode(u1);
		url.decode(u2);
		url.decode(u3);
		url.decode(u4);
		url.decode(u5);

	}

	Map<String, String> map = new HashMap<>();

	private String encode(String url) {

		StringBuilder sb = new StringBuilder();

		int val = (int) (Math.random() * 26);
		sb.append((char) (val + 97));

		while (sb.length() < 5 || map.containsKey(sb.toString())) {

			val = (int) (Math.random() * 26);
			sb.append((char) (val + 97));
		}

		map.put(sb.toString(), url);
		System.out.println(sb.toString());
		return sb.toString();
	}

	private void decode(String url) {

		String ans = map.get(url);
		System.out.println(ans);
	}

}
