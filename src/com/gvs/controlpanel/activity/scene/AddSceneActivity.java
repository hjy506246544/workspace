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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
/**
 * 添加场景主界面
 * 2016-5-23
 * @author hjy
 *
 */
public class AddSceneActivity extends FragmentActivityBase {
	public Header header;
	private ListView scenelist;
	private SceneaddListAdapter sceneaddListAdapter;
	private ImageView backiv;
	private Button bcbtn;
	private EditText scenenameet;
	private TextView textView_main_emptyInfo;
	// 设置适配器的图片资源
    private int[] imgiv = new int[] {
            R.drawable.main_light, R.drawable.main_cl,
            R.drawable.main_kt, R.drawable.main_dsj,
            R.drawable.main_jtyy, R.drawable.main_bgmusic,
            R.drawable.main_afjk};
    // 设置标题
    private String[] nametv = new String[] {
    		"灯光", "窗帘", "空调", "电视机", "家庭影院", "背景音乐", "安防监控"};
    //private List<Map<String, Object>> listitem = null;
    private List listitem = new ArrayList();
	private int positions;
	private MySQLiteHelper dbHelper;// 数据库工具类
	private int img = R.drawable.main_light;
	private SimpleAdapter adapter = null;

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
            map.put("nametv", nametv[i]);
            listitem.add(map);
        }
        /*
		listitem = dbHelper.selectList(
				"select _id,scenename, typename from db_controlpanel order by _id desc limit 0,30",null);
//		View view = getLayoutInflater().inflate(R.layout.sceneadd_item,null);
//		TextView nametv = (TextView) view.findViewById(R.id.nametv);
//		ImageView imgiv = (ImageView) view.findViewById(R.id.imgiv);
//		String typename = nametv.getText().toString();
//		boolean flag = dbHelper.execData(
//				"insert into db_controlpanel(typename) values('灯光')",new String[]{typename});
//		if(!flag){
//			nametv.setText("");
//		}else {
//			fillListView();
//		}
		adapter = new SimpleAdapter(this, listitem, R.layout.sceneadd_item,
				new String[] { "typename"}, new int[] {R.id.nametv});
		 */
	}

    private void initData() {
		// 创建数据库和表 执行完构造方法后会执行onCreate中的db.exec方法 创建表
		dbHelper = new MySQLiteHelper(this);
		getListItems();
//		scenelist.setAdapter(adapter);
//		scenelist.setEmptyView(textView_main_emptyInfo);// 无数据时显示此View
		sceneaddListAdapter = new SceneaddListAdapter(AddSceneActivity.this,listitem);//创建适配器
		scenelist.setAdapter(sceneaddListAdapter);
	}

    /**
	 *
	 * 执行操作后 更新当前ListView的界面
	 */
	public void fillListView() {
		listitem.clear();
		listitem.addAll(dbHelper.selectList(
						"select _id,scenename,typename from db_controlpanel order by _id desc limit 30",null));
		adapter.notifyDataSetChanged();
	}

	private void initListener() {
		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddSceneActivity.this.finish();
			}
		});

		scenelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				positions = position;
				ListItemView  listItemView = (ListItemView) view.getTag();
				listItemView.dxiv.toggle();
				final CheckBox radio=(CheckBox) view.findViewById(R.id.dxiv);
				listItemView.dxiv = radio;
				sceneaddListAdapter.getSubjectItemMap().put(String.valueOf(position), radio.isChecked());
				Log.e("position", "position="+position);
				listitem.get(position).equals("");
				sceneaddListAdapter.notifyDataSetChanged();
			}
		});

		bcbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String scenename = scenenameet.getText().toString();
				if(TextUtils.isEmpty(scenename)){
					ToastUtils.show(AddSceneActivity.this,"请输入名称");
					return ;
				}
				boolean flag=dbHelper.execData("insert into db_controlpanel(scenename) values(?)", new String[]{scenename});
				if(!flag){
					scenenameet.setText("");
				}
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
		scenenameet = (EditText) findViewById(R.id.scenenameet);
		textView_main_emptyInfo = (TextView) findViewById(R.id.textView_main_emptyInfo);
	}
}
