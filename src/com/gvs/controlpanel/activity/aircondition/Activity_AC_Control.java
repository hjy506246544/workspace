package com.gvs.controlpanel.activity.aircondition;

import greendao.DBHelper;
import greendao.bean.ACEntity;

import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.SlideSwitch;
import com.gvs.controlpanel.widget.SlideSwitch.OnStateChangedListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 空调详情界面
 * 
 * @author hjy 2016-06-30
 */
public class Activity_AC_Control extends Activity implements OnClickListener,
		OnSeekBarChangeListener {
	SlideSwitch mBtnSW;
	ImageView mACDec, mACInc, mModeHot, mModeCold, mModeDry, ModeWind;
	ImageView mWindHigh, mWindMid, mWindLow, mTypeIcon, mModeWind;
	public Header header;
	LinearLayout mACCtrlErea;
	TextView mAppName;
	String strText;
	private DBHelper dBManager;
	private int address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ac_control);
		dBManager = DBHelper.getInstance(this);

		mACDec = (ImageView) findViewById(R.id.btn_ac_dec);// 温度调节，减
		mACInc = (ImageView) findViewById(R.id.btn_ac_inc);// 温度调节，加

		mModeHot = (ImageView) findViewById(R.id.btn_mode_hot);// 制热
		mModeCold = (ImageView) findViewById(R.id.btn_mode_cold);// 制冷
		mModeDry = (ImageView) findViewById(R.id.btn_mode_dry);// 抽湿
		mModeWind = (ImageView) findViewById(R.id.btn_mode_wind);// 自动

		mWindHigh = (ImageView) findViewById(R.id.btn_wind_high);// 高风
		mWindMid = (ImageView) findViewById(R.id.btn_wind_mid);// 中风
		mWindLow = (ImageView) findViewById(R.id.btn_wind_low);// 低风
		mTypeIcon = (ImageView) findViewById(R.id.ac_type_icon);//

		// mAppName= (TextView) findViewById(R.id.txtview_ac_ctrl);
		header = (Header) findViewById(R.id.activity_ac_header);
		mACDec.setOnClickListener(this);
		mACInc.setOnClickListener(this);
		mModeHot.setOnClickListener(this);
		mModeCold.setOnClickListener(this);
		mModeDry.setOnClickListener(this);
		mModeWind.setOnClickListener(this);
		mWindHigh.setOnClickListener(this);
		mWindMid.setOnClickListener(this);
		mWindLow.setOnClickListener(this);

		mBtnSW = (SlideSwitch) findViewById(R.id.ac_slideswitch);
		mBtnSW.setOnStateChangedListener(new OnStateChangedListener() {

			@Override
			public void onStateChanged(boolean state) {
				// TODO Auto-generated method stub

				if (state == true) {
					Toast.makeText(Activity_AC_Control.this, "OPEN",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(Activity_AC_Control.this, "CLOSE",
							Toast.LENGTH_SHORT).show();

				}
			}
		});

		Intent itent = getIntent();
		// mAppName.setText(getResources().getString(R.string.controlcenter_mainmenu_ac)+":"+itent.getStringExtra("TITLE_NAME"));
		strText = itent.getStringExtra("TITLE_NAME");
		header.setTitle(getResources().getString(
				R.string.controlcenter_mainmenu_ac)
				+ ":" + itent.getStringExtra("TITLE_NAME"));

		header.setLeftImageVewRes(R.drawable.btn_return, new OnClickListener() {

			@Override
			public void onClick(View v) {
				Activity_AC_Control.this.finish();
			}
		});

		List<ACEntity> mList;

		mList = dBManager.select_AC(strText);

		if (mList.isEmpty()) {
			ToastUtils.show(Activity_AC_Control.this,
					getResources().getString(R.string.target_not_exist));
			return;

		} else {
			address = mList.get(0).getAddress();
			mTypeIcon.setBackgroundResource(mList.get(0).getIconId());
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.blinds_open) {

		} else if (v.getId() == R.id.blinds_stop) {

		} else if (v.getId() == R.id.blinds_close) {

		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}
}
