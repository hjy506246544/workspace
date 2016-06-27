package com.gvs.controlpanel.activity.aircondition;

import greendao.DBHelper;
import greendao.ACEntity;

import java.util.List;

import com.gvs.controlpanel.R;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.SlideSwitch;
import com.gvs.controlpanel.widget.SlideSwitch.OnStateChangedListener;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Activity_AC_Control extends Activity implements
		OnClickListener,  OnSeekBarChangeListener {
	SlideSwitch mBtnSW;
	ImageView mACDec;
	ImageView mACInc;
	
	ImageView mModeHot;
	ImageView mModeCold;
	ImageView mModeDry;
	ImageView mModeWind;
	
	ImageView mWindHigh;
	ImageView mWindMid;
	ImageView mWindLow;
	ImageView mTypeIcon;
	LinearLayout mACCtrlErea;
    TextView  mAppName;
    String strText;
    private DBHelper dBManager;
    private int address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ac_control);
		dBManager = DBHelper.getInstance(this);

		mACDec = (ImageView) findViewById(R.id.btn_ac_dec);
		mACInc = (ImageView) findViewById(R.id.btn_ac_inc);
		
		mModeHot = (ImageView) findViewById(R.id.btn_mode_hot);
		mModeCold = (ImageView) findViewById(R.id.btn_mode_cold);
		mModeDry = (ImageView) findViewById(R.id.btn_mode_dry);
		mModeWind = (ImageView) findViewById(R.id.btn_mode_wind);
		
		mWindHigh = (ImageView) findViewById(R.id.btn_wind_high);
		mWindMid = (ImageView) findViewById(R.id.btn_wind_mid);
		mWindLow = (ImageView) findViewById(R.id.btn_wind_low);
		mTypeIcon=(ImageView) findViewById(R.id.ac_type_icon);
		
		mAppName= (TextView) findViewById(R.id.txtview_ac_ctrl);
	    mACDec.setOnClickListener(this);
	    mACInc.setOnClickListener(this);
	    mModeHot.setOnClickListener(this);
	    mModeCold.setOnClickListener(this);
	    mModeDry.setOnClickListener(this);
	    mModeWind.setOnClickListener(this);
	    mWindHigh.setOnClickListener(this);
	    mWindMid.setOnClickListener(this);
	    mWindLow.setOnClickListener(this);
	    
		mBtnSW=(SlideSwitch) findViewById(R.id.ac_slideswitch);
		mBtnSW.setOnStateChangedListener(new OnStateChangedListener() {
			
			@Override
			public void onStateChanged(boolean state) {
				// TODO Auto-generated method stub
				
				if(state==true){
					Toast.makeText(Activity_AC_Control.this, "OPEN", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(Activity_AC_Control.this, "CLOSE", Toast.LENGTH_SHORT).show();
					
				}
			}
		});
	
		Intent itent=getIntent();
		mAppName.setText(getResources().getString(R.string.controlcenter_mainmenu_ac)+":"+itent.getStringExtra("TITLE_NAME"));
		strText=itent.getStringExtra("TITLE_NAME");
		List<ACEntity> mList;

		mList = dBManager.select_AC(strText);
		

		if (mList.isEmpty()) {
			ToastUtils.show(Activity_AC_Control.this, getResources().getString(R.string.target_not_exist));
			return;
			
		} else {
			address=mList.get(0).getAddress();
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
