package com.gvs.controlpanel.widget;

import com.gvs.controlpanel.R;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LoadingDialog extends DialogFragment
{

	private String mMsg = "正在加载中，请稍后......";

	public void setMsg(String msg)
	{
		this.mMsg = msg;
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.dialog_loading, null);
		TextView title = (TextView) view
				.findViewById(R.id.id_dialog_loading_msg);
		title.setText(mMsg);
		Dialog dialog = new Dialog(getActivity(), R.style.dialog);
		dialog.setContentView(view);
		return dialog;
	}
}
