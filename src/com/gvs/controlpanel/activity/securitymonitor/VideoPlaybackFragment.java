package com.gvs.controlpanel.activity.securitymonitor;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentBase;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 影像回放主界面
 * @author hjy
 * 2016-6-15
 */
public class VideoPlaybackFragment extends FragmentBase{
	private SecurityMonitorActivity securityMonitorActivity;

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
        View view =  inflater.inflate(R.layout.securitymonitor_videoplay_fragment, container, false);
        initView(view,savedInstanceState);
		return view;
	}

	private void initView(View view, Bundle savedInstanceState) {
	}


	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        InitListener();
    }

	private void InitListener() {
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
