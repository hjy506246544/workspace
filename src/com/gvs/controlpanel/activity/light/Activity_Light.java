package com.gvs.controlpanel.activity.light;
import greendao.DBHelper;
import greendao.bean.LightEntity;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.LoadingDialog;
import com.gvs.edwin.activity.AppIcon;
import com.gvs.edwin.activity.IconAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * 灯光主界面
 *
 * @author hjy 2016-0630
 */
public class Activity_Light extends Activity implements OnItemClickListener,
		OnItemLongClickListener {
	AppIcon appicon;
	public Header header;
	public ArrayList<String> mNameList;
	public ArrayList<Drawable> mDrawableList;
	public boolean isDeleteMode = false;
	boolean isType1 = false;
	private DBHelper dBManager;
	private DialogFragment mLoadingDialog;

	public boolean getDeleteMode() {
		return isDeleteMode;
	}

	public void setDeleteMode(boolean isDeleteMode) {
		this.isDeleteMode = isDeleteMode;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);
		dBManager = DBHelper.getInstance(this);
		appicon = (AppIcon) findViewById(R.id.gridview_light);
		header = (Header) findViewById(R.id.light_activity_header);

		header.setTitle(getResources().getString(R.string.light_title));
		header.setLeftImageVewRes(R.drawable.btn_return, new OnClickListener() {

			@Override
			public void onClick(View v) {
				Activity_Light.this.finish();
			}
		});

		appicon.setOnItemClickListener(this);
		appicon.setOnItemLongClickListener(this);

		mLoadingDialog = new LoadingDialog();
		mLoadingDialog.show(getFragmentManager(), "LoadingDialog");
		LightAsyncTask asyncTask = new LightAsyncTask();
		asyncTask.execute(500);
		/*
		List<LightEntity> listentity = dBManager.loadAllLightEntity();
		if (!listentity.isEmpty()) {
			for (int i = 0; i < listentity.size(); i++) {
				LightEntity tmpEntity = listentity.get(i);

				mNameList.add(tmpEntity.getStrText());
				mDrawableList.add(getResources().getDrawable(
						tmpEntity.getIconId()));

			}
		}
		*/

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		// if (getDeleteMode()) {
		//
		// if ((mNameList.size() - 1) != position) {
		// // delete
		//
		//
		// List<LightEntity> mList;
		//
		// mList = dBManager.select_Light(mNameList
		// .get(position));
		//
		// Log.d("TAG", String.valueOf(position)+mNameList
		// .get(position));
		//
		//
		//
		//
		// if (mList.isEmpty()) {
		// ToastUtils.show(Activity_Light.this, "删除的对象不存在");
		// return;
		//
		// } else {
		// dBManager.DeleteLightEntityById(mList.get(0).getId());
		// }
		// mNameList.remove(position);
		// mDrawableList.remove(position);
		// appicon.setAdapter(new IconAdapter(this, mNameList,
		// mDrawableList));
		// }
		// setDeleteMode(false);
		// return;
		// }
		// if ((mNameList.size() - 1) == position) {
		// // add
		// showLightDialogTip();
		//
		// return;
		// } else {
		Intent mIntent = new Intent(Activity_Light.this,
				Activity_Light_Control.class);
		mIntent.putExtra("TITLE_NAME", mNameList.get(position));
		startActivity(mIntent);
		// }

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		// setDeleteMode(true);
		// Toast.makeText(Activity_Light.this,
		// getResources().getString(R.string.delete_success),
		// Toast.LENGTH_SHORT).show();

		return false;
	}

	public void showLightDialogTip() {

		final Dialog dialog = new Dialog(this, R.style.dialog);
		dialog.setContentView(R.layout.config_dialog_light);
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
				List<LightEntity> mList;

				if (TextUtils.isEmpty(nametxt)) {
					ToastUtils.show(Activity_Light.this, getResources()
							.getString(R.string.add_curtain_name));
					return;
				} else {
					mList = dBManager.select_Light(nametxt);
					if (mList.size() >= 1) {
						ToastUtils.show(Activity_Light.this, getResources()
								.getString(R.string.name_have_exist));
						return;
					}
				}
				mNameList.add(mDrawableList.size() - 1, nametxt);
				mDrawable = R.drawable.icon_light_droplight;
				if (isType1)
					mDrawable = R.drawable.icon_light_droplight;

				else
					mDrawable = R.drawable.icon_light_energysaving;

				mDrawableList.add(mDrawableList.size() - 1, getResources()
						.getDrawable(mDrawable));

				appicon.setAdapter(new IconAdapter(Activity_Light.this,
						mNameList, mDrawableList));

				LightEntity entity = new LightEntity();
				entity.setIconId(mDrawable);
				entity.setStrText(nametxt);
				dBManager.saveLightEntity(entity);

				ToastUtils.show(Activity_Light.this,
						getResources().getString(R.string.add_curtain_ok));
				dialog.dismiss();
			}
		});
		dialog.setCancelable(true);
		dialog.show();
	}

	/**
     * 定义一个类，让其继承AsyncTask这个类
     * Params: String类型，表示传递给异步任务的参数类型是String，通常指定的是URL路径
     * Progress: Integer类型，进度条的单位通常都是Integer类型
     * Result：String类型
     * @author hjy
     *
     */
    public class LightAsyncTask extends AsyncTask<Integer, Integer, String>{
	    @Override
	    protected String doInBackground(Integer... params) {
	    	List<LightEntity> listentity = dBManager.loadAllLightEntity();
	    	mNameList = new ArrayList<String>();
			mDrawableList = new ArrayList<Drawable>();
			if (!listentity.isEmpty()) {
				try {
					for (int j = 0; j < listentity.size(); j++) {
						LightEntity tmpEntity = listentity.get(j);

						mNameList.add(tmpEntity.getStrText());
						mDrawableList.add(getResources().getDrawable(
								tmpEntity.getIconId()));

					}
					Thread.sleep(params[0]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    	return null;
	    }

	    @Override
	    protected void onPostExecute(String result){
	        super.onPostExecute(result);
	        //    使ProgressDialog框消失
	        mLoadingDialog.dismiss();
			appicon.setAdapter(new IconAdapter(Activity_Light.this, mNameList, mDrawableList));
	    }
	}
}
