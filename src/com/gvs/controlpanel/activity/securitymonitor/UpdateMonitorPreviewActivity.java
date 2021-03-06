package com.gvs.controlpanel.activity.securitymonitor;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 修改摄像头界面
 * 2016-6-16
 * @author hjy
 */
public class UpdateMonitorPreviewActivity extends FragmentActivityBase {
	public Header header;
	static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securitymonitor_monitorpreview_addactivity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.updatesxt_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdateMonitorPreviewActivity.this.finish();
			}
		});
	}

	private void initListener() {
	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
	}
}
