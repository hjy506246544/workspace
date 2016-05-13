package com.gvs.hwcontrol.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.hwcontrol.R;
import com.gvs.hwcontrol.adapter.GridViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * 我的应用主界面
 * 2016-5-13
 * @author hjy
 *
 */
public class WdyyFragment extends Fragment {
	private MainActivity mainActivity;
	private GridView listgv;
	private GridViewAdapter gridViewAdapter;
	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher};
    // 设置标题
    private String[] title = new String[] {
    		"相机", "定位", "画笔", "视频", "声音",
    		"相机", "相机", "定位", "画笔", "视频",
             "声音", "相机","定位", "画笔", "视频",
             "声音","定位", "画笔", "视频", "声音",
             "相机", "定位"};
    private List listitem = new ArrayList();

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.wdyyactivity, container, false);
        initView(view,savedInstanceState);
		return view;
    }

    private void initView(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	//textView = (TextView) view.findViewById(R.id.zjth);
		listgv=(GridView) view.findViewById(R.id.listgv);
	}

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        InitListener();
    }

    private void InitListener() {
    	listgv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position==0){
					Intent intent  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent);
				}else if (position==1) {
					Intent intent2  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent2);
				}else if (position==2) {
					Intent intent3  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent3);
				}else if (position==3) {
					Intent intent4  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent4);
				}else if (position==4) {
					Intent intent5  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent5);
				}
			}
		});
	}

	private void initData() {
		// 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
		gridViewAdapter=new GridViewAdapter(mainActivity,listitem);
		listgv.setAdapter(gridViewAdapter);
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
