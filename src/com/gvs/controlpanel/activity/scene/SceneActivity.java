package com.gvs.controlpanel.activity.scene;

import greendao.SceneEntity;
import greendao.DBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.aircondition.AirConditionActivity;
import com.gvs.controlpanel.activity.main.MainMenuActivity;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SceneActivity extends Activity implements OnItemClickListener,
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
		setContentView(R.layout.activity_scene);
		dBManager = DBHelper.getInstance(this);
		appicon = (AppIcon) findViewById(R.id.gridview_scene);
		header = (Header) findViewById(R.id.activity_header);

		mNameList = new ArrayList<String>();
		mDrawableList = new ArrayList<Drawable>();

		appicon.setAdapter(new IconAdapter(this, mNameList, mDrawableList));
		appicon.setOnItemClickListener(this);
		appicon.setOnItemLongClickListener(this);

		List<SceneEntity> listentity = dBManager.loadAllSceneEntity();
		if (listentity.size() >= 1) {
			for (int i = 0; i < listentity.size(); i++) {
				SceneEntity tmpEntity = listentity.get(i);

				mNameList.add(tmpEntity.getStrText());
				mDrawableList.add(getResources().getDrawable(
						tmpEntity.getIconId()));

			}
		}
		mNameList.add(getString(R.string.controlcenter_add_new));

		mDrawableList.add(getResources().getDrawable(R.drawable.btn_add_new));

		header.setTitle(getResources().getString(R.string.scene_title));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				SceneActivity.this.finish();
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


				List<SceneEntity> mList;

				mList = dBManager.select_Scene(mNameList
						.get(position));

				Log.d("TAG", String.valueOf(position)+mNameList
						.get(position));




				if (mList.isEmpty()) {
					ToastUtils.show(SceneActivity.this, "删除的对象不存在");
					return;

				} else {
					dBManager.DeleteSceneEntityById(mList.get(0).getId());
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
			startActivity(new Intent(SceneActivity.this,AddSceneActivity.class));

			return;
		} else {
			Intent mIntent=new Intent(SceneActivity.this,
					MainMenuActivity.class);

			startActivity(mIntent);
		}

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		setDeleteMode(true);
		Toast.makeText(SceneActivity.this, getResources().getString(R.string.delete_success),
				Toast.LENGTH_SHORT).show();

		return false;
	}

}
