package com.liu.lframelib.utils;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

public class TextUtil {
	/**
	 * 设置文字中，特殊文字的字体
	 * 
	 * @param source
	 * @param color
	 * @param message
	 * @param context
	 * 
	 * @return
	 */
	private static Spanned setSpannedColor(Context context, String source, final int color, String... message) {
		final Context ctxt = context.getApplicationContext();
		SpannableString ss = new SpannableString(source);
		if (TextUtils.isEmpty(source)) {
			return ss;
		}
		final String[] ms = message;
		for (int i = 0; i < ms.length; i++) {
			if (TextUtils.isEmpty(ms[i])) {
				continue;
			}
			if (source.contains(ms[i])) {
				// ss.setSpan(new ForegroundColorSpan(color),
				// source.indexOf(ms[i]),
				// source.indexOf(ms[i]) + ms[i].length(),
				// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				ss.setSpan(new ClickableSpan() {
					@SuppressLint("NewApi")
					@Override
					public void onClick(View widget) {
						ClipboardManager cmb = (ClipboardManager) ctxt.getSystemService(Context.CLIPBOARD_SERVICE);
						cmb.setText(ms[1]);
						Toast.makeText(ctxt, ms[1] + " 已经复制到粘贴板.", Toast.LENGTH_SHORT).show();

					}

					@Override
					public void updateDrawState(TextPaint ds) {
						super.updateDrawState(ds);
						ds.setColor(color);
						ds.setUnderlineText(false);
					}
				}, source.indexOf(ms[i]), source.indexOf(ms[i]) + ms[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}

		return ss;
	}

}
