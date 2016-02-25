package com.liu.lframelib.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 解析列表的数据
 * 
 * @param <T>
 *            列表的实体类
 */
public class ListDataParser<T> {
	//默认单页10个
	private static final int DEFAULT_SINGLE_PAGE_NUMBER = 10;
	// 单页的列表个数
	private int singPageNumber;

	public ListDataParser() {
		singPageNumber = DEFAULT_SINGLE_PAGE_NUMBER;
	}

	public ListDataParser(int singlePageNumber) {
		this.singPageNumber = singlePageNumber;
	}

	static class ParseResult<T> {
		public boolean nextEnable;
		public List<T> mResult;

		@Override
		public String toString() {
			return "ParseResult [nextEnable=" + nextEnable + ", mResult=" + mResult + "]";
		}
	}

	static class ParseParam {
		public String paramName;

		@Override
		public String toString() {
			return "ParseParam [paramName=" + paramName + "]";
		}

	}

	/**
	 * 拿到List的数据
	 * @param data json数据
	 * @param params json指向Array的每级key｛"data":[]｝-> list.add("data")
	 * @param t 列表实体类
	 * @return
	 * @throws JSONException
	 */
	public ParseResult<T> getResult(String data, List<ParseParam> params, Class<T> t) throws JSONException {
		ParseResult<T> result = new ParseResult<T>();
		parseData(data, params, t, result);
		if (result.mResult == null || result.mResult.size() < singPageNumber) {
			result.nextEnable = false;
		} else {
			result.nextEnable = true;
		}
		return result;
	}

	public void parseData(String data, List<ParseParam> params, Class<T> t, ParseResult<T> result)
			throws JSONException {
		if (params == null || params.isEmpty() || TextUtils.isEmpty(data)) {
			result.mResult = null;
		} else if (params.size() == 1) {
			JSONObject obj = new JSONObject(data);
			JSONArray array = obj.optJSONArray(params.get(0).paramName);
			result.mResult = JsonTools.jsonObjArray(array, t);
		} else {
			JSONObject obj = new JSONObject(data);
			JSONObject subObj = obj.optJSONObject(params.get(0).paramName);
			params.remove(0);
			parseData(subObj.toString(), params, t, result);
		}
	}

}
