package com.gvs.hwcontrol.activity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.hwcontrol.R;
import com.gvs.hwcontrol.activity.base.FragmentActivityBase;
import com.gvs.hwcontrol.adapter.GridViewAdapter;
import com.gvs.hwcontrol.util.SkinSettingManager;
import com.gvs.hwcontrol.widget.Header;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
	/**
	 * 底部菜单
	 */
	private RadioGroup hwcontrolrg;//选项卡
	private RadioButton wdyyrb;//我的应用
	private RadioButton merb;//我的
	private RadioButton txlrb;//通讯录
	private RadioButton bhjprb;//拨号键盘

    //public List<Fragment> fragments = new ArrayList<Fragment>();
	private long mExitTime;

	private GridView listgv;
    private int listgvs=R.id.listgv;
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
	private SkinSettingManager mSettingManager;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wdyyactivity);
 //       fragments.add(new WdyyFragment());
//        fragments.add(new TxlFragment());
//        fragments.add(new BhjpFragment());
//        fragments.add(new MeFragment());
		initView();
		initData();
		initListener();
    }

    private void initData() {
//		header.setTitle(getResources().getString(R.string.wdyytitle));

//		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				MainActivity.this.finish();
//			}
//		});
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

		gridViewAdapter=new GridViewAdapter(MainActivity.this,listitem);
		listgv.setAdapter(gridViewAdapter);
	}

	private void initListener() {
		listgv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position==0){
					Intent intent  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent);
				}else if (position==1) {
					Intent intent2  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent2);
				}else if (position==2) {
					Intent intent3  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent3);
				}else if (position==3) {
					Intent intent4  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent4);
				}else if (position==4) {
					Intent intent5  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent5);
				}else if (position==5) {
					Intent intent6  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent6);
				}else if (position==6) {
					Intent intent7  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent7);
				}else if (position==7) {
					Intent intent8  = new Intent(MainActivity.this,TestActivity.class);
					startActivity(intent8);
				}else if (position==8) {
					Intent intent9  = new Intent(MainActivity.this,TestActivity.class);
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
				Intent intent = new Intent(MainActivity.this,SetActivity.class);
				//intent.putExtra("listgv", listgv);
				startActivity(intent);

			}
		});

		/*
		MainTabAdapter mainTabAdapter = new MainTabAdapter(this, fragments, R.id.contentll, hwcontrolrg);
		mainTabAdapter.setOnRgsExtraCheckedChangedListener(new MainTabAdapter.OnRgsExtraCheckedChangedListener(){
			@Override
			public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
				if(checkedId==R.id.wdyyrb){
					wdyyrb.setTextColor(getResources().getColor(R.color.green));
					txlrb.setTextColor(getResources().getColor(R.color.gray));
					bhjprb.setTextColor(getResources().getColor(R.color.gray));
					merb.setTextColor(getResources().getColor(R.color.gray));
				}
				else if(checkedId==R.id.txlrb){
					wdyyrb.setTextColor(getResources().getColor(R.color.gray));
					txlrb.setTextColor(getResources().getColor(R.color.green));
					bhjprb.setTextColor(getResources().getColor(R.color.gray));
					merb.setTextColor(getResources().getColor(R.color.gray));
				}else if(checkedId==R.id.bhjprb){
					wdyyrb.setTextColor(getResources().getColor(R.color.gray));
					txlrb.setTextColor(getResources().getColor(R.color.gray));
					bhjprb.setTextColor(getResources().getColor(R.color.green));
					merb.setTextColor(getResources().getColor(R.color.gray));
				}else if(checkedId==R.id.merb){
					wdyyrb.setTextColor(getResources().getColor(R.color.gray));
					txlrb.setTextColor(getResources().getColor(R.color.gray));
					bhjprb.setTextColor(getResources().getColor(R.color.gray));
					merb.setTextColor(getResources().getColor(R.color.green));
				}

			}
		});*/
	}

    private void initView() {
		//header = (Header) findViewById(R.id.header);
    	timetv = (TextView) findViewById(R.id.timetv);
		listgv=(GridView) findViewById(R.id.listgv);
		setiv = (ImageView) findViewById(R.id.setiv);
		/*
		wdyyrb = (RadioButton) findViewById(R.id.wdyyrb);
		txlrb = (RadioButton) findViewById(R.id.txlrb);
		bhjprb = (RadioButton) findViewById(R.id.bhjprb);
		merb = (RadioButton) findViewById(R.id.merb);
		hwcontrolrg = (RadioGroup) findViewById(R.id.hwcontrolrg);
		*/
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
