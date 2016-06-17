package com.gvs.controlpanel.activity.securitymonitor;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentBase;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 监控预览主界面
 * @author hjy
 * 2016-6-15
 */
public class MonitorPreviewFragment extends FragmentBase{
	private SecurityMonitorActivity securityMonitorActivity;
	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        securityMonitorActivity = (SecurityMonitorActivity) activity;
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.securitymonitor_monitorpreview_fragment, container, false);
        initView(view,savedInstanceState);
		return view;
	}

	private void initView(View view, Bundle savedInstanceState) {
		tv = (TextView) view.findViewById(R.id.tv);
	}

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        InitListener();
    }

	private void InitListener() {
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				OpenActivity(AddMonitorPreviewActivity.class);
			}
		});
	}

	private void initData() {

	}

	@Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}