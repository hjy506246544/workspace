package com.gvs.controlpanel.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.activity.main.MainActivity;
import com.gvs.controlpanel.activity.main.MainTwoActivity;
import com.gvs.controlpanel.activity.main.MainActivity;
/**
 * 主题选择
 * @author hjy
 * 2016-6-2
 */
public class SplashActivity extends FragmentActivityBase {
	private Button themeone_btn,themeTwo_btn,themeThree_btn;
	private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
		initView();
		initData();
		initListener();
    }

    private void initData() {

	}

	private void initListener() {

		themeone_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent  = new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});

		themeTwo_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent2  = new Intent(SplashActivity.this,MainTwoActivity.class);
				startActivity(intent2);
			}
		});
	}

    private void initView() {
    	themeone_btn = (Button) findViewById(R.id.themeOne_btn);
    	themeTwo_btn = (Button) findViewById(R.id.themeTwo_btn);
	}

    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event){
		if (event.getAction() == KeyEvent.KEYCODE_HOME) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}
