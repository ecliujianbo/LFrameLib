package com.liu.lframelib.widget.edit;

import android.widget.EditText;

/**
 * 手机号码格式
 */
public class EditPatternPhone implements IEditPattern {
	private EditText mEditText;

	public EditPatternPhone(EditText editText) {
		this.mEditText = editText;
	}

	private boolean mPersonalChange = false;

	@Override
	public void onEditTextChange(CharSequence s, int count) {
		if (mEditText == null)
			return;
		if (mPersonalChange) {
			mPersonalChange = false;
			return;
		}
		mPersonalChange = true;
		String finalString = "";
		int index = 0;
		String telString = s.toString().replace(" ", "");
		if ((index + 3) < telString.length()) {
			finalString += (telString.substring(index, index + 3) + " ");
			index += 3;
		}
		while ((index + 4) < telString.length()) {
			finalString += (telString.substring(index, index + 4) + " ");
			index += 4;
		}
		finalString += telString.substring(index, telString.length());
		mEditText.setText(finalString);
		mEditText.setSelection(finalString.length());
	}

}
