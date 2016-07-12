package com.gvs.controlpanel.activity.main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.aircondition.AirConditionActivity;
import com.gvs.controlpanel.activity.backgroundmusic.BgMusicActivity;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.activity.curtain.CurtainDetailActivity;
import com.gvs.controlpanel.activity.light.Activity_Light;
import com.gvs.controlpanel.activity.scene.SceneActivity;
import com.gvs.controlpanel.activity.securitymonitor.SecurityMonitorActivity;
import com.gvs.controlpanel.activity.set.SetActivity;
import com.gvs.controlpanel.adapter.Mainthree_GridViewAdapter;
import com.gvs.controlpanel.util.SkinSettingManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 主题界面三
 * @author hjy
 * 2016-6-2
 */
public class MainMenuActivity extends FragmentActivityBase {
	static Context context;
	private GridView listgv;
	private RelativeLayout main;
	private TextView timetv,datetv,weektv;
	private Mainthree_GridViewAdapter gridViewAdapter;
	private long mExitTime;
	// 设置适配器的图片资源
    private int[] imageId = new int[] {
    		R.drawable.btn_main_light3_,
            R.drawable.btn_main_cl3_,
            R.drawable.btn_main_kt3_,

            R.drawable.btn_main_jtyy3_,
            R.drawable.btn_main_bgmusic3_,
            R.drawable.btn_main_scene3_,
            R.drawable.btn_main_afjk3_,

            R.drawable.btn_main_set3_};
    // 设置标题
    private String[] title = new String[] {
    		"灯光",
    		"窗帘",
    		"空调",
    		"家庭影院",
    		"背景音乐",
    		"场景",
    		"安防监控",
    		"设置",
    		};

    private List listitem = new ArrayList();
	private SkinSettingManager mSettingManager;
	private Dialog dialog;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainthree_activity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }

        //获取当前系统时间
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String mYear = String.valueOf(c.get(Calendar.YEAR));
		String mMonth = String.valueOf(c.get(Calendar.MONTH)+1);
		String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mMinute = String.valueOf(c.get(Calendar.MINUTE));
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if("1".equals(mWay)){
			mWay ="日";
		}else if("2".equals(mWay)){
			mWay ="一";
		}else if("3".equals(mWay)){
			mWay ="二";
		}else if("4".equals(mWay)){
			mWay ="三";
		}else if("5".equals(mWay)){
			mWay ="四";
		}else if("6".equals(mWay)){
			mWay ="五";
		}else if("7".equals(mWay)){
			mWay ="六";
		}

		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
		long timeStamp = System.currentTimeMillis();
		timetv.setText(/*mHour + ":" + mMinute*/timeformat.format(new Date(timeStamp)));
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		long dateStamp = System.currentTimeMillis();
		datetv.setText(/*mYear +
		        "-" + mMonth +
		        "-" + mDay*/dateformat.format(new Date(dateStamp)));
		weektv.setText("星期" + mWay);

		gridViewAdapter=new Mainthree_GridViewAdapter(MainMenuActivity.this,listitem);
		listgv.setAdapter(gridViewAdapter);
	}

	private void initListener() {
		/*
		lightll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				lightll.setBackgroundResource(R.drawable.main_guan);
			}
		});
		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainThreeActivity.this.finish();
			}
		});
		*/
		listgv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//灯光
				if(position==0){
					Intent intent  = new Intent(MainMenuActivity.this,Activity_Light.class);
					startActivity(intent);
				}else if (position==1) {
					//窗帘Activity_curtain.java
					Intent intent2  = new Intent(MainMenuActivity.this,CurtainDetailActivity.class);
					startActivity(intent2);
				}else if (position==2) {
					//空调

					startActivity(new Intent(MainMenuActivity.this,AirConditionActivity.class));
				}else if (position==3) {
					//家庭影院
				}else if (position==4) {
					//背景音乐
					Intent intent6  = new Intent(MainMenuActivity.this,BgMusicActivity.class);
					startActivity(intent6);
				}else if (position==5) {
					//场景
					Intent intent7  = new Intent(MainMenuActivity.this,SceneActivity.class);
					startActivity(intent7);
				}else if (position==6) {
					//安防监控
					Intent intent8  = new Intent(MainMenuActivity.this,SecurityMonitorActivity.class);
					startActivity(intent8);
				}else if (position==7) {
					//设置按钮
					Intent intent = new Intent(MainMenuActivity.this,SetActivity.class);
					startActivity(intent);
				}
			}
		});
	}

    private void initView() {
    	timetv = (TextView) findViewById(R.id.main_timetv);
    	weektv = (TextView) findViewById(R.id.weektv);
    	datetv = (TextView) findViewById(R.id.datetv);
    	main = (RelativeLayout) findViewById(R.id.activity_main);
		listgv=(GridView) findViewById(R.id.listgv2);
	}

    //每个页面都要重写这个方法和初始化皮肤的方法
    @Override
    protected void onResume() {
    		mSettingManager=new SkinSettingManager(MainMenuActivity.this,main);
    		mSettingManager.initSkins();
    	super.onResume();
    }

    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event){
		if (event.getAction() == KeyEvent.KEYCODE_HOME) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}
