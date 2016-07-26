package com.gvs.controlpanel.activity.systemset;
import java.util.ArrayList;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.util.PreferencesUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.SlideSwitch;
import com.gvs.controlpanel.widget.SlideSwitch.OnStateChangedListener;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
/**
 * 系统设置界面
 * 2016-7-25
 * @author hjy
 */
public class SystemSetActivity extends FragmentActivityBase {
	public Header header;
	Context context;
	//SlideSwitch setup_activity_iv_wifislideswitch;
	private RelativeLayout setup_activity_rl_wifi,setup_activity_rl_show,
						   setup_activity_rl_language,setup_activity_rl_aboutdevice,
						   setup_activity_rl_bluetooth,setup_activity_rl_more;
	public WifiManager wifiManager;
	private boolean wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setactivity_two);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.set_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				SystemSetActivity.this.finish();
			}
		});
	}

	private void initListener() {
		setup_activity_rl_wifi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
				startActivity(new Intent(SystemSetActivity.this,WifiActivity.class));
			}
		});

		setup_activity_rl_bluetooth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
			}
		});

		setup_activity_rl_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
			}
		});

    	setup_activity_rl_show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
			}
		});

    	setup_activity_rl_language.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
			}
		});

    	setup_activity_rl_aboutdevice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
				startActivity(new Intent(SystemSetActivity.this,AboutDeviceActivity.class));
			}
		});
	}

    private void initView() {
    	setup_activity_rl_wifi = (RelativeLayout) findViewById(R.id.setup_activity_rl_wifi);
    	setup_activity_rl_show = (RelativeLayout) findViewById(R.id.setup_activity_rl_show);
    	setup_activity_rl_language = (RelativeLayout) findViewById(R.id.setup_activity_rl_language);
    	setup_activity_rl_aboutdevice = (RelativeLayout) findViewById(R.id.setup_activity_rl_aboutdevice);
    	setup_activity_rl_bluetooth = (RelativeLayout) findViewById(R.id.setup_activity_rl_bluetooth);
    	setup_activity_rl_more = (RelativeLayout) findViewById(R.id.setup_activity_rl_more);
    	//setup_activity_iv_wifislideswitch = (SlideSwitch) findViewById(R.id.setup_activity_iv_wifislideswitch);
		header = (Header) findViewById(R.id.setup_activity_header);
	}
}
