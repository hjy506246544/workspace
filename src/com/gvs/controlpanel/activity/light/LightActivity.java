package com.gvs.controlpanel.activity.light;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.Main_GridViewAdapter;
import com.gvs.controlpanel.widget.ColorPickerView;
import com.gvs.controlpanel.widget.ColorPickerView.onColorChangedListener;
import com.gvs.controlpanel.widget.Header;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
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
            R.drawable.main_dsj_, R.drawable.main_bx_,
            R.drawable.main_cl_};
    // 设置标题
    private String[] title = new String[] {
    		"茶几顶", "台灯", "激光灯"};
    private List listitem = new ArrayList();
	private AlertDialog dialog;

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
					showDialogTip();
				}else if (position==1) {
					//台灯
				}else if (position==2) {
					//激光灯
				}
			}
		});
	}

	private void showDialogTip() {
	    dialog = new AlertDialog.Builder(LightActivity.this).create();
	    dialog.show();
	    dialog.getWindow().setContentView(R.layout.teatable_dialog);
//	    Button zbrz = (Button) dialog.getWindow().findViewById(R.id.qdbtn);
	    final TextView rgbtv = (TextView) dialog.getWindow().findViewById(R.id.rgbtv);
//	    tstv.setText(ts);
//	    zbrz.setOnClickListener(new OnClickListener() {
//	      @Override
//	      public void onClick(View v) {
//	        dialog.dismiss();
//	      }
//	    });
	    ColorPickerView colorPickerView = (ColorPickerView) dialog.getWindow().findViewById(R.id.cpv);
        colorPickerView.setColorChangedListener(new onColorChangedListener() {

			@Override
			public void colorChanged(int red, int blue, int green) {
				Toast.makeText(LightActivity.this, "red="+red+"  blue="+blue+"   green="+green,
						Toast.LENGTH_SHORT).show();
				//rgbtv.setTextColor(red);
			}
		});
	    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
	    WindowManager windowManager = (WindowManager) LightActivity.this.getSystemService(Context.WINDOW_SERVICE);
	    params.width = windowManager.getDefaultDisplay().getWidth() - 100;
	    dialog.getWindow().setAttributes(params);
	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
		lightgv=(GridView) findViewById(R.id.lightgv);
	}
}
