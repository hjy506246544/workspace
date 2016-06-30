package com.gvs.controlpanel.activity.aircondition;
import greendao.DBHelper;
import greendao.ACEntity;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.curtain.CurtainDetailActivity;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.edwin.activity.AppIcon;
import com.gvs.edwin.activity.IconAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
/**
 * 空调界面
 * @author hjy
 * 2016-06-30
 */
public class AirConditionActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {
	AppIcon appicon;
	public Header header;
	public ArrayList<String> mNameList;
	public ArrayList<Drawable> mDrawableList;
	public boolean isDeleteMode = false;
	boolean isType1 = false;
	private DBHelper dBManager;

	public boolean getDeleteMode() {
		return isDeleteMode;
	}

	public void setDeleteMode(boolean isDeleteMode) {
		this.isDeleteMode = isDeleteMode;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ac);
		dBManager = DBHelper.getInstance(this);
		appicon = (AppIcon) findViewById(R.id.gridview_ac);
		header = (Header) findViewById(R.id.activity_header);

		mNameList = new ArrayList<String>();
		mDrawableList = new ArrayList<Drawable>();

		appicon.setAdapter(new IconAdapter(this, mNameList, mDrawableList));
		appicon.setOnItemClickListener(this);
		appicon.setOnItemLongClickListener(this);

		List<ACEntity> listentity = dBManager.loadAllACEntity();
		if (listentity.size() >= 1) {
			for (int i = 0; i < listentity.size(); i++) {
				ACEntity tmpEntity = listentity.get(i);

				mNameList.add(tmpEntity.getStrText());
				mDrawableList.add(getResources().getDrawable(
						tmpEntity.getIconId()));

			}
		}
		mNameList.add(getString(R.string.controlcenter_add_new));

		mDrawableList.add(getResources().getDrawable(R.drawable.btn_add_new));

		header.setTitle(getResources().getString(R.string.aircondition_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				AirConditionActivity.this.finish();
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		if (getDeleteMode()) {

			if ((mNameList.size() - 1) != position) {
				// delete


				List<ACEntity> mList;

				mList = dBManager.select_AC(mNameList
						.get(position));

				Log.d("TAG", String.valueOf(position)+mNameList
						.get(position));




				if (mList.isEmpty()) {
					ToastUtils.show(AirConditionActivity.this, "删除的对象不存在");
					return;

				} else {
					dBManager.DeleteACEntityById(mList.get(0).getId());
				}
				mNameList.remove(position);
				mDrawableList.remove(position);
				appicon.setAdapter(new IconAdapter(this, mNameList,
						mDrawableList));
			}
			setDeleteMode(false);
			return;
		}
		if ((mNameList.size() - 1) == position) {
			// add
			showACDialogTip();

			return;
		} else {
			Intent mIntent=new Intent(AirConditionActivity.this,
					Activity_AC_Control.class);
			mIntent.putExtra("TITLE_NAME", mNameList
					.get(position));
			startActivity(mIntent);
		}

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		setDeleteMode(true);
		Toast.makeText(AirConditionActivity.this, getResources().getString(R.string.delete_success),
				Toast.LENGTH_SHORT).show();

		return false;
	}

	public void showACDialogTip() {

		final Dialog dialog = new Dialog(this, R.style.dialog);
		dialog.setContentView(R.layout.config_dialog_ac);
		final EditText nameet = (EditText) dialog.getWindow().findViewById(
				R.id.nameet);
		RadioGroup modeclll = (RadioGroup) dialog.getWindow().findViewById(
				R.id.config_type_radio_group_curtain);
		Button qrbtn = (Button) dialog.getWindow().findViewById(R.id.qdbtn);

		modeclll.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (v.getId() == R.id.RadioButton_curtain_type2) {
					isType1 = true;
				} else if (v.getId() == R.id.RadioButton_curtain_type1) {
					isType1 = false;

				}
			}
		});

		qrbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String nametxt = nameet.getText().toString();
				int mDrawable;
				List<ACEntity> mList;

				if (TextUtils.isEmpty(nametxt)) {
					ToastUtils.show(AirConditionActivity.this, getResources().getString(R.string.add_curtain_name));
					return;
				} else {
					mList = dBManager
							.select_AC(nametxt);
					if (mList.size() >= 1) {
						ToastUtils.show(AirConditionActivity.this, getResources().getString(R.string.name_have_exist));
						return;
					}
				}
				mNameList.add(mDrawableList.size()-1, nametxt);
				mDrawable = R.drawable.icon_kt_gs;
				if (isType1)
					mDrawable = R.drawable.icon_kt_gs;

				else
					mDrawable = R.drawable.icon_kt_ls;

				mDrawableList.add(mDrawableList.size()-1, getResources()
						.getDrawable(mDrawable));

				appicon.setAdapter(new IconAdapter(AirConditionActivity.this,
						mNameList, mDrawableList));

				ACEntity entity = new ACEntity();
				entity.setIconId(mDrawable);
				entity.setStrText(nametxt);
				dBManager.saveACEntity(entity);

				ToastUtils.show(AirConditionActivity.this, getResources().getString(R.string.add_curtain_ok));
				dialog.dismiss();
			}
		});
		dialog.setCancelable(true);
		dialog.show();
	}
}
