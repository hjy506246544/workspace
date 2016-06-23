package com.gvs.controlpanel.activity.set;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.AddressConfigurationAdapter;
import com.gvs.controlpanel.adapter.AddressConfigurationAdapter.viewhodler2;
import com.gvs.controlpanel.bean.AddressConfigurationInfo;
import com.gvs.controlpanel.util.ToastUtils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
/**
 * 地址配置界面
 * 2016-6-21
 * @author hjy
 *
 */
public class AddressConfigurationActivity extends FragmentActivityBase {
	private ImageView backiv;
	private Button bcbtn;
	//public ListView lvaddress;
	private AddressConfigurationAdapter configurationAdapter;
    private AddressConfigurationInfo configurationInfo = new AddressConfigurationInfo();
    private List<AddressConfigurationInfo> listitem = new ArrayList<AddressConfigurationInfo>();
    private int pos;
    public RadioButton radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cell_config);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	// 将上述资源转化为list集合
//    	configurationInfo.setConfigurationName("1.1.0");
//    	listitem.add(configurationInfo);
//
//    	AddressConfigurationInfo configurationInfo2 = new AddressConfigurationInfo();
//    	configurationInfo2.setConfigurationName("1.1.1");
//    	listitem.add(configurationInfo2);
//
//    	AddressConfigurationInfo configurationInfo3 = new AddressConfigurationInfo();
//    	configurationInfo3.setConfigurationName("1.1.2");
//    	listitem.add(configurationInfo3);
//
//        configurationAdapter = new AddressConfigurationAdapter(AddressConfigurationActivity.this,listitem);
//        lvaddress.setAdapter(configurationAdapter);
	}

	private void initListener() {
//		lvaddress.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				pos = position;
//				viewhodler2 holder= (viewhodler2) view.getTag();
//				holder.pImageView.toggle();
//				radio=(RadioButton) view.findViewById(R.id.albumPhoto);
//				holder.pImageView = radio;
//				for(String key:configurationAdapter.getStates().keySet()){
//					configurationAdapter.getStates().put(key, false);
//				}
//				configurationAdapter.getStates().put(String.valueOf(pos), radio.isChecked());
//				configurationAdapter.notifyDataSetChanged();
//
//				//发送广播    2016-6-22
//				Intent intnet = new Intent("com.gvs.controlpanel.broadcast.USER_ACTION");
//				intnet.putExtra("id", String.valueOf(pos));
//				intnet.putExtra("name", listitem.get(pos).getConfigurationName());
//				sendBroadcast(intnet);
//			}
//		});

//		backiv.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				AddressConfigurationActivity.this.finish();
//			}
//		});
//
//		bcbtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				ToastUtils.show(AddressConfigurationActivity.this,"选择地址配置成功！");
//				AddressConfigurationActivity.this.finish();
//			}
//		});
	}

    private void initView() {
//		backiv = (ImageView) findViewById(R.id.backiv);
//		bcbtn = (Button) findViewById(R.id.bcbtn);
		//lvaddress = (ListView) findViewById(R.id.lvaddress);
	}
}
