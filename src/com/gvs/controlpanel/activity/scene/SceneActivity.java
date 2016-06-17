package com.gvs.controlpanel.activity.scene;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.db.MySQLiteHelper;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.util.WriteToSD;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenu;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuCreator;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuItem;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuListView;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
/**
 * 场景主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class SceneActivity extends FragmentActivityBase{
	public Header header;
	private ImageView backiv;
	private Button bcbtn;
	static Context context;
	private SwipeMenuListView listView;
	private MySQLiteHelper dbHelper;// 数据库工具类
	private List<Map<String, Object>> totaList = null;
	private SimpleAdapter adapter = null;
	private TextView textView_main_emptyInfo;
	private Dialog dialog;
	/*2016-6-6 hjy gai
	private SceneAdapter sceneAdapter;
	private List listitem = new ArrayList();
	// 设置标题
	private String[] title = new String[] {
			"晨起模式", "聚餐模式", "自定义场景"};
	*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sceneactivity);
		initView();
		initData();
		initListener();
		//2016-6-3
		//WriteToSD.write(SceneActivity.this);获取单个文件夹中的数据
		//读取assets目录下的所有图片
		WriteToSD.getAsset(SceneActivity.this);
    }

    /**
     * 初始化listview信息
     */
	private void getListItems(){
		// 将上述资源转化为list集合
		/*
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            //map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
        */

		// 查询数据库中30条数据 并以id倒序排列
		totaList = dbHelper.selectList(
						"select _id, scenename from db_controlpanel order by _id desc limit 0,30",null);
		adapter = new SimpleAdapter(this, totaList, R.layout.scene_listitem,
				new String[] { "scenename"}, new int[] {R.id.nametv});
	}

    private void initData() {
		// 创建数据库和表 执行完构造方法后会执行onCreate中的db.exec方法 创建表
		dbHelper = new MySQLiteHelper(this);
//    	header.setTitle(getResources().getString(R.string.scene_title));
//
//		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				SceneActivity.this.finish();
//			}
//		});
		getListItems();
		// 给ListView 设置适配器
		listView.setAdapter(adapter);
		listView.setEmptyView(textView_main_emptyInfo);// 无数据时显示此View
		if(totaList.size()<1){
			bcbtn.setVisibility(View.VISIBLE);
			Log.e("totaList.size()", "totaList.size()"+totaList.size());
		}else {
			bcbtn.setVisibility(View.GONE);
			Log.e("totaList.size()2", "totaList.size()2"+totaList.size());
		}
//      sceneAdapter = new SceneAdapter(SceneActivity.this,listitem);//创建适配器
//		listView.setAdapter(sceneAdapter);
	}

	private void initListener() {
		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SceneActivity.this.finish();
			}
		});

		bcbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SceneActivity.this,AddSceneActivity.class);
				startActivity(intent);
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if("1".equals(totaList.get(position).get("_id").toString())){
					Intent intent = new Intent(SceneActivity.this,AddSceneActivity.class);
					startActivity(intent);
				}else {
					SceneActivity.this.finish();
				}
			}
		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if("1".equals(totaList.get(position).get("_id").toString())){
				    ToastUtils.show(SceneActivity.this, "自定义模式不可修改！");
				}else {
					Intent intent = new Intent(SceneActivity.this,UpdateSceneActivity.class);
					intent.putExtra("id", totaList.get(position).get("_id").toString()+"");
					intent.putExtra("scenename", totaList.get(position).get("scenename").toString()+"");
					Log.e("id", "id="+totaList.get(position).get("_id").toString());
					Log.e("scenename", "scenename="+totaList.get(position).get("scenename").toString());
					startActivity(intent);
				}
				return true;
			}
		});

		// step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(SceneActivity.this);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
				switch (index) {
	              case 0:
	  				  if("1".equals(totaList.get(position).get("_id").toString())
	  						  || "2".equals(totaList.get(position).get("_id").toString())
	  						  || "3".equals(totaList.get(position).get("_id").toString())
	  						  || "4".equals(totaList.get(position).get("_id").toString())
	  				  ){
	  					  ToastUtils.show(SceneActivity.this, "该项不可删除！");
	  				  }else {
	  					  showDelDialog(position);
					  }
	                  break;
	            }
			}
		});
	}

	/**
	 * 删除
	 * 自定义弹出框
	 * 2016-6-6
	 * hjy
	 */
	private void showDelDialog(final int position) {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.scene_del_dialog);
	    Button delbtn = (Button) dialog.getWindow().findViewById(R.id.delbtn);
	    delbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean flag = dbHelper.execData(
						"delete from db_controlpanel where _id=?",
						new Object[] { totaList.get(position).get("_id").toString() });
			    if (!flag) {
					ToastUtils.show(SceneActivity.this, "删除失败！");
			    }else {
				    fillListView();
				    ToastUtils.show(SceneActivity.this, "删除成功！");
				    dialog.dismiss();
			    }
			}
		});
	    dialog.setCancelable(true);
		dialog.show();
	}

	private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

	@Override
    public void onResume() {
        super.onResume();
        fillListView();
    }

	/**
	 *
	 * 执行操作后 更新当前ListView的界面
	 */
	public void fillListView() {
		totaList.clear();
		totaList.addAll(dbHelper.selectList(
						"select _id,scenename from db_controlpanel order by _id desc limit 30",null));
		adapter.notifyDataSetChanged();
	}

    private void initView() {
		//header = (Header) findViewById(R.id.header);
		listView = (SwipeMenuListView) findViewById(R.id.listview);
		textView_main_emptyInfo = (TextView) findViewById(R.id.textView_main_emptyInfo);
		backiv = (ImageView) findViewById(R.id.backiv);
		bcbtn = (Button) findViewById(R.id.bcbtn);
	}
}
