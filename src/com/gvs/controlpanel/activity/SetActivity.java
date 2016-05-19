package com.gvs.controlpanel.activity;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
/**
 * 设置界面
 * 2016-5-16
 * @author hjy
 *
 */
public class SetActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private ImageView backiv;
	private RelativeLayout xxrl,xxrl2,xxrl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setactivity);
		initView();
		initData();
		initListener();
    }

    private void initData() {

	}

	private void initListener() {
    	backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SetActivity.this.finish();
			}
		});

    	xxrl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SetActivity.this,BjtActivity.class));
			}
		});

    	xxrl2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SetActivity.this,TestActivity.class));
			}
		});

    	xxrl3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SetActivity.this,TestActivity.class));
			}
		});
	}

    private void initView() {
    	backiv = (ImageView) findViewById(R.id.backiv);
    	xxrl = (RelativeLayout) findViewById(R.id.xxrl);
    	xxrl2 = (RelativeLayout) findViewById(R.id.xxrl2);
    	xxrl3 = (RelativeLayout) findViewById(R.id.xxrl3);
	}
}
