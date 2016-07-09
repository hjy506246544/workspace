package com.gvs.controlpanel.activity.set;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.bean.SetBean;
import com.gvs.controlpanel.util.PreferencesUtils;
import com.gvs.controlpanel.util.SharedPreferencesUtils;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import common.ReadFileText;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 设置界面
 * 2016-5-16
 * @author hjy
 */
public class SetActivity extends FragmentActivityBase {
	public Header header;
	Context context;
	private TextView setup_activity_tv_setname,setup_activity_tv_setname2;
	private RelativeLayout setup_activity_rl_backgroundimg,setup_activity_rl_setaddress,
							setup_activity_rl_updatepwd,setup_activity_rl_modeselect,setup_activity_rl,setup_activity_rl2;
	private ImageView setup_activity_iv_setname,setup_activity_iv_setname2,setup_activity_iv_return
					,setup_activity_iv_return2,setup_activity_iv_xian,setup_activity_iv_xian7;
    MyReceiver myReceiver;
    IntentFilter intentFilter;
	private Dialog dialog;
	private List<SetBean> list  = new ArrayList<SetBean>();
	private SetBean setBean = new SetBean(),setBean2 = new SetBean(),setBean3 = new SetBean(),setBean4 = new SetBean(),setBean5 = new SetBean(),setBean6 = new SetBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setactivity);
		initView();
		setType();
		initData();
		//接收，注册广播    2016-6-22
