package com.gvs.controlpanel.adapter;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.systemset.WifiActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class WifiAdapter extends BaseAdapter{
	LayoutInflater inflater;
    List<ScanResult> list;
    public WifiActivity activity;
	// 取得WifiManager对象
	private WifiManager mWifiManager;
    public Context context;
	private WifiInfo connInfo;
	ConnectivityManager cm;
    public WifiAdapter(Context context, List<ScanResult> list) {
        // TODO Auto-generated constructor stub
        this.inflater = LayoutInflater.from(context);
        this.list = list;
		mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		connInfo = mWifiManager.getConnectionInfo();
		cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void setDatas(List<ScanResult> datas) {
		this.list = datas;
		connInfo = mWifiManager.getConnectionInfo();
	}

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        view = inflater.inflate(R.layout.systemset_item_wifi_list, null);
        ScanResult scanResult = list.get(position);
        TextView textView = (TextView) view.findViewById(R.id.systemset_item_tv);
        textView.setText(scanResult.SSID);
        TextView systemset_item_desc = (TextView) view.findViewById(R.id.systemset_item_desc);
        ImageView imageView = (ImageView) view.findViewById(R.id.systemset_item_iv);

        // Wifi 描述
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

 		if (TextUtils.isEmpty(desc)) {
 			desc = "未受保护的网络";
 		} else {
 			desc = "通过 " + desc + " 进行保护";
 		}

 		// 是否连接，如果刚刚断开连接，connInfo.SSID==null
 		connInfo = mWifiManager.getConnectionInfo();

 		State wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (wifi == State.CONNECTED) {
			WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
			String g1 = wifiInfo.getSSID();
			Log.e("g1============>", g1);
			Log.e("g2============>", scanResult.SSID);

			String g2 = "\""+scanResult.SSID+"\"";

			if (g2.endsWith(g1)) {
				desc = "已连接";
			}
		}

		systemset_item_desc.setText(desc);

        //判断信号强度，显示对应的指示图标
        if (Math.abs(scanResult.level) > 100) {
            imageView.setImageResource(R.drawable.stat_sys_wifi_signal_4);
        } else if (Math.abs(scanResult.level) > 80) {
            imageView.setImageResource(R.drawable.stat_sys_wifi_signal_3);
        } else if (Math.abs(scanResult.level) > 70) {
            imageView.setImageResource(R.drawable.stat_sys_wifi_signal_3);
        } else if (Math.abs(scanResult.level) > 60) {
            imageView.setImageResource(R.drawable.stat_sys_wifi_signal_2);
        } else if (Math.abs(scanResult.level) > 50) {
            imageView.setImageResource(R.drawable.stat_sys_wifi_signal_1);
        } else {
            imageView.setImageResource(R.drawable.stat_sys_wifi_signal_0);
        }
        return view;
    }
}
