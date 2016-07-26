package com.gvs.controlpanel.activity.systemset;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.util.ContextUtil;
import com.gvs.controlpanel.util.PreferencesUtils;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 关于设备主界面
 * 2016-7-25
 * @author hjy
 *
 */
public class AboutDeviceActivity extends FragmentActivityBase {
	public Header aboutdevice_activity_header;
	private TextView aboutdevice_activity_tv_modelnumber,aboutdevice_activity_tv_androidversion,
					 aboutdevice_activity_tv_firmwareversion,aboutdevice_activity_tv_kernelversion,
					 aboutdevice_activity_tv_versionnumber;
	private RelativeLayout aboutdevice_activity_rl_systemupdate,aboutdevice_activity_rl_legalinformation;
	private int Result;
	private String target;
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private ProgressBar mProgress;
	private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_aboutdevice);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	aboutdevice_activity_header.setTitle(getResources().getString(R.string.aboutdevice_set));

    	aboutdevice_activity_header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				AboutDeviceActivity.this.finish();
			}
		});

    	aboutdevice_activity_tv_modelnumber.setText(android.os.Build.MODEL);
    	aboutdevice_activity_tv_androidversion.setText(android.os.Build.VERSION.RELEASE);
    	aboutdevice_activity_tv_firmwareversion.setText(getAppVersionName(AboutDeviceActivity.this));
    	aboutdevice_activity_tv_kernelversion.setText(getKernelVersion());
    	aboutdevice_activity_tv_versionnumber.setText(android.os.Build.VERSION.SDK);
	}

    /**
     * 获取内核版本
     * @return
     */
    public static String getKernelVersion() {
        String kernelVersion = "";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/proc/version");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return kernelVersion;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8 * 1024);
        String info = "";
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                info += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (info != "") {
                final String keyword = "version ";
                int index = info.indexOf(keyword);
                line = info.substring(index + keyword.length());
                index = line.indexOf(" ");
                kernelVersion = line.substring(0, index);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return kernelVersion;
    }

    /**
     * 获取当前版本号
     * @param context
     * @return
     */
    private String getAppVersionName(Context context) {
	    String versionName = "";
	    try {
		    PackageManager packageManager = context.getPackageManager();
		    PackageInfo packageInfo = packageManager.getPackageInfo("com.gvs.controlpanel", 0);
		    versionName = packageInfo.versionName;
		    if (TextUtils.isEmpty(versionName)) {
		    	return "";
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return versionName;
    }

	private void initListener() {
		aboutdevice_activity_rl_systemupdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//ToastUtils.show(AboutDeviceActivity.this, "正在开发中！");
				w_sys();
			}
		});

		aboutdevice_activity_rl_legalinformation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ToastUtils.show(AboutDeviceActivity.this, "正在开发中！");
			}
		});
	}

    private void initView() {
    	aboutdevice_activity_header = (Header) findViewById(R.id.aboutdevice_activity_header);
    	aboutdevice_activity_tv_modelnumber = (TextView) findViewById(R.id.aboutdevice_activity_tv_modelnumber);
    	aboutdevice_activity_tv_androidversion = (TextView) findViewById(R.id.aboutdevice_activity_tv_androidversion);
    	aboutdevice_activity_tv_firmwareversion = (TextView) findViewById(R.id.aboutdevice_activity_tv_firmwareversion);
    	aboutdevice_activity_tv_kernelversion = (TextView) findViewById(R.id.aboutdevice_activity_tv_kernelversion);
    	aboutdevice_activity_tv_versionnumber = (TextView) findViewById(R.id.aboutdevice_activity_tv_versionnumber);
    	aboutdevice_activity_rl_systemupdate = (RelativeLayout) findViewById(R.id.aboutdevice_activity_rl_systemupdate);
    	aboutdevice_activity_rl_legalinformation = (RelativeLayout) findViewById(R.id.aboutdevice_activity_rl_legalinformation);
	}

    private void w_sys() {
		Double oldversion = Double.parseDouble(ContextUtil.getAppVersionName(AboutDeviceActivity.this)[0]);
    	String version = "6";
    	if(version !=null && !"".equals(version)){
			if (Double.parseDouble(version) > oldversion) {
				// 强制升级
				Result = -10;
			} else if (Double.parseDouble(version) > oldversion) {
				Result = -11;
			}
			dealResult(Result, "请升级到最新版···");
		}
    }

    private void dealResult(int Result, String upgrademsg) {
		if (Result == 0) {

			ToastUtils.show(AboutDeviceActivity.this, "该版本已经是最新版！");
		} else if (Result == -10) {// 强制升级
			showUpgradeForce(upgrademsg);
		} else if (Result == -11) {// 提示更新
			showUpgradeTip(upgrademsg);
		} else {
			ToastUtils.show(AboutDeviceActivity.this, "未知错误码");
		}
	}

    /**
	 * 提示升级提示框
	 *
	 * @param upgrademsg
	 */
	public void showUpgradeTip(String upgrademsg) {
		final Dialog dialog = new Dialog(this, R.style.dialog);
	    dialog.setContentView(R.layout.uodatedialog);
	    Button qd = (Button) dialog.getWindow().findViewById(R.id.qdbtn);
	    Button qx = (Button) dialog.getWindow().findViewById(R.id.qxbtn);
	    TextView tstv = (TextView) dialog.getWindow().findViewById(R.id.tstv);
	    tstv.setText(upgrademsg);

	    qd.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {
		        dialog.dismiss();
				showCustomMessageOK();
		      }
		});
	    qx.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {
		        dialog.dismiss();
		      }
	    });
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 强制升级提示框
	 *
	 * @param upgrademsg
	 */
	public void showUpgradeForce(String upgrademsg) {
		final Dialog dialog = new Dialog(this, R.style.dialog);
	    dialog.setContentView(R.layout.uodatedialog);
	    Button qd = (Button) dialog.getWindow().findViewById(R.id.qdbtn);
	    Button qx = (Button) dialog.getWindow().findViewById(R.id.qxbtn);
	    TextView tstv = (TextView) dialog.getWindow().findViewById(R.id.tstv);
	    tstv.setText(upgrademsg);

	    qd.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {
		        dialog.dismiss();
				showCustomMessageOK();
		      }
		});
	    qx.setOnClickListener(new OnClickListener() {
		      @Override
		      public void onClick(View v) {
		        dialog.dismiss();
				AboutDeviceActivity.this.finish();
		      }
	    });
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 弹出更新提示框
	 */
	private void showCustomMessageOK() {
		final Dialog dialog = new Dialog(this, R.style.dialog);
	    dialog.setContentView(R.layout.customdialog);
	    Button qx = (Button) dialog.getWindow().findViewById(R.id.qxbtn);
	    TextView tstv = (TextView) dialog.getWindow().findViewById(R.id.tstv);
		mProgress = (ProgressBar) dialog.findViewById(R.id.progress);

	    qx.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        dialog.dismiss();
			//handler.stop();
	      }
	    });
	    downLoadApk(tstv, dialog);
	    dialog.setCancelable(true);
		dialog.show();
	}

	/**
	 * 下载apk
	 * 2016-3-30 hjy gai
	 * @param dialog_message
	 */
	private void downLoadApk(TextView dialog_message, final Dialog dialog) {
//		final DownLoadManager updateManager = new DownLoadManager();
//		String url = PreferencesUtils.getString(AboutDeviceActivity.this,"downurl");
//		target = updateManager.createApkTarget(AboutDeviceActivity.this);
//		if (TextUtils.isEmpty(target)) {
//			Uri myBlogUri = null;
//			if (url == null || "".equalsIgnoreCase(url)) {
//				url = "http://219.137.26.249:8087/falaapp/fala_dft_gz20160318.apk";
//			}
//			myBlogUri = Uri.parse(url);
//			Intent returnIt = new Intent(Intent.ACTION_VIEW, myBlogUri);
//			AboutDeviceActivity.this.startActivity(returnIt);
//			AboutDeviceActivity.this.finish();
//		} else {
//			String apkName = url.substring(url.lastIndexOf("/") + 1);// 接口名称
//			target = target + apkName;
//			File apkfile = new File(target);
//			if (apkfile.exists()) {
//				apkfile.delete();
//			}
//			dialog_message.setText("正在下载最新版安装包,该包存放路径为" + target+ "，如在安装过程中出现问题请手动到该包存放路径中进行安装，谢谢。");

//			handler = updateManager.downLoadApk(AboutDeviceActivity.this, url,
//					target, true, new NetCallBack<File>() {
//						@Override
//						public void onFailure(Throwable t, String strMsg) {
//							Log.e("onFailure",strMsg);
//							super.onFailure(t, strMsg);
//							if (strMsg.contains("416")) {
//								installApk(target);
//								dialog.dismiss();
//								AboutDeviceActivity.this.finish();
//							}
//						}
//
//						@Override
//						public void onLoading(long count, long current) {
//							super.onLoading(count, current);
//							progress = (int) (((float) current / count) * 100);
//							// 更新进度
//							mHandler.sendEmptyMessage(DOWN_UPDATE);
//							Log.e("onLoading", "onLoading");
//						}
//
//						@Override
//						public void onSuccess(File arg0) {
//							Log.e("onSuccess", "onSuccess");
//							installApk(target);
//							dialog.dismiss();
//							AboutDeviceActivity.this.finish();
//						}
//					});
		//}
	}

	/**
	 * 安装指定地址(filePath)的apk
	 */
	private void installApk(String filePath) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.parse("file://" + filePath),"application/vnd.android.package-archive");
		startActivity(i);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				Object object = msg.obj;
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:

				break;
			default:
				break;
			}
		};
	};
}
