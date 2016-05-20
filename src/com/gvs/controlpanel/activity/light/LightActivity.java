package com.gvs.controlpanel.activity.light;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.MainActivity;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.Main_GridViewAdapter;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.ColorPickView;
import com.gvs.controlpanel.widget.ColorPickView.OnColorChangedListener;
import com.gvs.controlpanel.widget.Header;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
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
	private GridView lightgv;
	private Main_GridViewAdapter gridViewAdapter;

	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_dsj_, R.drawable.main_afjk_,
            R.drawable.main_cl_};
    // 设置标题
    private String[] title = new String[] {
    		"茶几顶", "台灯", "激光灯"};
    private List listitem = new ArrayList();
	private Dialog dialog;

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
		lightgv.setAdapter(gridViewAdapter);
	}

	private void initListener() {
		lightgv.setOnItemClickListener(new OnItemClickListener() {

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
	private void showTeaTableDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.light_teatable_dialog);
	    final TextView rgbtv = (TextView) dialog.getWindow().findViewById(R.id.teatable_rgbtv);
	    ImageButton minusib = (ImageButton) dialog.getWindow().findViewById(R.id.teatable_minusib);
	    ImageButton jiaib = (ImageButton) dialog.getWindow().findViewById(R.id.teatable_jiaib);
	    ColorPickView colorPickerView = (ColorPickView) dialog.getWindow().findViewById(R.id.teatable_cpv);
	    minusib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(LightActivity.this, "该功能正在开发");
			}
		});

	    jiaib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(LightActivity.this, "该功能正在开发");
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
	private void showDeskLampDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.light_desklamp_dialog);
	    final TextView rgbtv = (TextView) dialog.getWindow().findViewById(R.id.desklamp_rgbtv);
	    ImageButton minusib = (ImageButton) dialog.getWindow().findViewById(R.id.desklamp_minusib);
	    ImageButton jiaib = (ImageButton) dialog.getWindow().findViewById(R.id.desklamp_jiaib);
	    ColorPickView colorPickerView = (ColorPickView) dialog.getWindow().findViewById(R.id.desklamp_cpv);
	    minusib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(LightActivity.this, "该功能正在开发");
			}
		});

	    jiaib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(LightActivity.this, "该功能正在开发");
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
	private void showLaserDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.light_laser_dialog);
	    final TextView rgbtv = (TextView) dialog.getWindow().findViewById(R.id.laser_rgbtv);
	    ImageButton minusib = (ImageButton) dialog.getWindow().findViewById(R.id.laser_minusib);
	    ImageButton jiaib = (ImageButton) dialog.getWindow().findViewById(R.id.laser_jiaib);
	    ColorPickView colorPickerView = (ColorPickView) dialog.getWindow().findViewById(R.id.laser_cpv);
	    minusib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(LightActivity.this, "该功能正在开发");
			}
		});

	    jiaib.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(LightActivity.this, "该功能正在开发");
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
		lightgv=(GridView) findViewById(R.id.lightgv);
	}
}
