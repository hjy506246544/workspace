package com.gvs.controlpanel.activity.backgroundmusic;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 背景音乐主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class BgMusicActivity extends FragmentActivityBase {
	public Header header;
	static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bgmusic_activity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.bgmusic_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				BgMusicActivity.this.finish();
			}
		});
	}

	private void initListener() {

	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
	}
}
