package com.gvs.controlpanel.activity.main;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.SetActivity;
import com.gvs.controlpanel.activity.aircondition.AirConditionActivity;
import com.gvs.controlpanel.activity.backgroundmusic.BgMusicActivity;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.activity.curtain.CurtainDetailActivity;
import com.gvs.controlpanel.activity.light.Activity_Light;
import com.gvs.controlpanel.activity.scene.SceneActivity;
import com.gvs.controlpanel.activity.securitymonitor.SecurityMonitorActivity;
import com.gvs.controlpanel.adapter.Main_GridViewAdapter;
import com.gvs.controlpanel.adapter.Mainthree_GridViewAdapter;
import com.gvs.controlpanel.util.SkinSettingManager;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.SlideSwitch;
import com.gvs.controlpanel.widget.SlideSwitch.OnStateChangedListener;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private ImageView backiv;
	private GridView listgv;
	private RelativeLayout main;
	private TextView timetv,datetv,weektv;
	private LinearLayout lightll,clll,ktll,dsjll;
	private Mainthree_GridViewAdapter gridViewAdapter;
	private long mExitTime;
	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_light6, R.drawable.main_cl6,
            R.drawable.main_kt6, R.drawable.main_dsj6,
            R.drawable.main_jtyy6, R.drawable.main_bgmusic6,
            R.drawable.main_scene6, R.drawable.main_afjk6,R.drawable.main_set6};
    // 设置标题
    private String[] title = new String[] {
    		"灯光", "窗帘", "空调",
    		"电视机", "家庭影院", "背景音乐",
    		"场景", "安防监控", "设置"};
    /*
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
    		*/
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
    	// 将上述资源转化为list集合
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
		timetv.setText(mHour + ":" + mMinute);
		datetv.setText(mYear +
		        "-" + mMonth +
		        "-" + mDay);
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
					//showCurtainDialogTip();
					Intent intent2  = new Intent(MainMenuActivity.this,CurtainDetailActivity.class);
					startActivity(intent2);
				}else if (position==2) {
					//空调
//					showAirConditionDialogTip();
					
					startActivity(new Intent(MainMenuActivity.this,AirConditionActivity.class));
				}else if (position==3) {
					//电视机
					showTVDialogTip();
				}else if (position==4) {
					//家庭影院
					showHomeThertreDialogTip();
				}else if (position==5) {
					//背景音乐
					Intent intent6  = new Intent(MainMenuActivity.this,BgMusicActivity.class);
					startActivity(intent6);
				}else if (position==6) {
					//场景
					Intent intent7  = new Intent(MainMenuActivity.this,SceneActivity.class);
					startActivity(intent7);
				}else if (position==7) {
					//安防监控
					Intent intent8  = new Intent(MainMenuActivity.this,SecurityMonitorActivity.class);
					startActivity(intent8);
				}else if (position==8) {
					//设置按钮
					Intent intent = new Intent(MainMenuActivity.this,SetActivity.class);
					startActivity(intent);
				}
			}
		});
	}

	/**
	 * 窗帘
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	public void showCurtainDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.curtain_dialog);
	    SlideSwitch sSwitch = (SlideSwitch) dialog.getWindow().findViewById(R.id.curtain_slideswitch);
	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainMenuActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainMenuActivity.this, "开关已关闭", 1000).show();
                }
            }

        });
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 空调
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	int wd = 26;
	public void showAirConditionDialogTip() {
		dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.aircondition_dialog);
	    ImageButton minusib = (ImageButton) dialog.getWindow().findViewById(R.id.minusib);
	    ImageButton jiaib = (ImageButton) dialog.getWindow().findViewById(R.id.jiaib);
	    final TextView ktwdtv = (TextView) dialog.getWindow().findViewById(R.id.ktwdtv);
	    ktwdtv.setText(wd +"℃");
	    SlideSwitch sSwitch = (SlideSwitch) dialog.getWindow().findViewById(R.id.aircondition_slideswitch);
	    minusib.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        if(wd>16){
	                wd--;
			        ktwdtv.setText(wd +"℃");
		        }else {
					ToastUtils.show(MainMenuActivity.this, "超过最低温度了！");
				}
			}
		});

	    jiaib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
		        if(wd <32){
	                wd++;
			        ktwdtv.setText(wd +"℃");
		        }else {
					ToastUtils.show(MainMenuActivity.this, "超过最高温度了！");
				}
			}
		});

	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainMenuActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainMenuActivity.this, "开关已关闭", 1000).show();
                }
            }

        });
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 电视机
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	public void showTVDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.tv_dialog);
	    SlideSwitch sSwitch = (SlideSwitch) dialog.getWindow().findViewById(R.id.tv_slideswitch);
	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainMenuActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainMenuActivity.this, "开关已关闭", 1000).show();
                }
            }

        });
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 家庭影院
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	public void showHomeThertreDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.hometheatre_dialog);
	    SlideSwitch sSwitch = (SlideSwitch) dialog.getWindow().findViewById(R.id.hometheatre_slideswitch);
	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainMenuActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainMenuActivity.this, "开关已关闭", 1000).show();
                }
            }

        });
	    dialog.setCancelable(true);
		dialog.show();
	}

    private void initView() {
    	timetv = (TextView) findViewById(R.id.timetv);
    	weektv = (TextView) findViewById(R.id.weektv);
    	datetv = (TextView) findViewById(R.id.datetv);
    	main = (RelativeLayout) findViewById(R.id.main);
//    	lightll = (LinearLayout) findViewById(R.id.lightll);
//    	clll = (LinearLayout) findViewById(R.id.clll);
//    	ktll = (LinearLayout) findViewById(R.id.ktll);
//    	dsjll = (LinearLayout) findViewById(R.id.dsjll);
		listgv=(GridView) findViewById(R.id.listgv2);
		//backiv = (ImageView) findViewById(R.id.backiv);
	}

    //每个页面都要重写这个方法和初始化皮肤的方法
    @Override
    protected void onResume() {
    	//初始化皮肤
    	//layout=new LinearLayout[layouts.length];
    	//for(int i=0; i<layouts.length; i++){
    		//layout[i]=(LinearLayout) findViewById(layouts[i]);
    		mSettingManager=new SkinSettingManager(MainMenuActivity.this,main);
    		mSettingManager.initSkins();
    	//}
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
