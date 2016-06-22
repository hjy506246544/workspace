package com.gvs.controlpanel.activity.set;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.AddressConfigurationAdapter;
import com.gvs.controlpanel.adapter.AddressConfigurationAdapter.viewhodler2;
import com.gvs.controlpanel.bean.AddressConfigurationInfo;
import com.gvs.controlpanel.util.ToastUtils;
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
	private ListView lvaddress;
	private AddressConfigurationAdapter configurationAdapter;
    // 设置标题
    private String[] title = new String[] {
    		"1.1.0", "1.1.1", "1.1.2",
    		"1.1.3", "1.1.4", "1.1.5",
    		"1.1.6", "1.1.7", "1.1.8"};
    private AddressConfigurationInfo configurationInfo = new AddressConfigurationInfo();
    private List<AddressConfigurationInfo> listitem = new ArrayList<AddressConfigurationInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_addconfiguration_activity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	// 将上述资源转化为list集合
    	configurationInfo.setConfigurationName("1.1.0");
    	listitem.add(configurationInfo);

    	AddressConfigurationInfo configurationInfo2 = new AddressConfigurationInfo();
    	configurationInfo2.setConfigurationName("1.1.1");
    	listitem.add(configurationInfo2);

    	AddressConfigurationInfo configurationInfo3 = new AddressConfigurationInfo();
    	configurationInfo3.setConfigurationName("1.1.2");
    	listitem.add(configurationInfo3);
//        for (int i = 0; i < title.length; i++) {
//            Map map = new HashMap();
//            map.put("title", title[i]);
//        	//configurationInfo.setConfigurationName("1.1.0");
//            listitem.add(map);
//        }

        configurationAdapter = new AddressConfigurationAdapter(AddressConfigurationActivity.this,listitem);
        lvaddress.setAdapter(configurationAdapter);
	}

	private void initListener() {
		lvaddress.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				viewhodler2 holder= (viewhodler2) view.getTag();
				holder.pImageView.toggle();
				final RadioButton radio=(RadioButton) view.findViewById(R.id.albumPhoto);
				holder.pImageView = radio;
				for(String key:configurationAdapter.getStates().keySet()){
					configurationAdapter.getStates().put(key, false);
				}
				configurationAdapter.getStates().put(String.valueOf(position), radio.isChecked());
				configurationAdapter.notifyDataSetChanged();
			}
		});

		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddressConfigurationActivity.this.finish();
			}
		});

		bcbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ToastUtils.show(AddressConfigurationActivity.this,"选择地址配置成功！");
				AddressConfigurationActivity.this.finish();
			}
		});
	}

    private void initView() {
		backiv = (ImageView) findViewById(R.id.backiv);
		bcbtn = (Button) findViewById(R.id.bcbtn);
		lvaddress = (ListView) findViewById(R.id.lvaddress);
	}
}
