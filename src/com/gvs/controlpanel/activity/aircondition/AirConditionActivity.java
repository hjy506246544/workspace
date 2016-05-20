package com.gvs.controlpanel.activity.aircondition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.Main_GridViewAdapter;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
/**
 * 空调主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class AirConditionActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private GridView airconditiongv;
	private Main_GridViewAdapter gridViewAdapter;

	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_dsj_, R.drawable.main_afjk_,
            R.drawable.main_cl_};
    // 设置标题
    private String[] title = new String[] {
    		"空调", "空调", "空调"};
    private List listitem = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aircondition_activity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.aircondition_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				AirConditionActivity.this.finish();
			}
		});
		// 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
		gridViewAdapter=new Main_GridViewAdapter(AirConditionActivity.this,listitem);
		airconditiongv.setAdapter(gridViewAdapter);
	}

	private void initListener() {

	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
		airconditiongv=(GridView) findViewById(R.id.airconditiongv);
	}
}
