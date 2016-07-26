package com.gvs.controlpanel.activity.systemset;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.WifiAdapter;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.util.WifiAdmin;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.MyListView;
import com.gvs.controlpanel.widget.MyListView.OnRefreshListener;
import com.gvs.controlpanel.widget.OnNetworkChangeListener;
import com.gvs.controlpanel.widget.WifiConnDialog;
import com.gvs.controlpanel.widget.WifiStatusDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
/**
 * wifi主界面
 * 2016-7-26
 * @author hjy
 */
public class WifiActivity extends FragmentActivityBase {
	public Header systemset_activity_wifiheader;
	static Context context;
    private ToggleButton systemset_activity_wifislideswitch;
	private WifiAdapter wifiAdapter;
	protected static final String TAG = WifiActivity.class.getSimpleName();
	private static final int REFRESH_CONN = 100;
	private static final int REQ_SET_WIFI = 200;
	// Wifi管理类
	private WifiAdmin mWifiAdmin;
	// 扫描结果列表
	private List<ScanResult> list = new ArrayList<ScanResult>();
	// 显示列表
	private MyListView listView;

	private OnNetworkChangeListener mOnNetworkChangeListener = new OnNetworkChangeListener() {

		@Override
		public void onNetWorkDisConnect() {
			getWifiListInfo();
			wifiAdapter.setDatas(list);
			wifiAdapter.notifyDataSetChanged();
		}

		@Override
		public void onNetWorkConnect() {
			getWifiListInfo();
			wifiAdapter.setDatas(list);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wifiAdapter.notifyDataSetChanged();
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemset_wifi_list);
		initView();
		initData();
		initListener();
		refreshWifiStatusOnTime();
    }

    private void getWifiListInfo() {
		mWifiAdmin.startScan();
		List<ScanResult> tmpList = mWifiAdmin.getWifiList();
		if (tmpList == null) {
			list.clear();
		} else {
			list = tmpList;
		}
	}

    private void initData() {
    	systemset_activity_wifiheader.setTitle(getResources().getString(R.string.wifi_systemset));

    	systemset_activity_wifiheader.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				WifiActivity.this.finish();
			}
		});

		mWifiAdmin = new WifiAdmin(WifiActivity.this);
		// 获得Wifi列表信息
		getWifiListInfo();
		wifiAdapter = new WifiAdapter(WifiActivity.this, list);
		listView.setAdapter(wifiAdapter);
		int wifiState = mWifiAdmin.checkState();
		if (wifiState == WifiManager.WIFI_STATE_DISABLED
				|| wifiState == WifiManager.WIFI_STATE_DISABLING
				|| wifiState == WifiManager.WIFI_STATE_UNKNOWN) {
			systemset_activity_wifislideswitch.setChecked(false);
		} else {
			systemset_activity_wifislideswitch.setChecked(true);
		}
	}

	private void initListener() {
		systemset_activity_wifislideswitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Log.w(TAG, "======== open wifi ========");
					// 打开Wifi
					mWifiAdmin.openWifi();
				} else {
					Log.w(TAG, "======== close wifi ========");
					// 关闭Wifi
					mWifiAdmin.closeWifi();
				}
			}
		});

		// 设置刷新监听
		listView.setonRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {

				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						getWifiListInfo();
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						wifiAdapter.setDatas(list);
						wifiAdapter.notifyDataSetChanged();
						listView.onRefreshComplete();
					}

				}.execute();
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				int position = pos - 1;
				ScanResult scanResult = list.get(position);

				String desc = "";
				String descOri = scanResult.capabilities;
				if (descOri.toUpperCase().contains("WPA-PSK")) {
					desc = "WPA";
				}
				if (descOri.toUpperCase().contains("WPA2-PSK")) {
					desc = "WPA2";
				}
				if (descOri.toUpperCase().contains("WPA-PSK")
						&& descOri.toUpperCase().contains("WPA2-PSK")) {
					desc = "WPA/WPA2";
				}

				if (desc.equals("")) {
					isConnectSelf(scanResult);
					return;
				}
				isConnect(scanResult);
			}

			private void isConnect(ScanResult scanResult) {
				if (mWifiAdmin.isConnect(scanResult)) {
					// 已连接，显示连接状态对话框
					WifiStatusDialog mStatusDialog = new WifiStatusDialog(
							WifiActivity.this, R.style.PopDialog,
							scanResult, mOnNetworkChangeListener);
					mStatusDialog.show();
				} else {
					// 未连接显示连接输入对话框
					WifiConnDialog mDialog = new WifiConnDialog(
							WifiActivity.this, R.style.PopDialog,
							scanResult, mOnNetworkChangeListener);
					mDialog.show();
				}
			}

			private void isConnectSelf(ScanResult scanResult) {
				if (mWifiAdmin.isConnect(scanResult)) {

					// 已连接，显示连接状态对话框
					WifiStatusDialog mStatusDialog = new WifiStatusDialog(
							WifiActivity.this, R.style.PopDialog,
							scanResult, mOnNetworkChangeListener);
					mStatusDialog.show();

				} else {
					boolean iswifi = mWifiAdmin.connectSpecificAP(scanResult);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (iswifi) {
						ToastUtils.show(WifiActivity.this, "连接成功！");
					}else {
						ToastUtils.show(WifiActivity.this, "连接失败！");
					}
				}
			}
		});
	}

    private void initView() {
    	systemset_activity_wifiheader = (Header) findViewById(R.id.systemset_activity_wifiheader);
    	listView = (MyListView) findViewById(R.id.systemset_activity_listView);
    	systemset_activity_wifislideswitch = (ToggleButton) findViewById(R.id.systemset_activity_wifislideswitch);
	}

    private Handler mHandler = new MyHandler(WifiActivity.this);

	protected boolean isUpdate = true;

	private static class MyHandler extends Handler {

		private WeakReference<WifiActivity> reference;

		public MyHandler(WifiActivity activity) {
			this.reference = new WeakReference<WifiActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {

			WifiActivity activity = reference.get();

			switch (msg.what) {
			case REFRESH_CONN:
				activity.getWifiListInfo();
				activity.wifiAdapter.setDatas(activity.list);
				activity.wifiAdapter.notifyDataSetChanged();
				break;

			default:
				break;
			}

			super.handleMessage(msg);
		}
	}

	/**
	 * Function:定时刷新Wifi列表信息<br>
	 * @author hjy<br>
	 * <br>
	 */
	private void refreshWifiStatusOnTime() {
		new Thread() {
			public void run() {
				while (isUpdate) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mHandler.sendEmptyMessage(REFRESH_CONN);
				}
			}
		}.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isUpdate = false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQ_SET_WIFI:
			// 处理改变wifi状态结果
			int wifiState = mWifiAdmin.checkState();
			if (wifiState == WifiManager.WIFI_STATE_DISABLED
					|| wifiState == WifiManager.WIFI_STATE_DISABLING
					|| wifiState == WifiManager.WIFI_STATE_UNKNOWN) {
				systemset_activity_wifislideswitch.setChecked(false);
			} else {
				systemset_activity_wifislideswitch.setChecked(true);
			}
			break;

		default:
			break;
		}
	}
}
