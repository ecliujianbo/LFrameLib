package com.liu.lframelib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆形图片
 *  重写CircleImage，继承ImageView
 */
public class CircleImage extends ImageView {
	Bitmap b = null;
	Bitmap bitmap = null;
	Bitmap roundBitmap = null;

	public CircleImage(Context context) {
		super(context);
	}

	public CircleImage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleImage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		try {
			Drawable drawable = getDrawable();
			if (drawable == null) {
				return;
			}
			if (getWidth() == 0 || getHeight() == 0) {
				return;
			}
			b = ((BitmapDrawable) drawable).getBitmap();
			roundBitmap = getCroppedBitmap(b, getWidth());
			canvas.drawBitmap(roundBitmap, 0, 0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 对Bitmap裁剪，使其变成圆形，这步最关键
	 */
	private static Bitmap sbmp = null;

	public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
		if (sbmp != null) {
			sbmp = null;
		}
		if (bmp.getWidth() != radius || bmp.getHeight() != radius)
			sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
		else
			sbmp = bmp;
		bmp = null;
		Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
		// int minlenth = Math.min(sbmp.getWidth(), sbmp.getHeight());
		// final Rect rect = new Rect(0, 0, minlenth, minlenth);
		final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		paint.setColor(Color.parseColor("#BAB399"));

		Canvas c = new Canvas(output);
		c.drawARGB(0, 0, 0, 0);
		c.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		c.drawBitmap(sbmp, rect, rect, paint);
		return output;
	}

}