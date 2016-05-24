package com.gvs.controlpanel.activity.scene;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.SceneaddListAdapter;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
/**
 * 添加场景主界面
 * 2016-5-23
 * @author hjy
 *
 */
public class AddSceneActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private ListView scenelist;
	private SceneaddListAdapter sceneaddListAdapter;
	private ImageView backiv;
	private Button bcbtn;
	// 设置适配器的图片资源
    private int[] imgiv = new int[] {
            R.drawable.main_light, R.drawable.main_cl,
            R.drawable.main_kt, R.drawable.main_dsj,
            R.drawable.main_jtyy, R.drawable.main_bgmusic,
            R.drawable.main_afjk};
    // 设置适配器的图片资源
    private int[] dxiv = new int[] {
            R.drawable.scene_checkfalse, R.drawable.scene_checkfalse,
            R.drawable.scene_checkfalse, R.drawable.scene_checkfalse,
            R.drawable.scene_checkfalse, R.drawable.scene_checkfalse,
            R.drawable.scene_checkfalse};
    // 设置标题
    private String[] nametv = new String[] {
    		"灯光", "窗帘", "空调", "电视机", "家庭影院", "背景音乐", "安防监控"};
    private List listitem = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sceneadd_activity);
		initView();
		initData();
		initListener();
    }

    /**
     * 初始化listview信息
     */
	private void getListItems(){
		// 将上述资源转化为list集合
        for (int i = 0; i < imgiv.length; i++) {
            Map map = new HashMap();
            map.put("imgiv", imgiv[i]);
            map.put("dxiv", dxiv[i]);
            map.put("nametv", nametv[i]);
            listitem.add(map);
        }
	}

    private void initData() {
//    	header.setTitle(getResources().getString(R.string.scenezdy_title));
//
//		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				AddSceneActivity.this.finish();
//			}
//
//		});
//
//		header.setRightImageViewRes(R.drawable.scenecreate_, new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				ToastUtils.show(AddSceneActivity.this, "创建成功！");
//				AddSceneActivity.this.finish();
//			}
//		});
//		header.setTextViewRes(R.string.scenezdy_ljcj, new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				ToastUtils.show(AddSceneActivity.this, "创建成功2！");
//			}
//		});
		getListItems();
		sceneaddListAdapter = new SceneaddListAdapter(AddSceneActivity.this,listitem);//创建适配器
		scenelist.setAdapter(sceneaddListAdapter);
	}

	private void initListener() {
		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddSceneActivity.this.finish();
			}
		});

		bcbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ToastUtils.show(AddSceneActivity.this, "创建成功！");
				AddSceneActivity.this.finish();
			}
		});
	}

    private void initView() {
		//header = (Header) findViewById(R.id.header);
		scenelist = (ListView) findViewById(R.id.scenelist);
		backiv = (ImageView) findViewById(R.id.backiv);
		bcbtn = (Button) findViewById(R.id.bcbtn);
	}
}
