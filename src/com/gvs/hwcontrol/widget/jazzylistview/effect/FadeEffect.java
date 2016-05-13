package com.gvs.hwcontrol.widget.jazzylistview.effect;

import com.gvs.hwcontrol.widget.jazzylistview.JazzyEffect;
import com.gvs.hwcontrol.widget.jazzylistview.JazzyHelper;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class FadeEffect implements JazzyEffect {

	private static final int DURATION_MULTIPLIER = 5;

	@Override
	public void initView(View item, int position, int scrollDirection) {
		ViewHelper.setAlpha(item, JazzyHelper.TRANSPARENT);
	}

	@Override
	public void setupAnimation(View item, int position, int scrollDirection,
			ViewPropertyAnimator animator) {
		animator.setDuration(JazzyHelper.DURATION * DURATION_MULTIPLIER);
		animator.alpha(JazzyHelper.OPAQUE);
	}

}
