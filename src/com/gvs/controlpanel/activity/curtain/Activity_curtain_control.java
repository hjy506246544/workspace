package com.gvs.controlpanel.activity.curtain;
import greendao.DBHelper;
import greendao.bean.CurtainEntity;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 窗帘主界面
 * @author hjy
 * 2016-0630
 */
public class Activity_curtain_control extends Activity implements
		OnClickListener {
	ImageView mBtnOpen,mBtnstop,mBtnClose,mTypeIcon;
    TextView  mAppName;
	public Header header;
    String strText;
    private DBHelper dBManager;
    private int address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curtain_control);
		dBManager = DBHelper.getInstance(this);
		mTypeIcon=(ImageView) findViewById(R.id.curtain_type_icon);
		mBtnOpen = (ImageView) findViewById(R.id.blinds_open);
		mBtnstop = (ImageView) findViewById(R.id.blinds_stop);
		mBtnClose = (ImageView) findViewById(R.id.blinds_close);
		//mAppName= (TextView) findViewById(R.id.txtview_curtain_ctrl);
		header = (Header) findViewById(R.id.activity_curtain_header);
		mBtnOpen.setOnClickListener(this);
		mBtnstop.setOnClickListener(this);
		mBtnClose.setOnClickListener(this);
		Intent itent=getIntent();
		header.setTitle(getResources().getString(R.string.curtain)+itent.getStringExtra("TITLE_NAME"));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				Activity_curtain_control.this.finish();
			}
		});

//		Intent itent=getIntent();
//		mAppName.setText(getResources().getString(R.string.curtain)+itent.getStringExtra("TITLE_NAME"));
		strText=itent.getStringExtra("TITLE_NAME");
		List<CurtainEntity> mList;

		mList = dBManager.select(strText);


		if (mList.isEmpty()) {
			ToastUtils.show(Activity_curtain_control.this, getResources().getString(R.string.target_not_exist));
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

}
