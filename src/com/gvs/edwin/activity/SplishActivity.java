package com.gvs.edwin.activity;


import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.main.MainMenuActivity;

import common.StaticUnits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplishActivity extends Activity {

	/**
	 * 三个切换的动画
	 */
	private Animation mFadeIn;
	private Animation mFadeInScale;
	private Animation mFadeOut;

	// @InjectView(R.id.image)
	ImageView mImageView;
	private String activityname = null;
	public static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splish);
		mImageView = (ImageView) findViewById(R.id.activity_splish_image);

		mImageView.setImageResource(R.drawable.flashball_01);


		initAnim();
		setListener();
		// Intent serInent=new Intent(SplishActivity.this,SerialService.class);
		// startService(serInent);

		Intent RecIntent = getIntent();
		activityname = RecIntent.getStringExtra(StaticUnits.TAG_SERIAL_ACTION);

	}

	

	private void initAnim() {
		mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
		mFadeIn.setDuration(500);
		mFadeInScale = AnimationUtils.loadAnimation(this,
				R.anim.welcome_fade_in_scale);
		mFadeInScale.setDuration(2000);
		mFadeOut = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_out);
		mFadeOut.setDuration(500);
		mImageView.startAnimation(mFadeIn);
	}

	/**
	 * 监听事件
	 */
	public void setListener() {
		/**
		 * 动画切换原理:开始时是用第一个渐现动画,当第一个动画结束时开始第二个放大动画,当第二个动画结束时调用第三个渐隐动画,
		 * 第三个动画结束时修改显示的内容并且重新调用第一个动画,从而达到循环效果
		 */
		mFadeIn.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				mImageView.startAnimation(mFadeInScale);
			}
		});
		mFadeInScale.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {

				mImageView.startAnimation(mFadeOut);
			}
		});
		mFadeOut.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {

			}

			public void onAnimationRepeat(Animation animation) {

			}

			public void onAnimationEnd(Animation animation) {
				mImageView.setVisibility(View.INVISIBLE);
				if (activityname == null) {
					startActivity(new Intent(SplishActivity.this,
							MainMenuActivity.class));
				}

				else if (activityname.equals(StaticUnits.ID_Light_Activity)) {
					startActivity(new Intent(
							SplishActivity.this,
							com.gvs.controlpanel.activity.light.Activity_Light.class));

				} else {
					startActivity(new Intent(SplishActivity.this,
							MainMenuActivity.class));
				}
				finish();
			}
		});
	}
}
