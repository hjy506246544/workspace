package com.gvs.controlpanel.activity.securitymonitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentBase;
import com.gvs.controlpanel.adapter.VideoPlayAdapter;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.FlowLayout;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 影像回放主界面
 * @author hjy
 * 2016-6-15
 */
public class VideoPlaybackFragment extends FragmentBase{
	private SecurityMonitorActivity securityMonitorActivity;
	private TextView allyeartv,liutv,wutv,sitv,santv,ertv,yitv;
	private TextView allmonthtv,januarytv,februarytv,marchtv,apriltv,maytv,junetv,julytv,augusttv,septembertv,octobertv,novembertv,decembertv;
	private FlowLayout flowLayout;
	private ListView listView;
	private VideoPlayAdapter videoPlayAdapter;
	private List listitem = new ArrayList();
	// 设置标题
	private String[] title = new String[] {
			"客厅的录影", "厨房的录影", "走廊的录影"};
	private String[] lxtimetv = new String[] {
			"2016-6-16 1小时50分", "2016-6-16 50分", "2016-6-16 10分"};
	// 设置适配器的图片资源
    private int[] image = new int[] {
            R.drawable.icon_afjk_logo, R.drawable.icon_afjk_logo,R.drawable.icon_afjk_logo};

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
        View view =  inflater.inflate(R.layout.securitymonitor_videoplay_fragment, container, false);
        initView(view,savedInstanceState);
		return view;
	}

	private void initView(View view, Bundle savedInstanceState) {
		listView = (ListView) view.findViewById(R.id.activity_securitymonitor_listview);
		allyeartv = (TextView) view.findViewById(R.id.activity_securitymonitor_allyeartv);
		liutv = (TextView) view.findViewById(R.id.activity_securitymonitor_liutv);
		wutv = (TextView) view.findViewById(R.id.activity_securitymonitor_wutv);
		sitv = (TextView) view.findViewById(R.id.activity_securitymonitor_sitv);
		santv = (TextView) view.findViewById(R.id.activity_securitymonitor_santv);
		ertv = (TextView) view.findViewById(R.id.activity_securitymonitor_ertv);
		yitv = (TextView) view.findViewById(R.id.activity_securitymonitor_yitv);
		allmonthtv = (TextView) view.findViewById(R.id.activity_securitymonitor_allmonthtv);
		januarytv = (TextView) view.findViewById(R.id.activity_securitymonitor_januarytv);
		februarytv = (TextView) view.findViewById(R.id.activity_securitymonitor_februarytv);
		marchtv = (TextView) view.findViewById(R.id.activity_securitymonitor_marchtv);
		apriltv = (TextView) view.findViewById(R.id.activity_securitymonitor_apriltv);
		maytv = (TextView) view.findViewById(R.id.activity_securitymonitor_maytv);
		junetv = (TextView) view.findViewById(R.id.activity_securitymonitor_junetv);
		julytv = (TextView) view.findViewById(R.id.activity_securitymonitor_julytv);
		augusttv = (TextView) view.findViewById(R.id.activity_securitymonitor_augusttv);
		septembertv = (TextView) view.findViewById(R.id.activity_securitymonitor_septembertv);
		octobertv = (TextView) view.findViewById(R.id.activity_securitymonitor_octobertv);
		novembertv = (TextView) view.findViewById(R.id.activity_securitymonitor_novembertv);
		decembertv = (TextView) view.findViewById(R.id.activity_securitymonitor_decembertv);
		flowLayout  = (FlowLayout) view.findViewById(R.id.activity_securitymonitor_flowLayout);
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
            map.put("title", title[i]);
            map.put("lxtimetv", lxtimetv[i]);
            map.put("image", image[i]);
            listitem.add(map);
        }
	}

	private void InitListener() {
		allmonthtv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getAllmonth();
			}
		});

		januarytv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getjanuary();
			}
		});

		februarytv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getfebruary();
			}
		});

		marchtv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getmarch();
			}
		});

		apriltv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getapril();
			}
		});

		maytv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getmay();
			}
		});

		junetv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getjune();
			}
		});

		julytv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getjuly();
			}
		});

		augusttv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getaugust();
			}
		});

		septembertv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getseptember();
			}
		});

		octobertv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getoctober();
			}
		});

		novembertv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getnovember();
			}
		});

		decembertv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getdecember();
			}
		});

		allyeartv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getAllyear();
			}
		});

		liutv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				allyeartv.setBackgroundResource(R.drawable.flag_02);
				liutv.setBackgroundResource(R.drawable.flag_03);
				wutv.setBackgroundResource(R.drawable.flag_02);
				sitv.setBackgroundResource(R.drawable.flag_02);
				santv.setBackgroundResource(R.drawable.flag_02);
				ertv.setBackgroundResource(R.drawable.flag_02);
				yitv.setBackgroundResource(R.drawable.flag_02);
				ToastUtils.show(securityMonitorActivity, "你点击了2016");
			}
		});

		wutv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				allyeartv.setBackgroundResource(R.drawable.flag_02);
				liutv.setBackgroundResource(R.drawable.flag_02);
				wutv.setBackgroundResource(R.drawable.flag_03);
				sitv.setBackgroundResource(R.drawable.flag_02);
				santv.setBackgroundResource(R.drawable.flag_02);
				ertv.setBackgroundResource(R.drawable.flag_02);
				yitv.setBackgroundResource(R.drawable.flag_02);
				ToastUtils.show(securityMonitorActivity, "你点击了2015");
			}
		});

		sitv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				allyeartv.setBackgroundResource(R.drawable.flag_02);
				liutv.setBackgroundResource(R.drawable.flag_02);
				wutv.setBackgroundResource(R.drawable.flag_02);
				sitv.setBackgroundResource(R.drawable.flag_03);
				santv.setBackgroundResource(R.drawable.flag_02);
				ertv.setBackgroundResource(R.drawable.flag_02);
				yitv.setBackgroundResource(R.drawable.flag_02);
				ToastUtils.show(securityMonitorActivity, "你点击了2014");
			}
		});

		santv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				allyeartv.setBackgroundResource(R.drawable.flag_02);
				liutv.setBackgroundResource(R.drawable.flag_02);
				wutv.setBackgroundResource(R.drawable.flag_02);
				sitv.setBackgroundResource(R.drawable.flag_02);
				santv.setBackgroundResource(R.drawable.flag_03);
				ertv.setBackgroundResource(R.drawable.flag_02);
				yitv.setBackgroundResource(R.drawable.flag_02);
				ToastUtils.show(securityMonitorActivity, "你点击了2013");
			}
		});

		ertv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				allyeartv.setBackgroundResource(R.drawable.flag_02);
				liutv.setBackgroundResource(R.drawable.flag_02);
				wutv.setBackgroundResource(R.drawable.flag_02);
				sitv.setBackgroundResource(R.drawable.flag_02);
				santv.setBackgroundResource(R.drawable.flag_02);
				ertv.setBackgroundResource(R.drawable.flag_03);
				yitv.setBackgroundResource(R.drawable.flag_02);
				ToastUtils.show(securityMonitorActivity, "你点击了2012");
			}
		});

		yitv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				allyeartv.setBackgroundResource(R.drawable.flag_02);
				liutv.setBackgroundResource(R.drawable.flag_02);
				wutv.setBackgroundResource(R.drawable.flag_02);
				sitv.setBackgroundResource(R.drawable.flag_02);
				santv.setBackgroundResource(R.drawable.flag_02);
				ertv.setBackgroundResource(R.drawable.flag_02);
				yitv.setBackgroundResource(R.drawable.flag_03);
				ToastUtils.show(securityMonitorActivity, "你点击了2011");
			}
		});

		flowLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ToastUtils.show(securityMonitorActivity, "你点击了````");
			}
		});
	}

	private void initData() {
		getListItems();
		videoPlayAdapter = new VideoPlayAdapter(securityMonitorActivity,listitem);//创建适配器
		listView.setAdapter(videoPlayAdapter);
	}

	public void getAllmonth(){
		allmonthtv.setBackgroundResource(R.drawable.flag_03);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了所有月份");
	}

	public void getjanuary(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_03);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了1月份");
	}

	public void getfebruary(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_03);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了2月份");
	}

	public void getmarch(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_03);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了3月份");
	}

	public void getapril(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_03);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了4月份");
	}

	public void getmay(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_03);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了5月份");
	}

	public void getjune(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_03);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了6月份");
	}

	public void getjuly(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_03);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了7月份");
	}

	public void getaugust(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_03);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了8月份");
	}

	public void getseptember(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_03);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了9月份");
	}

	public void getoctober(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_03);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了10月份");
	}

	public void getnovember(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_03);
		decembertv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了11月份");
	}

	public void getdecember(){
		allmonthtv.setBackgroundResource(R.drawable.flag_02);
		januarytv.setBackgroundResource(R.drawable.flag_02);
		februarytv.setBackgroundResource(R.drawable.flag_02);
		marchtv.setBackgroundResource(R.drawable.flag_02);
		apriltv.setBackgroundResource(R.drawable.flag_02);
		maytv.setBackgroundResource(R.drawable.flag_02);
		junetv.setBackgroundResource(R.drawable.flag_02);
		julytv.setBackgroundResource(R.drawable.flag_02);
		augusttv.setBackgroundResource(R.drawable.flag_02);
		septembertv.setBackgroundResource(R.drawable.flag_02);
		octobertv.setBackgroundResource(R.drawable.flag_02);
		novembertv.setBackgroundResource(R.drawable.flag_02);
		decembertv.setBackgroundResource(R.drawable.flag_03);
		ToastUtils.show(securityMonitorActivity, "你点击了12月份");
	}

	public void getAllyear(){
		allyeartv.setBackgroundResource(R.drawable.flag_03);
		liutv.setBackgroundResource(R.drawable.flag_02);
		wutv.setBackgroundResource(R.drawable.flag_02);
		sitv.setBackgroundResource(R.drawable.flag_02);
		santv.setBackgroundResource(R.drawable.flag_02);
		ertv.setBackgroundResource(R.drawable.flag_02);
		yitv.setBackgroundResource(R.drawable.flag_02);
		ToastUtils.show(securityMonitorActivity, "你点击了所有年份");
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
