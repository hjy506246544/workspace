package com.gvs.controlpanel.activity.main;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.SetActivity;
import com.gvs.controlpanel.activity.backgroundmusic.BgMusicActivity;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.activity.light.LightActivity;
import com.gvs.controlpanel.activity.scene.AddSceneActivity;
import com.gvs.controlpanel.activity.scene.SceneActivity;
import com.gvs.controlpanel.activity.securitymonitor.SecurityMonitorActivity;
import com.gvs.controlpanel.adapter.Main_GridViewAdapter;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 主界面
 * @author hjy
 *
 */
public class MainActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
    private ImageView backiv;
	private GridView listgv;
	private TextView timetv;
	private Main_GridViewAdapter gridViewAdapter;
	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_light_, R.drawable.main_cl_,
            R.drawable.main_kt_, R.drawable.main_dsj_,
            R.drawable.main_jtyy_, R.drawable.main_bgmusic_,
            R.drawable.main_scene_, R.drawable.main_afjk_,R.drawable.main_set_};
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
        setContentView(R.layout.mainactivity);
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
            /*
            map.put("state", state[i]);
            map.put("info", info[i]);
            */
            listitem.add(map);
        }

        //获取当前系统时间
		Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH)+1;
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		int mHour = c.get(Calendar.HOUR_OF_DAY);
		int mMinute = c.get(Calendar.MINUTE);
		timetv.setText(mYear +
        "年" + mMonth +
        "月" + mDay +
        "日  " + mHour +
        " : " + mMinute);

		gridViewAdapter=new Main_GridViewAdapter(MainActivity.this,listitem);
		listgv.setAdapter(gridViewAdapter);
	}

	private void initListener() {
		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.this.finish();
			}
		});

		listgv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//灯光
				if(position==0){
					Intent intent  = new Intent(MainActivity.this,LightActivity.class);
					startActivity(intent);
				}else if (position==1) {
					//窗帘
					showCurtainDialogTip();
				}else if (position==2) {
					//空调
					showAirConditionDialogTip();
				}else if (position==3) {
					//电视机
					showTVDialogTip();
				}else if (position==4) {
					//家庭影院
					showHomeThertreDialogTip();
				}else if (position==5) {
					//背景音乐
					Intent intent6  = new Intent(MainActivity.this,BgMusicActivity.class);
					startActivity(intent6);
				}else if (position==6) {
					//场景
					Intent intent7  = new Intent(MainActivity.this,SceneActivity.class);
					startActivity(intent7);
				}else if (position==7) {
					//安防监控
					Intent intent8  = new Intent(MainActivity.this,SecurityMonitorActivity.class);
					startActivity(intent8);
				}else if (position==8) {
					//设置按钮
					Intent intent = new Intent(MainActivity.this,SetActivity.class);
					startActivity(intent);
				}
			}
		});

    	/**
    	 * 设置按钮
    	 * 打开手机自带系统设置界面
    	setiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//打开手机自带系统设置界面
				//startActivity(new Intent(Settings.ACTION_SETTINGS));
				Intent intent = new Intent(MainActivity.this,SetActivity.class);
				startActivity(intent);

			}
		});
    	 */
	}

	/**
	 * 窗帘
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	private void showCurtainDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.curtain_dialog);
	    SlideSwitch sSwitch = (SlideSwitch) dialog.getWindow().findViewById(R.id.curtain_slideswitch);
	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "开关已关闭", 1000).show();
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
	private void showAirConditionDialogTip() {
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
					ToastUtils.show(MainActivity.this, "超过最低温度了！");
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
					ToastUtils.show(MainActivity.this, "超过最高温度了！");
				}
			}
		});

	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "开关已关闭", 1000).show();
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
	private void showTVDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.tv_dialog);
	    SlideSwitch sSwitch = (SlideSwitch) dialog.getWindow().findViewById(R.id.tv_slideswitch);
	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "开关已关闭", 1000).show();
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
	private void showHomeThertreDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.hometheatre_dialog);
	    SlideSwitch sSwitch = (SlideSwitch) dialog.getWindow().findViewById(R.id.hometheatre_slideswitch);
	    sSwitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    Toast.makeText(MainActivity.this, "开关已打开", 1000).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "开关已关闭", 1000).show();
                }
            }

        });
	    dialog.setCancelable(true);
		dialog.show();
	}

    private void initView() {
    	timetv = (TextView) findViewById(R.id.timetv);
		listgv=(GridView) findViewById(R.id.listgv);
		backiv = (ImageView) findViewById(R.id.backiv);
	}

    //每个页面都要重写这个方法和初始化皮肤的方法
    @Override
    protected void onResume() {
    	//初始化皮肤
    	//layout=new LinearLayout[layouts.length];
    	//for(int i=0; i<layouts.length; i++){
    		//layout[i]=(LinearLayout) findViewById(layouts[i]);
    		mSettingManager=new SkinSettingManager(MainActivity.this,listgv);
    		mSettingManager.initSkins();
    	//}
    	super.onResume();
    }
}
