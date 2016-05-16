package com.gvs.hwcontrol.activity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.hwcontrol.R;
import com.gvs.hwcontrol.adapter.GridViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 我的应用主界面
 * 2016-5-13
 * @author hjy
 *
 */
public class WdyyFragment extends Fragment {
	private MainActivity mainActivity;
	private GridView listgv;
	private TextView timetv;
	private ImageView setiv;
	private GridViewAdapter gridViewAdapter;
	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_dsj_, R.drawable.main_bx_,
            R.drawable.main_cl_, R.drawable.main_dsj_,
            R.drawable.main_bx_, R.drawable.main_cl_,
            R.drawable.main_dsj_, R.drawable.main_bx_,
            R.drawable.main_cl_};
    // 设置标题
    private String[] title = new String[] {
    		"电视机", "冰箱", "窗帘",
    		"电视机", "冰箱", "窗帘",
    		"电视机", "冰箱", "窗帘"};
    // 设置状态
    private String[] state = new String[] {
    		"状态：off", "状态：on", "状态：off",
    		"状态：on", "状态：off", "状态：on",
    		"状态：off", "状态：on", "状态：off"};
    // 设置信息
    private String[] info = new String[] {
    		"信息：该产品···", "信息：该产品好东西", "信息：该产品很好",
    		"信息：该产品不错", "信息：该产品···", "信息：该产品···",
    		"信息：该产品呵呵", "信息：该产品···", "信息：该产品···"};
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
    	timetv = (TextView) view.findViewById(R.id.timetv);
		listgv=(GridView) view.findViewById(R.id.listgv);
		setiv = (ImageView) view.findViewById(R.id.setiv);
	}

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        InitListener();
    }

    private void InitListener() {
    	//GridView list事件
    	listgv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
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
				}else if (position==5) {
					Intent intent6  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent6);
				}else if (position==6) {
					Intent intent7  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent7);
				}else if (position==7) {
					Intent intent8  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent8);
				}else if (position==8) {
					Intent intent9  = new Intent(mainActivity,TestActivity.class);
					startActivity(intent9);
				}
			}
		});

    	/**
    	 * 设置按钮
    	 * 打开手机自带系统设置界面
    	 */
    	setiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//打开手机自带系统设置界面
				//startActivity(new Intent(Settings.ACTION_SETTINGS));
				startActivity(new Intent(mainActivity,SetActivity.class));

			}
		});
	}

	private void initData() {
		// 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            map.put("state", state[i]);
            map.put("info", info[i]);
            listitem.add(map);
        }

        //获取当前系统时间
		Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		int mHour = c.get(Calendar.HOUR_OF_DAY);
		int mMinute = c.get(Calendar.MINUTE);
		timetv.setText(mYear +
        "年 " + mMonth +
        "月 " + mDay +
        "日  " + mHour +
        ": " + mMinute);

		gridViewAdapter=new GridViewAdapter(mainActivity,listitem);
		listgv.setAdapter(gridViewAdapter);
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
