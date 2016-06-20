package com.gvs.controlpanel.activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.aircondition.AirConditionActivity;
import com.gvs.controlpanel.activity.backgroundmusic.BgMusicActivity;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.activity.curtain.CurtainActivity;
import com.gvs.controlpanel.activity.light.LightActivity;
import com.gvs.controlpanel.activity.main.MainTwoActivity;
import com.gvs.controlpanel.activity.scene.SceneActivity;
import com.gvs.controlpanel.activity.securitymonitor.SecurityMonitorActivity;
import com.gvs.controlpanel.activity.tv.TVActivity;
/**
 * 根据数据传过来的activity值响应不同的activity界面
 * @author hjy
 * 2016-6-20
 */
public class SplashTwoActivity extends FragmentActivityBase {
	private long mExitTime;
	public static final String LIGHT = "light";
	public static final String CURTAIN = "curtain";
	public static final String backgroundmusic = "backgroundmusic";
	public static final String aircondition = "aircondition";
	public static final String hometheatre = "hometheatre";
	public static final String scene = "scene";
	public static final String tv = "tv";
	public static final String securitymonitor = "securitymonitor";
	public static final String set = "set";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashtwo_activity);
        SplashTwoActivity.this.finish();
        startActivity(new Intent(SplashTwoActivity.this, MainTwoActivity.class));
//        if(getIntent().getExtras().get("intent").equals(LIGHT)){
//        	Log.e("8923gadg", "8923gadg");
//        	startActivity(new Intent(SplashTwoActivity.this, LightActivity.class));
////        }else if(getIntent().getExtras().get("intent").equals(CURTAIN)){
////        	startActivity(new Intent(SplashTwoActivity.this, CurtainActivity.class));
////        }else if(getIntent().getExtras().get("intent").equals(aircondition)){
////        	startActivity(new Intent(SplashTwoActivity.this, AirConditionActivity.class));
////        }else if(getIntent().getExtras().get("intent").equals(tv)){
////        	startActivity(new Intent(SplashTwoActivity.this, TVActivity.class));
////        }else if(getIntent().getExtras().get("intent").equals(hometheatre)){
////        	startActivity(new Intent(SplashTwoActivity.this, MainTwoActivity.));
//
//        }else if(getIntent().getExtras().get("intent").equals(backgroundmusic)){
//        	startActivity(new Intent(SplashTwoActivity.this, BgMusicActivity.class));
//        }else if(getIntent().getExtras().get("intent").equals(scene)){
//        	startActivity(new Intent(SplashTwoActivity.this, SceneActivity.class));
//        }else if(getIntent().getExtras().get("intent").equals(securitymonitor)){
//        	startActivity(new Intent(SplashTwoActivity.this, SecurityMonitorActivity.class));
//        }else if(getIntent().getExtras().get("intent").equals(set)){
//        	startActivity(new Intent(SplashTwoActivity.this, SetActivity.class));
//        }else{
//        	Log.e("8923gadg", "8923gadg");
//        	startActivity(new Intent(SplashTwoActivity.this, MainTwoActivity.class));
//        }
    }

    private void initData() {

	}

	private void initListener() {
	}

    private void initView() {
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
