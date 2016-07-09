package com.gvs.controlpanel.activity.securitymonitor;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.bean.Camera;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/**
 * 添加摄像头界面
 * 2016-6-16
 * @author hjy
 */
public class AddMonitorPreviewActivity extends FragmentActivityBase {
	public Header header;
	private Button tjbtn;
	private EditText devicenameet,deviceidet,devicepwdet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securitymonitor_monitorpreview_addactivity);
		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.addsxt_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddMonitorPreviewActivity.this.finish();
			}
		});
	}

	private void initListener() {
		tjbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String devicename = devicenameet.getText().toString();
				String deviceid = deviceidet.getText().toString();
				String devicepwd = devicepwdet.getText().toString();
				if(TextUtils.isEmpty(devicename)){
					ToastUtils.show(AddMonitorPreviewActivity.this,"请填写设备名称");
					return;
				}
				if(TextUtils.isEmpty(deviceid)){
					ToastUtils.show(AddMonitorPreviewActivity.this,"请填写设备序列号");
					return;
				}
				if(TextUtils.isEmpty(devicepwd)){
					ToastUtils.show(AddMonitorPreviewActivity.this,"请填写设备密码");
					return;
				}
				Camera camera = new Camera();
				camera.setDeviceName(devicename);
				camera.setDeviceId(deviceid);
				camera.setDevicePwd(devicepwd);
//				camera.save(AddMonitorPreviewActivity.this, new SaveListener() {
//
//					@Override
//					public void onSuccess() {
//						ToastUtils.show(AddMonitorPreviewActivity.this,"添加摄像头成功!");
//						setResult(RESULT_OK);
//						finish();
//					}
//
//					@Override
//					public void onFailure(int arg0, String arg1) {
//						ToastUtils.show(AddMonitorPreviewActivity.this,"添加摄像头失败:"+arg0);
//					}
//				});
			}
		});
	}

    private void initView() {
		header = (Header) findViewById(R.id.activity_addmonitorpreview_header);
		devicepwdet = (EditText) findViewById(R.id.activity_addmonitorpreview_devicepwdet);
		deviceidet = (EditText) findViewById(R.id.activity_addmonitorpreview_deviceidet);
		devicenameet = (EditText) findViewById(R.id.activity_addmonitorpreview_devicenameet);
		tjbtn = (Button) findViewById(R.id.activity_addmonitorpreview_tjbtn);
	}
}
