package com.gvs.controlpanel.activity.scene;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.SceneaddListAdapter;
import com.gvs.controlpanel.adapter.SceneaddListAdapter.ListItemView;
import com.gvs.controlpanel.db.MySQLiteHelper;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 修改场景主界面
 * 2016-5-24
 * @author hjy
 *
 */
public class UpdateSceneActivity extends FragmentActivityBase {
	public Header header;
	private ListView scenelist;
	private SceneaddListAdapter sceneaddListAdapter;
	private ImageView backiv;
	private Button bcbtn;
	public EditText scenenameet;
	// 设置适配器的图片资源
    private int[] imgiv = new int[] {
            R.drawable.icon_main_light, R.drawable.icon_main_cl,
            R.drawable.icon_main_kt, R.drawable.icon_main_dsj,
            R.drawable.icon_main_jtyy, R.drawable.icon_main_bgmusic,
            R.drawable.icon_main_afjk};
    // 设置标题
    private String[] nametv = new String[] {
    		"灯光", "窗帘", "空调", "电视机", "家庭影院", "背景音乐", "安防监控"};
    private List listitem = new ArrayList();
	private int positions;
	private MySQLiteHelper dbHelper;// 数据库工具类
	private List<Map<String, Object>> totaList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sceneupdate_activity);
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
            map.put("nametv", nametv[i]);
            listitem.add(map);
        }
	}

    private void initData() {
		scenenameet.setText(getIntent().getStringExtra("scenename"));
		// 创建数据库和表 执行完构造方法后会执行onCreate中的db.exec方法 创建表
		dbHelper = new MySQLiteHelper(this);
		getListItems();
		sceneaddListAdapter = new SceneaddListAdapter(UpdateSceneActivity.this,listitem);//创建适配器
		scenelist.setAdapter(sceneaddListAdapter);
	}

	private void initListener() {

		scenelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				positions = position;
				ListItemView  listItemView = (ListItemView) view.getTag();
				listItemView.dxiv.toggle();
				final CheckBox radio=(CheckBox) view.findViewById(R.id.dxiv);
				listItemView.dxiv = radio;
				sceneaddListAdapter.getSubjectItemMap().put(String.valueOf(position), radio.isChecked());
				sceneaddListAdapter.notifyDataSetChanged();
			}
		});

		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UpdateSceneActivity.this.finish();
			}
		});

		bcbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String id = getIntent().getStringExtra("id");
				Log.e("id2", "id2="+id);
				String scenename = scenenameet.getText().toString();
				boolean flag = dbHelper.execData(
						"update db_controlpanel set scenename=? where _id=?",
						new Object[] { scenename, id });
				if (!flag) {
					ToastUtils.show(UpdateSceneActivity.this, "修改失败！");
				}
				ToastUtils.show(UpdateSceneActivity.this, "修改成功！");
				UpdateSceneActivity.this.finish();
			}
		});
	}

    private void initView() {
		scenelist = (ListView) findViewById(R.id.activity_updatescene_scenelist);
		backiv = (ImageView) findViewById(R.id.activity_updatescene_backiv);
		bcbtn = (Button) findViewById(R.id.activity_updatescene_bcbtn);
		scenenameet = (EditText) findViewById(R.id.activity_updatescene_scenenameet);
	}
}
