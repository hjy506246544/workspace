package com.gvs.controlpanel.activity;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.activity.light.LightActivity;
import com.gvs.controlpanel.activity.set.AddressConfigurationActivity;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
/**
 * 设置界面
 * 2016-5-16
 * @author hjy
 *
 */
public class SetActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private RelativeLayout xxrl,xxrl2,xxrl3;
    MyReceiver myReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setactivity);
		initView();
		initData();
		//接收，注册广播    2016-6-22
        myReceiver = new MyReceiver();
        intentFilter = new IntentFilter("com.gvs.controlpanel.broadcast.USER_ACTION");
        this.registerReceiver(new MyReceiver(), intentFilter);
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.set_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				SetActivity.this.finish();
			}
		});
	}

	private void initListener() {

    	xxrl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SetActivity.this,BjtActivity.class));
			}
		});

    	xxrl2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//完成地址配置，暂时隐藏 2016-6-22
				startActivity(new Intent(SetActivity.this,AddressConfigurationActivity.class));
				//startActivity(new Intent(SetActivity.this,LightActivity.class));
			}
		});

    	xxrl3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SetActivity.this,LightActivity.class));
			}
		});
	}

	@Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    private void initView() {
    	xxrl = (RelativeLayout) findViewById(R.id.xxrl);
    	xxrl2 = (RelativeLayout) findViewById(R.id.xxrl2);
    	xxrl3 = (RelativeLayout) findViewById(R.id.xxrl3);
		header = (Header) findViewById(R.id.header);
	}

    public class MyReceiver extends BroadcastReceiver{

    	@Override
    	public void onReceive(Context context, Intent intent) {
    		// TODO Auto-generated method stub
            String name = intent.getExtras().getString("name");
            String id = intent.getExtras().getString("id");
            ToastUtils.show(context,"收到广播：第" +id+"个配置地址："+name);
            //abortBroadcast();   //Receiver1接收到广播后中断广播
    	}

    }
}