//        myReceiver = new MyReceiver();
//        intentFilter = new IntentFilter("com.gvs.controlpanel.broadcast.USER_ACTION");
//        this.registerReceiver(new MyReceiver(), intentFilter);
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.set_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				SetActivity.this.finish();
			}
		});
	}

    /**
     * 用SharedPreferences保存对象到本地
     * 用类型判断是否是一级菜单或者二级菜单
     * 2016-7-7 hjy
     */
    public void setType(){
    	setBean.setSetType("1");
    	setBean.setSetName("设置");
    	list.add(setBean);
    	PreferencesUtils.putString(SetActivity.this, "type", setBean.getSetType());
    	PreferencesUtils.putString(SetActivity.this, "name", setBean.getSetName());
    	//SharedPreferencesUtils.setParam(SetActivity.this, "type", setBean.getSetType());

    	setBean2.setSetType("1");
    	setBean2.setSetName("设置2");
    	list.add(setBean2);
    	PreferencesUtils.putString(SetActivity.this, "type2", setBean2.getSetType());
    	PreferencesUtils.putString(SetActivity.this, "name2", setBean2.getSetName());

    	setBean3.setSetType("2");
    	setBean3.setSetName("背景替换");
    	list.add(setBean3);
    	PreferencesUtils.putString(SetActivity.this, "type3", setBean3.getSetType());
    	PreferencesUtils.putString(SetActivity.this, "name3", setBean3.getSetName());

    	setBean4.setSetType("2");
    	setBean4.setSetName("地址配置");
    	list.add(setBean4);
    	PreferencesUtils.putString(SetActivity.this, "type4", setBean4.getSetType());
    	PreferencesUtils.putString(SetActivity.this, "name4", setBean4.getSetName());

    	setBean5.setSetType("2");
    	setBean5.setSetName("密码替换");
    	list.add(setBean5);
    	PreferencesUtils.putString(SetActivity.this, "type5", setBean5.getSetType());
    	PreferencesUtils.putString(SetActivity.this, "name5", setBean5.getSetName());

    	setBean6.setSetType("2");
    	setBean6.setSetName("模式选择");
    	list.add(setBean6);
    	PreferencesUtils.putString(SetActivity.this, "type6", setBean6.getSetType());
    	PreferencesUtils.putString(SetActivity.this, "name6", setBean6.getSetName());
    	Log.e("type2", "type2="+SharedPreferencesUtils.getParam(SetActivity.this, "type",setBean.getSetType()));
//    	Log.e("name2", "name2="+PreferencesUtils.getString(SetActivity.this, "name2"));
    	if(PreferencesUtils.getString(SetActivity.this, "type").equals("1")){
    		setup_activity_rl.setBackgroundResource(0);
    		setup_activity_iv_setname.setVisibility(View.GONE);
    		setup_activity_iv_return.setVisibility(View.GONE);
    		setup_activity_iv_xian.setVisibility(View.GONE);
    		setup_activity_tv_setname.setVisibility(View.VISIBLE);
    		setup_activity_tv_setname.setTextSize(36);
    	}
    	if(PreferencesUtils.getString(SetActivity.this, "type2").equals("1")){
    		setup_activity_rl2.setBackgroundResource(0);
    		setup_activity_iv_setname2.setVisibility(View.GONE);
    		setup_activity_iv_return2.setVisibility(View.GONE);
    		setup_activity_iv_xian7.setVisibility(View.GONE);
    		setup_activity_tv_setname2.setVisibility(View.VISIBLE);
    		setup_activity_tv_setname2.setTextSize(36);
    	}
    	if(PreferencesUtils.getString(SetActivity.this, "type3").equals("2")){
    		setup_activity_rl_backgroundimg.setVisibility(View.VISIBLE);
    	}
//    	if(PreferencesUtils.getString(SetActivity.this, "type4").equals("2")){
//    		setup_activity_rl_setaddress.setVisibility(View.VISIBLE);
//    	}
    	if(PreferencesUtils.getString(SetActivity.this, "type5").equals("2")){
    		setup_activity_rl_updatepwd.setVisibility(View.VISIBLE);
    	}
    	if(PreferencesUtils.getString(SetActivity.this, "type6").equals("2")){
    		setup_activity_rl_modeselect.setVisibility(View.VISIBLE);
    	}
    }

	private void initListener() {

    	setup_activity_rl_backgroundimg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(SetActivity.this,BjtActivity.class));
			}
		});

    	setup_activity_rl_setaddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//完成地址配置，暂时隐藏 2016-6-22
				startActivity(new Intent(SetActivity.this,Activity_AddressConfiguration.class));
				//startActivity(new Intent(SetActivity.this,LightActivity.class));
			}
		});

    	setup_activity_rl_updatepwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPwdDialogTip();
			}
		});

    	setup_activity_rl_modeselect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialogTip();
			}
		});
	}

	@Override
    protected void onResume() {
        super.onResume();
        //registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //unregisterReceiver(myReceiver);
    }

    private void initView() {
    	setup_activity_rl_backgroundimg = (RelativeLayout) findViewById(R.id.setup_activity_rl_backgroundimg);
    	setup_activity_rl_setaddress = (RelativeLayout) findViewById(R.id.setup_activity_rl_setaddress);
    	setup_activity_rl_updatepwd = (RelativeLayout) findViewById(R.id.setup_activity_rl_updatepwd);
    	setup_activity_rl_modeselect = (RelativeLayout) findViewById(R.id.setup_activity_rl_modeselect);
    	setup_activity_rl = (RelativeLayout) findViewById(R.id.setup_activity_rl);
    	setup_activity_rl2 = (RelativeLayout) findViewById(R.id.setup_activity_rl2);
		header = (Header) findViewById(R.id.setup_activity_header);
		setup_activity_tv_setname = (TextView) findViewById(R.id.setup_activity_tv_setname);
		setup_activity_tv_setname2 = (TextView) findViewById(R.id.setup_activity_tv_setname2);
		setup_activity_iv_setname = (ImageView) findViewById(R.id.setup_activity_iv_setname);
		setup_activity_iv_return = (ImageView) findViewById(R.id.setup_activity_iv_return);
		setup_activity_iv_xian = (ImageView) findViewById(R.id.setup_activity_iv_xian);
		setup_activity_iv_setname2 = (ImageView) findViewById(R.id.setup_activity_iv_setname2);
		setup_activity_iv_return2 = (ImageView) findViewById(R.id.setup_activity_iv_return2);
		setup_activity_iv_xian7 = (ImageView) findViewById(R.id.setup_activity_iv_xian7);
	}

    /**
	 * 替换密码
	 * 自定义弹出框
	 * 2016-5-20
	 * hjy
	 */
	public void showPwdDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    dialog.setContentView(R.layout.setpwd_dialog);
	    final EditText pwdet = (EditText) dialog.getWindow().findViewById(R.id.pwdet);
	    Button qrbtn = (Button) dialog.getWindow().findViewById(R.id.qdbtn);
	    qrbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String pwdtxt = pwdet.getText().toString();
				if(TextUtils.isEmpty(pwdtxt)){
					ToastUtils.show(SetActivity.this,"请输入密码");
					return;
				}
				if(pwdtxt.equals("000000")){
					//ToastUtils.show(SetActivity.this,"修改密码成功");
					dialog.dismiss();
					showDialog_UpdateDeviceData();
				}else {
					ToastUtils.show(SetActivity.this,"输入密码有误");
					return;
				}
			}
		});
	    dialog.setCancelable(true);
		dialog.show();
	}

	private void showDialogTip() {
	    dialog = new Dialog(this,R.style.dialog);
	    //dialog.show();
	    dialog.setContentView(R.layout.setmodeselection_dialog);
	    LinearLayout modeselectionll = (LinearLayout) dialog.getWindow().findViewById(R.id.modeselectionll);
	    LinearLayout usermodell = (LinearLayout) dialog.getWindow().findViewById(R.id.usermodell);
	    final RadioButton modeselectioniv = (RadioButton) dialog.getWindow().findViewById(R.id.modeselectioniv);
	    final RadioButton usermodeiv = (RadioButton) dialog.getWindow().findViewById(R.id.usermodeiv);

	    modeselectionll.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {
		    	  modeselectioniv.setButtonDrawable(R.drawable.btn_checked2);
		    	  usermodeiv.setButtonDrawable(R.drawable.btn_unchecked2);
		    	  new Handler().postDelayed(new Runnable() {
		  			@Override
		  			public void run() {
		  				dialog.dismiss();
		  			}
		  		  }, 200);
		      }
	    });

	    usermodell.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {
		    	  usermodeiv.setButtonDrawable(R.drawable.btn_checked2);
		    	  modeselectioniv.setButtonDrawable(R.drawable.btn_unchecked2);
		    	  new Handler().postDelayed(new Runnable() {
		  			@Override
		  			public void run() {
		  				dialog.dismiss();
		  			}
		  		  }, 200);
		      }
	    });
	    dialog.setCancelable(true);
		dialog.show();
	}

	private void showDialog_UpdateDeviceData() {
	    dialog = new Dialog(this,R.style.dialog);
	    //dialog.show();
	    dialog.setContentView(R.layout.showdialog_update_device_data);
	    Button btn_update = (Button) dialog.getWindow().findViewById(R.id.btn_updata_device_update);
	    Button btn_create = (Button) dialog.getWindow().findViewById(R.id.btn_updata_device_create);

	    btn_update.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {

		    	  //-------------------------------------------
		    	  ReadFileText mReadFileText=new ReadFileText(SetActivity.this);
					String path=null;
					path=mReadFileText.SearchSpecificFile("GvsDeviceConfig.cfg");
					if(path==null){
						Toast.makeText(SetActivity.this, "update file not exist! update failed!!!", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(SetActivity.this, "find file"+"GvsDeviceConfig.cfg"+ " in dir:"+path, Toast.LENGTH_SHORT).show();
						String txt=mReadFileText.ReadTxtFileAndCreateDevice(path);
						Log.i("TAG_GROM", txt);
					}
                  //-------------------------------------------
		    	  new Handler().postDelayed(new Runnable() {
		  			@Override
		  			public void run() {
		  				dialog.dismiss();
		  			}
		  		  }, 200);
		      }
	    });

	    btn_create.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {

		    	  //-------------------------------------------
		    	  ReadFileText mReadFileText=new ReadFileText(SetActivity.this);
					String path=null;
					path=mReadFileText.SearchSpecificFile("GvsDeviceConfig.cfg");
					if(path==null){
						Toast.makeText(SetActivity.this, "update file not exist! update failed!!!", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(SetActivity.this, "find file"+"GvsDeviceConfig.cfg"+ " in dir:"+path, Toast.LENGTH_SHORT).show();
						String txt=mReadFileText.ReadTxtFileAndCreateDevice(path);
						Log.i("TAG_GROM", txt);
					}
                  //-------------------------------------------
		    	  new Handler().postDelayed(new Runnable() {
		  			@Override
		  			public void run() {
		  				dialog.dismiss();
		  			}
		  		  }, 200);
		      }
	    });
	    dialog.setCancelable(true);
		dialog.show();
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
