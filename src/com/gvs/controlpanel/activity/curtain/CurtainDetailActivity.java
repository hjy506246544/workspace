package com.gvs.controlpanel.activity.curtain;
import greendao.CurtainEntity;
import greendao.DBHelper;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.edwin.activity.AppIcon;
import com.gvs.edwin.activity.IconAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
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

public class CurtainDetailActivity extends Activity implements
		OnItemClickListener, OnItemLongClickListener {
	AppIcon appicon;
	public Header header;
	public ArrayList<String> mNameList;
	public ArrayList<Drawable> mDrawableList;
	public boolean isDeleteMode = false;
	boolean isType1 = false;
	private DBHelper dBManager;
    private ProgressDialog progressDialog;

	public boolean getDeleteMode() {
		return isDeleteMode;
	}

	public void setDeleteMode(boolean isDeleteMode) {
		this.isDeleteMode = isDeleteMode;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_curtain);
		dBManager = DBHelper.getInstance(this);
		appicon = (AppIcon) findViewById(R.id.gridview_curtain);
		header = (Header) findViewById(R.id.curtain_activity_header);

		mNameList = new ArrayList<String>();
		mDrawableList = new ArrayList<Drawable>();

		appicon.setAdapter(new IconAdapter(this, mNameList, mDrawableList));
		appicon.setOnItemClickListener(this);
		appicon.setOnItemLongClickListener(this);

		//	    弹出要给ProgressDialog
        progressDialog = new ProgressDialog(CurtainDetailActivity.this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在加载中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为圆圈的形式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        /*
		List<CurtainEntity> listentity = dBManager.loadAllCurtainEntity();
		if (!listentity.isEmpty()) {
			for (int i = 0; i < listentity.size(); i++) {
				CurtainEntity tmpEntity = listentity.get(i);

				mNameList.add(tmpEntity.getStrText());
				mDrawableList.add(getResources().getDrawable(
						tmpEntity.getIconId()));

			}
		}
		*/
        CurtainAsyncTask asyncTask = new CurtainAsyncTask();
        asyncTask.execute(500);

		header.setTitle(getResources().getString(R.string.curtain_title));

		header.setLeftImageVewRes(R.drawable.btn_return, new OnClickListener() {

			@Override
			public void onClick(View v) {
				CurtainDetailActivity.this.finish();
			}
		});

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
		// List<CurtainEntity> mList;
		//
		// mList = dBManager.select(mNameList
		// .get(position));
		//
		// Log.d("TAG", String.valueOf(position)+mNameList
		// .get(position));
		//
		//
		//
		//
		// if (mList.isEmpty()) {
		// ToastUtils.show(CurtainDetailActivity.this, "删除的对象不存在");
		// return;
		//
		// } else {
		// dBManager.DeleteCurtainEntityById(mList.get(0).getId());
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
		// showCurtainDialogTip();
		//
		// return;
		// } else {
		Intent mIntent = new Intent(CurtainDetailActivity.this,
				Activity_curtain_control.class);
		mIntent.putExtra("TITLE_NAME", mNameList.get(position));
		startActivity(mIntent);
		// }

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		// setDeleteMode(true);
		// Toast.makeText(CurtainDetailActivity.this,
		// getResources().getString(R.string.delete_success),
		// Toast.LENGTH_SHORT).show();

		return false;
	}

	public void showCurtainDialogTip() {

		final Dialog dialog = new Dialog(this, R.style.dialog);
		dialog.setContentView(R.layout.config_dialog_curtain);
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
				List<CurtainEntity> mList;

				if (TextUtils.isEmpty(nametxt)) {
					ToastUtils.show(CurtainDetailActivity.this, getResources()
							.getString(R.string.add_curtain_name));
					return;
				} else {
					mList = dBManager.select(nametxt);
					if (mList.size() >= 1) {
						ToastUtils.show(
								CurtainDetailActivity.this,
								getResources().getString(
										R.string.name_have_exist));
						return;
					}
				}
				mNameList.add(mDrawableList.size() - 1, nametxt);
				mDrawable = R.drawable.icon_curtain_type1;
				if (isType1)
					mDrawable = R.drawable.icon_curtain_type1;

				else
					mDrawable = R.drawable.icon_curtain_type2;

				mDrawableList.add(mDrawableList.size() - 1, getResources()
						.getDrawable(mDrawable));

				appicon.setAdapter(new IconAdapter(CurtainDetailActivity.this,
						mNameList, mDrawableList));

				CurtainEntity entity = new CurtainEntity();
				entity.setIconId(mDrawable);
				entity.setStrText(nametxt);
				dBManager.saveCurtainEntity(entity);

				ToastUtils.show(CurtainDetailActivity.this, getResources()
						.getString(R.string.add_curtain_ok));
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
    public class CurtainAsyncTask extends AsyncTask<Integer, Integer, String>{

		@Override
	    protected void onPreExecute(){
	        super.onPreExecute();
	        //    在onPreExecute()中我们让ProgressDialog显示出来
	        progressDialog.show();
	    }
	    @Override
	    protected String doInBackground(Integer... params) {
	    	List<CurtainEntity> listentity = dBManager.loadAllCurtainEntity();
			if (!listentity.isEmpty()) {
				for(int i=0;i<=params.length;i++){
					progressDialog.setProgress(i);
					publishProgress(i);
					try {
						for (int j = 0; j < listentity.size(); j++) {
							CurtainEntity tmpEntity = listentity.get(j);

							mNameList.add(tmpEntity.getStrText());
							mDrawableList.add(getResources().getDrawable(
									tmpEntity.getIconId()));

						}
						Thread.sleep(params[0]);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
	    	return "执行完毕";
	    }
	    @Override
	    protected void onProgressUpdate(Integer... values){
	        super.onProgressUpdate(values);
	    }
	    @Override
	    protected void onPostExecute(String result){
	        super.onPostExecute(result);
	        //    使ProgressDialog框消失
	        progressDialog.dismiss();
	    }
	}
}
