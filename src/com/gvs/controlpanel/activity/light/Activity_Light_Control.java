package com.gvs.controlpanel.activity.light;

import greendao.CurtainEntity;
import greendao.DBHelper;
import greendao.LightEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.gvs.controlpanel.R;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
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

public class Activity_Light_Control extends Activity implements
		OnClickListener,  OnSeekBarChangeListener {
	public Header header;
	ImageView mBtnDec;
	ImageView mBtnInc;
	ImageView mTypeIcon;
	ImageView mBtnRGB;
	SeekBar prb;
	LinearLayout mLightCtrlErea;
    TextView  mAppName;
    String strText;
    private DBHelper dBManager;
    private int address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SlideSwitch mBtnSW;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light_control);
		dBManager = DBHelper.getInstance(this);
		mTypeIcon=(ImageView) findViewById(R.id.light_type_icon);
		mBtnDec = (ImageView) findViewById(R.id.btn_light_dec);
		mBtnInc = (ImageView) findViewById(R.id.btn_light_inc);
		prb=(SeekBar) findViewById(R.id.seekbar_light_bar);
		//mAppName= (TextView) findViewById(R.id.txtview_light_ctrl);
	    mLightCtrlErea=(LinearLayout)findViewById(R.id.light_ctrl_erea);
		header = (Header) findViewById(R.id.activity_light_header);
		mBtnDec.setOnClickListener(this);
		mBtnInc.setOnClickListener(this);
		mBtnSW=(SlideSwitch) findViewById(R.id.light_slideswitch);
		Intent itent=getIntent();
		header.setTitle(getResources().getString(R.string.light_title)+":"+itent.getStringExtra("TITLE_NAME"));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				Activity_Light_Control.this.finish();
			}
		});
		mBtnSW.setOnStateChangedListener(new OnStateChangedListener() {

			@Override
			public void onStateChanged(boolean state) {
				// TODO Auto-generated method stub

				if(state==true){
					mLightCtrlErea.setVisibility(View.VISIBLE);
					Toast.makeText(Activity_Light_Control.this, "OPEN", Toast.LENGTH_SHORT).show();
				}else{
					mLightCtrlErea.setVisibility(View.GONE);
					Toast.makeText(Activity_Light_Control.this, "CLOSE", Toast.LENGTH_SHORT).show();

				}
			}
		});
		prb.setOnClickListener(this);
		prb.setOnSeekBarChangeListener(this);
		prb.setMax(100);
//		Intent itent=getIntent();
//		mAppName.setText(getResources().getString(R.string.light_title)+":"+itent.getStringExtra("TITLE_NAME"));
		strText=itent.getStringExtra("TITLE_NAME");
		List<LightEntity> mList;

		mList = dBManager.select_Light(strText);


		if (mList.isEmpty()) {
			ToastUtils.show(Activity_Light_Control.this, getResources().getString(R.string.target_not_exist));
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
