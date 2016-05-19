package com.gvs.controlpanel.activity;
import com.gvs.controlpanel.R;

import android.app.Application;
import android.os.Handler;

/**
 * 全局/共享变量
 * @author hjy
 *
 */
public class MyApplication extends Application {
	// 共享变量
	private Handler handler = null;
	public static final int BJT = R.drawable.main_bj;
	public static final int BJT2 = R.color.blue;
	public static final int BJT3 = R.color.translucent_8;

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}