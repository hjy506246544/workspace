package com.gvs.controlpanel.activity.set;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.util.SkinSettingManager;
import com.gvs.controlpanel.widget.Header;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/**
 * 背景图替换界面
 * 2016-5-17
 * @author hjy
 *
 */
public class BjtActivity extends FragmentActivityBase implements OnClickListener {
	private SkinSettingManager mSettingManager;
	private ImageView /*iv1,iv2,*/iv3,iv4,/*iv5,iv6,iv7,iv8,iv9,iv10,iv11,iv12,iv13,iv14,*/iv15,iv16;
	public Header header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bjtactivity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	//初始化皮肤
		mSettingManager=new SkinSettingManager(this);
		mSettingManager.initSkins();
		/*
		iv1=(ImageView) findViewById(R.id.imageView1);
		iv1.setOnClickListener(BjtActivity.this);
		iv2=(ImageView) findViewById(R.id.imageView2);
		iv2.setOnClickListener(BjtActivity.this);
		*/
		iv3=(ImageView) findViewById(R.id.imageView3);
		iv3.setOnClickListener(BjtActivity.this);
		iv4=(ImageView) findViewById(R.id.imageView4);
		iv4.setOnClickListener(BjtActivity.this);
		/*
		iv5=(ImageView) findViewById(R.id.imageView5);
		iv5.setOnClickListener(BjtActivity.this);
		iv6=(ImageView) findViewById(R.id.imageView6);
		iv6.setOnClickListener(BjtActivity.this);
		iv7=(ImageView) findViewById(R.id.imageView7);
		iv7.setOnClickListener(BjtActivity.this);
		iv8=(ImageView) findViewById(R.id.imageView8);
		iv8.setOnClickListener(BjtActivity.this);
		iv9=(ImageView) findViewById(R.id.imageView9);
		iv9.setOnClickListener(BjtActivity.this);
		iv10=(ImageView) findViewById(R.id.imageView10);
		iv10.setOnClickListener(BjtActivity.this);
		iv11=(ImageView) findViewById(R.id.imageView11);
		iv11.setOnClickListener(BjtActivity.this);
		iv12=(ImageView) findViewById(R.id.imageView12);
		iv12.setOnClickListener(BjtActivity.this);
		iv13=(ImageView) findViewById(R.id.imageView13);
		iv13.setOnClickListener(BjtActivity.this);
		iv14=(ImageView) findViewById(R.id.imageView14);
		iv14.setOnClickListener(BjtActivity.this);
		*/
		iv15=(ImageView) findViewById(R.id.imageView15);
		iv15.setOnClickListener(BjtActivity.this);
		iv16=(ImageView) findViewById(R.id.imageView16);
		iv16.setOnClickListener(BjtActivity.this);
	}

    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		/*
		case R.id.imageView1:
			mSettingManager.toggleSkins(0);
			break;
		case R.id.imageView2:
			mSettingManager.toggleSkins(1);
			break;
		*/
		case R.id.imageView3:
			mSettingManager.toggleSkins(0);
			break;
		case R.id.imageView4:
			mSettingManager.toggleSkins(1);
			break;
			/*
		case R.id.imageView5:
			mSettingManager.toggleSkins(4);
			break;
		case R.id.imageView6:
			mSettingManager.toggleSkins(5);
			break;
		case R.id.imageView7:
			mSettingManager.toggleSkins(6);
			break;
		case R.id.imageView8:
			mSettingManager.toggleSkins(7);
			break;
		case R.id.imageView9:
			mSettingManager.toggleSkins(8);
			break;
		case R.id.imageView10:
			mSettingManager.toggleSkins(9);
			break;
		case R.id.imageView11:
			mSettingManager.toggleSkins(10);
			break;
		case R.id.imageView12:
			mSettingManager.toggleSkins(11);
			break;
		case R.id.imageView13:
			mSettingManager.toggleSkins(12);
			break;
		case R.id.imageView14:
			mSettingManager.toggleSkins(13);
			break;
			 */
		case R.id.imageView15:
			mSettingManager.toggleSkins(2);
			break;
		case R.id.imageView16:
			mSettingManager.toggleSkins(3);
			break;
		}
	}

	private void initListener() {
		header.setTitle(getResources().getString(R.string.bjt_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				BjtActivity.this.finish();
			}
		});
	}

    private void initView() {
		header = (Header) findViewById(R.id.bjt_activity_header);
	}
}
