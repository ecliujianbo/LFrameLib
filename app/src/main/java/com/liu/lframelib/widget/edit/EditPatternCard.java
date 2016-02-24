package com.liu.lframelib.widget.edit;

import android.widget.EditText;

/**
 * 
 * 匹配银行卡
 */
public class EditPatternCard implements IEditPattern {
	private static final int EACH_LENGTH = 4;
	private EditText mEditText;

	public EditPatternCard(EditText editText) {
		this.mEditText = editText;
	}

	@Override
	public void onEditTextChange(CharSequence s, int count) {
		if (mEditText == null)
			return;
		int len = s.length();
		if (len < EACH_LENGTH)// 长度小于要求的数
			return;
		if (count > 1) {// 设置新字符串的时候，直接返回
			return;
		}
		// 如果包含空格，就清除
		char[] chars = s.toString().replace(" ", "").toCharArray();
		len = chars.length;
		// 每4个分组,加上空格组合成新的字符串
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			// 每次遍历到4的倍数，就添加一个空格
			if (i % EACH_LENGTH == 0 && i != 0) {
				sb.append(" ");
				sb.append(chars[i]);// 添加字符
			} else {
				sb.append(chars[i]);// 添加字符
			}
		}
		final String text = sb.toString();
		mEditText.setText(text);
		mEditText.setSelection(text.length());
	}
}
