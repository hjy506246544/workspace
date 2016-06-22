package com.gvs.controlpanel.activity.securitymonitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.simpleframework.xml.convert.Convert;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobQuery.CachePolicy;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.R.integer;
import com.gvs.controlpanel.activity.base.FragmentBase;
import com.gvs.controlpanel.adapter.MonitorPreview_Adapter;
import com.gvs.controlpanel.bean.Camera;
import com.gvs.controlpanel.util.ToastUtils;

import android.app.Activity;
import android.content.Intent;
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
    private List<Camera> listitem = new ArrayList<Camera>();
    private MonitorPreview_Adapter monitorPreview_Adapter;
	private TextView textView_main_emptyInfo;
	// 设置标题
	private String[] title = new String[] {
			"客厅", "厨房", "走廊"};
	private int pos;

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
		/*
		// 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            //map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
        */
		final BmobQuery<Camera> bmobQuery = new BmobQuery<Camera>();
		//bmobQuery.addWhereEqualTo("age", 25);
//		bmobQuery.setLimit(10);
//		bmobQuery.order("createdAt");
		//先判断是否有缓存
//		boolean isCache = bmobQuery.hasCachedResult(securityMonitorActivity,Camera.class);
//		if(isCache){
//			bmobQuery.setCachePolicy(CachePolicy.CACHE_ELSE_NETWORK);	// 先从缓存取数据，如果没有的话，再从网络取。
//		}else{
//			bmobQuery.setCachePolicy(CachePolicy.NETWORK_ELSE_CACHE);	// 如果没有缓存的话，则先从网络中取
//		}
		bmobQuery.findObjects(securityMonitorActivity, new FindListener<Camera>() {

			@Override
			public void onSuccess(List<Camera> object) {
				Log.e("","查询成功：共"+object.size()+"条数据。");
				for (Camera person : object) {
					Log.e("", "Name = "+person.getDeviceName());
					Camera camera = new Camera();
					String name = person.getDeviceName();
					camera.setDeviceName(name);
					object.add(camera);
					//listitem.addAll(object);
					//Log.d("", "ObjectId = "+person.getObjectId());
				}
				listitem.addAll(object);
				//monitorPreview_Adapter.notifyDataSetChanged();
			}

			@Override
			public void onError(int code, String msg) {
				Log.e("","查询失败："+msg);
			}
		});
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
				pos = position;
			}
		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent(securityMonitorActivity,UpdateMonitorPreviewActivity.class);
				intent.putExtra("devicename", listitem.get(position).getDeviceName());
//				Log.e("id", "id="+totaList.get(position).get("_id").toString());
//				Log.e("scenename", "scenename="+totaList.get(position).get("scenename").toString());
				startActivity(intent);
				return true;
			}
		});
	}

	private void initData() {
		getListItems();
		// 给ListView 设置适配器
		monitorPreview_Adapter = new MonitorPreview_Adapter(securityMonitorActivity, listitem);
		listView.setAdapter(monitorPreview_Adapter);
	}

	@Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getListItems();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 删除摄像头
     * @param position
     * @param listitem2
     * 2016-6-21
     */
	public void deldelCamera(int position,Camera camera) {
		pos = position;
		Camera camera2 =new Camera();
		String p = Integer.toString(pos);
		camera2.setObjectId(p);
		Log.e("", "lost.getDeviceId()="+camera2.getDeviceId());
		Log.e("", "pos="+pos);
		Log.e("", "p="+p);
		camera2.delete(securityMonitorActivity, new DeleteListener() {

			@Override
			public void onSuccess() {
				ToastUtils.show(securityMonitorActivity, "删除第"+pos+"成功");
			}

			@Override
			public void onFailure(int code, String arg0) {
				ToastUtils.show(securityMonitorActivity, "删除第"+pos+"失败");
			}
		});
	}
}
