package com.gvs.controlpanel.activity.curtain;
import java.util.ArrayList;
import java.util.List;

import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.Curtain_GridViewAdapter;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
/**
 * 窗帘主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class CurtainActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private GridView curtaingv;
	private Curtain_GridViewAdapter gridViewAdapter;
    private List listitem = new ArrayList();
	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_light6, R.drawable.main_cl6,
            R.drawable.main_kt6, R.drawable.main_dsj6,
            R.drawable.main_jtyy6, R.drawable.main_bgmusic6,
            R.drawable.main_scene6, R.drawable.main_afjk6,R.drawable.main_set6};
    // 设置标题
    private String[] title = new String[] {
    		"灯光", "窗帘", "空调",
    		"电视机", "家庭影院", "背景音乐",
    		"场景", "安防监控", "设置"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curtainactivity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.curtain_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				CurtainActivity.this.finish();
			}
		});
	}

	private void initListener() {

	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
		curtaingv = (GridView) findViewById(R.id.curtaingv);
	}
}
