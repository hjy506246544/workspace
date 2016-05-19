package com.gvs.controlpanel.activity;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.util.SkinSettingManager;
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
	private ImageView iv1,iv2,iv3,iv4,iv5,iv6,backiv;

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
		iv1=(ImageView) findViewById(R.id.imageView1);
		iv1.setOnClickListener(BjtActivity.this);
		iv2=(ImageView) findViewById(R.id.imageView2);
		iv2.setOnClickListener(BjtActivity.this);
		iv3=(ImageView) findViewById(R.id.imageView3);
		iv3.setOnClickListener(BjtActivity.this);
		iv4=(ImageView) findViewById(R.id.imageView4);
		iv4.setOnClickListener(BjtActivity.this);
		iv5=(ImageView) findViewById(R.id.imageView5);
		iv5.setOnClickListener(BjtActivity.this);
		iv6=(ImageView) findViewById(R.id.imageView6);
		iv6.setOnClickListener(BjtActivity.this);
		backiv=(ImageView) findViewById(R.id.backiv);
		backiv.setOnClickListener(BjtActivity.this);
	}

    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageView1:
			mSettingManager.toggleSkins(0);
			break;
		case R.id.imageView2:
			mSettingManager.toggleSkins(1);
			break;
		case R.id.imageView3:
			mSettingManager.toggleSkins(2);
			break;
		case R.id.imageView4:
			mSettingManager.toggleSkins(3);
			break;
		case R.id.imageView5:
			mSettingManager.toggleSkins(4);
			break;
		case R.id.imageView6:
			mSettingManager.toggleSkins(5);
			break;
		case R.id.backiv:
			BjtActivity.this.finish();
			break;
		}
	}

	private void initListener() {

	}

    private void initView() {
	}
}
