package com.gvs.controlpanel.activity.hometheatre;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 家庭影院主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class HomeTheatreActivity extends FragmentActivityBase {
	public Header header;
	static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hometheatre_activity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.hometheatre_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				HomeTheatreActivity.this.finish();
			}
		});
	}

	private void initListener() {

	}

    private void initView() {
		header = (Header) findViewById(R.id.activity_hometheatre_header);
	}
}
