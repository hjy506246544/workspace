package com.gvs.controlpanel.bean;
import cn.bmob.v3.BmobObject;
/**
 * 安防监控
 * @author hjy
 *
 */
public class Camera extends BmobObject{
	private String deviceName;
	private String deviceId;
	private String devicePwd;
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDevicePwd() {
		return devicePwd;
	}
	public void setDevicePwd(String devicePwd) {
		this.devicePwd = devicePwd;
	}
}
