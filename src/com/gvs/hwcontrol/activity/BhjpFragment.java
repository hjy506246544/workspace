package com.gvs.hwcontrol.activity;

import com.gvs.hwcontrol.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 拨号键盘主界面
 * 2016-5-13
 * @author hjy
 *
 */
public class BhjpFragment extends Fragment {
	private MainActivity mainActivity;

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.bhjpactivity, container, false);
        initView(view,savedInstanceState);
		return view;
    }

    private void initView(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub

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
