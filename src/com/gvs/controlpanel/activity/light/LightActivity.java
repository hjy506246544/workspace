package com.gvs.controlpanel.activity.light;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.Main_GridViewAdapter;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.ColorPickView;
import com.gvs.controlpanel.widget.ColorPickView.OnColorChangedListener;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.HorizontalListView;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 灯光主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class LightActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private Main_GridViewAdapter gridViewAdapter;

	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_light_, R.drawable.main_light_,
            R.drawable.main_light_, R.drawable.main_light_,
            R.drawable.main_light_, R.drawable.main_light_,
            R.drawable.main_light_};
    // 设置标题
    private String[] title = new String[] {
    		"茶几顶", "台灯", "激光灯", "台灯", "激光灯", "台灯", "激光灯"};
    private List listitem = new ArrayList();
	private Dialog dialog;
	private HorizontalListView hsm_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightactivity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.light_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				LightActivity.this.finish();
			}
		});
    	// 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
		gridViewAdapter=new Main_GridViewAdapter(LightActivity.this,listitem);
		hsm_container.setAdapter(gridViewAdapter);
	}

	private void initListener() {
		hsm_container.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//茶几顶
				if(position==0){
					showTeaTableDialogTip();
				}else if (position==1) {
					//台灯
					showDeskLampDialogTip();
				}else if (position==2) {
					//激光灯
					showLaserDialogTip();
				}
			}
		});
	}

	/**
	 * 茶几顶
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	int teatable_ld = 2;
	private void showTeaTableDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.light_teatable_dialog);
	    final TextView rgbtv = (TextView) dialog.getWindow().findViewById(R.id.teatable_rgbtv);
	    ImageButton minusib = (ImageButton) dialog.getWindow().findViewById(R.id.teatable_minusib);
	    ImageButton jiaib = (ImageButton) dialog.getWindow().findViewById(R.id.teatable_jiaib);
	    final SeekBar teatable_jdtsb = (SeekBar) dialog.getWindow().findViewById(R.id.teatable_jdtsb);
        teatable_jdtsb.setProgress(teatable_ld);
	    ColorPickView colorPickerView = (ColorPickView) dialog.getWindow().findViewById(R.id.teatable_cpv);
	    minusib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(teatable_ld>0){
					teatable_ld--;
					Log.e("ld=", "ld="+teatable_ld);
			        teatable_jdtsb.setProgress(teatable_ld);
					Log.e("ld2=", "ld2="+teatable_ld);
		        }else {
					ToastUtils.show(LightActivity.this, "超过最低亮度了！");
				}
			}
		});

	    jiaib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(teatable_ld<3){
					teatable_ld++;
					Log.e("ld3=", "ld3="+teatable_ld);
			        teatable_jdtsb.setProgress(teatable_ld);
					Log.e("ld4=", "ld4="+teatable_ld);
		        }else {
					ToastUtils.show(LightActivity.this, "超过最高亮度了！");
				}
			}
		});
	    colorPickerView.setOnColorChangedListener(new OnColorChangedListener() {

			@Override
			public void onColorChange(int color) {
				rgbtv.setTextColor(color);
			}

		});
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 台灯
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	int desklamp_ld = 2;
	private void showDeskLampDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.light_desklamp_dialog);
	    final TextView rgbtv = (TextView) dialog.getWindow().findViewById(R.id.desklamp_rgbtv);
	    ImageButton minusib = (ImageButton) dialog.getWindow().findViewById(R.id.desklamp_minusib);
	    ImageButton jiaib = (ImageButton) dialog.getWindow().findViewById(R.id.desklamp_jiaib);
	    final SeekBar teatable_jdtsb = (SeekBar) dialog.getWindow().findViewById(R.id.desklamp_jdtsb);
        teatable_jdtsb.setProgress(desklamp_ld);
	    ColorPickView colorPickerView = (ColorPickView) dialog.getWindow().findViewById(R.id.desklamp_cpv);
	    minusib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(desklamp_ld>0){
					desklamp_ld--;
					Log.e("ld=", "ld="+desklamp_ld);
			        teatable_jdtsb.setProgress(desklamp_ld);
					Log.e("ld2=", "ld2="+desklamp_ld);
		        }else {
					ToastUtils.show(LightActivity.this, "超过最低亮度了！");
				}
			}
		});

	    jiaib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(desklamp_ld<3){
					desklamp_ld++;
					Log.e("ld3=", "ld3="+desklamp_ld);
			        teatable_jdtsb.setProgress(desklamp_ld);
					Log.e("ld4=", "ld4="+desklamp_ld);
		        }else {
					ToastUtils.show(LightActivity.this, "超过最高亮度了！");
				}
			}
		});
	    colorPickerView.setOnColorChangedListener(new OnColorChangedListener() {

			@Override
			public void onColorChange(int color) {
				rgbtv.setTextColor(color);
			}

		});
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 激光灯
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	int laser_ld = 2;
	private void showLaserDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.light_laser_dialog);
	    final TextView rgbtv = (TextView) dialog.getWindow().findViewById(R.id.laser_rgbtv);
	    ImageButton minusib = (ImageButton) dialog.getWindow().findViewById(R.id.laser_minusib);
	    ImageButton jiaib = (ImageButton) dialog.getWindow().findViewById(R.id.laser_jiaib);
	    final SeekBar teatable_jdtsb = (SeekBar) dialog.getWindow().findViewById(R.id.laser_jdtsb);
        teatable_jdtsb.setProgress(laser_ld);
	    ColorPickView colorPickerView = (ColorPickView) dialog.getWindow().findViewById(R.id.laser_cpv);
	    minusib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(laser_ld>0){
					laser_ld--;
					Log.e("ld=", "ld="+laser_ld);
			        teatable_jdtsb.setProgress(laser_ld);
					Log.e("ld2=", "ld2="+laser_ld);
		        }else {
					ToastUtils.show(LightActivity.this, "超过最低亮度了！");
				}
			}
		});

	    jiaib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(laser_ld<3){
					laser_ld++;
					Log.e("ld3=", "ld3="+laser_ld);
			        teatable_jdtsb.setProgress(laser_ld);
					Log.e("ld4=", "ld4="+laser_ld);
		        }else {
					ToastUtils.show(LightActivity.this, "超过最高亮度了！");
				}
			}
		});
	    colorPickerView.setOnColorChangedListener(new OnColorChangedListener() {

			@Override
			public void onColorChange(int color) {
				rgbtv.setTextColor(color);
			}

		});
	    dialog.setCancelable(true);
		dialog.show();
	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
		hsm_container = (HorizontalListView) findViewById(R.id.hsm_container);
	}
}
