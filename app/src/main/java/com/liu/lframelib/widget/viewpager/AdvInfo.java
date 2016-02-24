package com.liu.lframelib.widget.viewpager;

public class AdvInfo {
	// 圆圈标志
	private int iconResId;
	// 介绍
	private String intro;

	private String imageUr;

//	public AdvInfo(int iconResId, String intro) {
//		super();
//		this.iconResId = iconResId;
//		this.intro = intro;
//	}

	public AdvInfo(String imageUr,String intro) {
		super();
		this.intro = intro;
		this.imageUr = imageUr;
	}

	public String getImageUr() {
		return imageUr;
	}

	public void setImageUr(String imageUr) {
		this.imageUr = imageUr;
	}

	public int getIconResId() {
		return iconResId;
	}

	public void setIconResId(int iconResId) {
		this.iconResId = iconResId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}