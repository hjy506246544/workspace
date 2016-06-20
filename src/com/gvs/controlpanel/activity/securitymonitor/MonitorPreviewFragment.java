package com.gvs.controlpanel.activity.securitymonitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentBase;
import com.gvs.controlpanel.activity.scene.SceneActivity;
import com.gvs.controlpanel.activity.scene.UpdateSceneActivity;
import com.gvs.controlpanel.adapter.MonitorPreview_Adapter;
import com.gvs.controlpanel.db.MySQLiteHelper;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenu;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuCreator;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuItem;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuListView;
import com.gvs.controlpanel.widget.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
/**
 * 监控预览主界面
 * @author hjy
 * 2016-6-15
 */
public class MonitorPreviewFragment extends FragmentBase{
	private SecurityMonitorActivity securityMonitorActivity;
	private ImageView addiv;
	private ListView listView;
    private List listitem = new ArrayList();
    private MonitorPreview_Adapter monitorPreview_Adapter;
	private TextView textView_main_emptyInfo;
	// 设置标题
	private String[] title = new String[] {
			"客厅", "厨房", "走廊"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        securityMonitorActivity = (SecurityMonitorActivity) activity;
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.securitymonitor_monitorpreview_fragment, container, false);
        initView(view,savedInstanceState);
		return view;
	}

	private void initView(View view, Bundle savedInstanceState) {
		addiv = (ImageView) view.findViewById(R.id.addiv);
		listView = (ListView) view.findViewById(R.id.listview);
	}

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        InitListener();
    }

	/**
     * 初始化listview信息
     */
	private void getListItems(){
		// 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            //map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
	}

	private void InitListener() {
		addiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				OpenActivity(AddMonitorPreviewActivity.class);
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
			}
		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent(securityMonitorActivity,UpdateMonitorPreviewActivity.class);
//				intent.putExtra("id", listitem.get(position)+"");
//				intent.putExtra("scenename", listitem.get(position).get("scenename").toString()+"");
//				Log.e("id", "id="+totaList.get(position).get("_id").toString());
//				Log.e("scenename", "scenename="+totaList.get(position).get("scenename").toString());
				startActivity(intent);
				return true;
			}
		});
/*
		// step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(securityMonitorActivity);
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
	  				  //showDelDialog(position);
	                  break;
	            }
			}
		});
		*/
	}

	private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

	private void initData() {

		getListItems();
		// 给ListView 设置适配器
		monitorPreview_Adapter = new MonitorPreview_Adapter(securityMonitorActivity, listitem);
		listView.setAdapter(monitorPreview_Adapter);
		//listView.setEmptyView(textView_main_emptyInfo);// 无数据时显示此View
	}

	@Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
