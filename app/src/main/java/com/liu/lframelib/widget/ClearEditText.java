package com.liu.lframelib.widget;


import com.liu.lframelib.R;
import com.liu.lframelib.widget.edit.IEditPattern;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * 使用pattern，你必须调用setEditType方法(手机号，银行卡),如果设置了Type，必须使用getValue()获取值
 */
public class ClearEditText extends EditText {
	private static final int DEAFUALT_CLEAR_ICON = R.drawable.icon_edit_clear_normal;
	private Drawable mClearDrawable;
	private boolean mIsFocused = false;
	private IEditPattern mEditPattern;

	public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	public ClearEditText(Context context) {
		this(context, null);
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public String getValue() {
		return getText().toString().replace(" ", "");
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText);

		int resClearId = a.getResourceId(R.styleable.ClearEditText_iconClear, DEAFUALT_CLEAR_ICON);
		mClearDrawable = getResources().getDrawable(resClearId);
		updateClearIcon();
		a.recycle();
		addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (mEditPattern != null) {
					mEditPattern.onEditTextChange(s, count);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				updateClearIcon();
			}
		});
	}

	public void setEditType(IEditPattern pattern) {
		this.mEditPattern = pattern;
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		mIsFocused = focused;
		updateClearIcon();
	}

	private void updateClearIcon() {
		Drawable[] drawables = getCompoundDrawables();
		if (length() > 0 && mIsFocused) {
			setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], mClearDrawable, drawables[3]);
		} else {
			setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], null, drawables[3]);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int x = (int) event.getX();
			if (x < getWidth() && x > getWidth() - getCompoundPaddingRight()) {
				setText("");
			}
		}
		return super.onTouchEvent(event);
	}
}
