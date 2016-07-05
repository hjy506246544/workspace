package com.gvs.controlpanel.activity.set;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.activity.main.MainMenuActivity;
import com.gvs.controlpanel.activity.securitymonitor.AddMonitorPreviewActivity;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.SlideSwitch;
import com.gvs.controlpanel.widget.SlideSwitch.OnStateChangedListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
/**
 * 设置界面
 * 2016-5-16
 * @author hjy
 *
 */
public class SetActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	private RelativeLayout xxrl,xxrl2,xxrl4,xxrl5;
    MyReceiver myReceiver;
    IntentFilter intentFilter;
	private Dialog dialog;

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

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

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
				startActivity(new Intent(SetActivity.this,Activity_AddressConfiguration.class));
				//startActivity(new Intent(SetActivity.this,LightActivity.class));
			}
		});

    	xxrl4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPwdDialogTip();
			}
		});

    	xxrl5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialogTip();
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
    	xxrl = (RelativeLayout) findViewById(R.id.setup_activity_xxrl);
    	xxrl2 = (RelativeLayout) findViewById(R.id.setup_activity_xxrl2);
    	xxrl4 = (RelativeLayout) findViewById(R.id.setup_activity_xxrl4);
    	xxrl5 = (RelativeLayout) findViewById(R.id.setup_activity_xxrl5);
		header = (Header) findViewById(R.id.setup_activity_header);
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
				if(pwdtxt.equals("0000")){
					ToastUtils.show(SetActivity.this,"修改密码成功");
					dialog.dismiss();
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
		    	  modeselectioniv.setButtonDrawable(R.drawable.btn_cheaktrue);
		    	  usermodeiv.setButtonDrawable(R.drawable.btn_cheakfalse);
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
		    	  usermodeiv.setButtonDrawable(R.drawable.btn_cheaktrue);
		    	  modeselectioniv.setButtonDrawable(R.drawable.btn_cheakfalse);
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
