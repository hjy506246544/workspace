package com.gvs.controlpanel.activity.tv;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 电视机主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class TVActivity extends FragmentActivityBase {
	public Header header;
	static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tvactivity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.tv_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				TVActivity.this.finish();
			}
		});
	}

	private void initListener() {

	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
	}
}
