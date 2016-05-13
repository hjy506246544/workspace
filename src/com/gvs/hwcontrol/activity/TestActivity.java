package com.gvs.hwcontrol.activity;
import com.gvs.hwcontrol.R;
import com.gvs.hwcontrol.activity.base.FragmentActivityBase;
import com.gvs.hwcontrol.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
/**
 *主界面
 * @author hjy
 *
 */
public class TestActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private ImageView backiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testactivity);
		initView();
//		initData();
		initListener();
    }

    private void initListener() {
    	backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TestActivity.this.finish();
			}
		});
	}

    private void initView() {
    	backiv = (ImageView) findViewById(R.id.backiv);
	}
}
