package common;

import android.content.Context;
import android.content.Intent;

public class StaticUnits {

	static String TAG_SERIAL_MSG = "serialmsg";
	public static String TAG_SERIAL_ACTION = "gom.gvs.serial.msg.action";

	static String ID_MainTwo_Activity = "com.gvs.controlpanel.activity.main.MainTwoActivity";
	public static String ID_Light_Activity = "com.gvs.controlpanel.activity.light.LightActivity";

	static void SendBroadcast(Context context, byte[] data, int size) {

		Intent intent = new Intent(TAG_SERIAL_ACTION);
		intent.putExtra(TAG_SERIAL_MSG, data);
		intent.putExtra(TAG_SERIAL_MSG, size);

		context.sendBroadcast(intent);
	}

	static void SerialTx(Byte[] data) {

	}

}
