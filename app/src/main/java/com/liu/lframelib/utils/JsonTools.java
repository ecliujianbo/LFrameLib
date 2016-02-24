package com.liu.lframelib.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * 
 * @author liu
 * 
 *         2014-3-10
 */
public class JsonTools {
	private static Gson gson;

	private JsonTools() {
	}

	public static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	/**
	 * 解析json
	 * 
	 * @param <T>
	 * @param jsonData
	 * @param cls
	 * @return
	 */
	public static <T> T jsonObj(String jsonData, Class<T> cls) {
		if (TextUtils.isEmpty(jsonData)) {
			return null;
		}
		T t = null;
		try {
			t = getGson().fromJson(jsonData, cls);
		} catch (JsonSyntaxException e) {
			return null;
		} catch (JsonParseException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return t;
	}

	/**
	 * 解析jsonArray
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonObjArray(String jsonArray, Class<T> clazz) {
		if (TextUtils.isEmpty(jsonArray)) {
			return null;
		}
		List<T> lists = new ArrayList<T>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(jsonArray).getAsJsonArray();
		for (JsonElement obj : array) {
			T t = getGson().fromJson(obj, clazz);
			lists.add(t);
		}
		return lists;
	}

	/**
	 * 解析jsonArray
	 * 
	 * @param <T>
	 * @param jsonArray
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonObjArray(JSONArray jsonArray, Class<T> clazz) {
		List<T> lists = jsonObjArray(jsonArray.toString(), clazz);
		return lists;

	}

	/**
	 * 保存json的List对象
	 * 
	 * @param lists
	 */
	public static <T> String listToJson(List<T> lists) {
		String jsonString = getGson().toJson(lists);
		return jsonString;
	}

	/**
	 * bean 转换成json
	 * 
	 * @param object
	 * @return
	 */
	public static String beanToJson(Object object) {
		if (object == null) {
			return null;
		}
		return getGson().toJson(object);

	}
}
