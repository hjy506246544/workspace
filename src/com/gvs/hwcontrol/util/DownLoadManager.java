package com.gvs.hwcontrol.util;

import java.io.File;

import net.tsz.afinal.http.HttpHandler;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import cellcom.com.cn.net.base.CellComHttpInterface.NetCallBack;
import cellcom.com.cn.util.LogMgr;
import com.gvs.hwcontrol.net.HttpHelper;

public class DownLoadManager {
	public HttpHandler<File> downLoadApk(Context context, String path,
			String target, boolean isResume, NetCallBack<File> netCallBack) {
		return HttpHelper.getInstances(context).downLoad(path, target,
				isResume, netCallBack);
	}

	public String createApkTarget(Context context) {
		String savePath = "";
		try {
			String sdPath = getSDPath();
			if (sdPath == null || "".equals(sdPath)) {
				Toast.makeText(context, "SD卡不存在", Toast.LENGTH_LONG).show();
			} else {
				if (!sdPath.endsWith("/")) {
					savePath = sdPath + "/cellcom/";
				} else {
					savePath = sdPath + "cellcom/";
				}
				LogMgr.showLog("savePath=" + savePath);
				File file = new File(savePath);
				if (!file.exists()) {
					if (!file.mkdir()) {
						if (!sdPath.endsWith("/")) {
							savePath += "/";
						}
					}
				}
			}
			return savePath;
		} catch (Exception e) {
			LogMgr.showLog("createApkTarget:" + e.getMessage());
		}
		return savePath;
	}

	private String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
			return sdDir.toString();
		} else {
			return "";
		}
	}

}
