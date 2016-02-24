package com.liu.lframelib.net;

import java.util.HashMap;

/**
 * 上传post方式，需要的参数
 * 
 * @author liu
 * 
 *         2014-9-2
 */
public class PostParams extends HashMap<String, String> {
	private static final long serialVersionUID = 8112047472727256876L;

	public PostParams with(String key, String value) {
		put(key, value);
		return this;
	}
}
