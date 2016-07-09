package com.gvs.controlpanel.activity.scene;
import greendao.SceneEntity;
import greendao.DBHelper;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.main.MainMenuActivity;
import com.gvs.controlpanel.widget.Header;
import com.gvs.edwin.activity.AppIcon;
import com.gvs.edwin.activity.IconAdapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class SceneActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {
	AppIcon appicon;
	public Header header;
	public ArrayList<String> mNameList;
	public ArrayList<Drawable> mDrawableList;
	List<SceneEntity> listentity;
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
		setContentView(R.layout.activity_scene);
		dBManager = DBHelper.getInstance(this);
		appicon = (AppIcon) findViewById(R.id.gridview_scene);
		header = (Header) findViewById(R.id.activity_header);

		mNameList = new ArrayList<String>();
		mDrawableList = new ArrayList<Drawable>();

		appicon.setAdapter(new IconAdapter(this, mNameList, mDrawableList));
		appicon.setOnItemClickListener(this);
		appicon.setOnItemLongClickListener(this);

		//	    弹出要给ProgressDialog
        progressDialog = new ProgressDialog(SceneActivity.this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在加载中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为圆圈的形式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		/*
		listentity = dBManager.loadAllSceneEntity();
		if (!listentity.isEmpty()) {
			for (int i = 0; i < listentity.size(); i++) {
				SceneEntity tmpEntity = listentity.get(i);

				if (tmpEntity.getStrType().contains("_main_menu")) {
					mNameList.add(tmpEntity.getStrName());

					if (tmpEntity.getStrType().contains("_auto")) {
						mDrawableList.add(getResources().getDrawable(
								R.drawable.btn_add_new));
					} else if (tmpEntity.getStrType().contains("_morning")) {
						mDrawableList.add(getResources().getDrawable(
								R.drawable.btn_add_new));
					} else if (tmpEntity.getStrType().contains("_idle")) {
						mDrawableList.add(getResources().getDrawable(
								R.drawable.btn_add_new));
					}

				}

			}
		}
		*/
        SceneAsyncTask asyncTask = new SceneAsyncTask();
        asyncTask.execute(500);

		header.setTitle(getResources().getString(R.string.scene_title));

		header.setLeftImageVewRes(R.drawable.btn_return, new OnClickListener() {

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
		// if (getDeleteMode()) {
		//
		// if ((mNameList.size() - 1) != position) {
		// // delete
		//
		//
		// List<SceneEntity> mList;
		//
		// mList = dBManager.select_Scene(mNameList
		// .get(position));
		//
		// Log.d("TAG", String.valueOf(position)+mNameList
		// .get(position));
		//
		//
		//
		//
		// if (mList.isEmpty()) {
		// ToastUtils.show(SceneActivity.this, "删除的对象不存在");
		// return;
		//
		// } else {
		// dBManager.DeleteSceneEntityById(mList.get(0).getId());
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
		// startActivity(new Intent(SceneActivity.this,AddSceneActivity.class));
		//
		// return;
		// } else {
		Intent mIntent = new Intent(SceneActivity.this, MainMenuActivity.class);

		startActivity(mIntent);
		// }

	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		// setDeleteMode(true);
		// Toast.makeText(SceneActivity.this,
		// getResources().getString(R.string.delete_success),
		// Toast.LENGTH_SHORT).show();

		return false;
	}

	/**
     * 定义一个类，让其继承AsyncTask这个类
     * Params: String类型，表示传递给异步任务的参数类型是String，通常指定的是URL路径
     * Progress: Integer类型，进度条的单位通常都是Integer类型
     * Result：String类型
     * @author hjy
     *
     */
    public class SceneAsyncTask extends AsyncTask<Integer, Integer, String>{

		@Override
	    protected void onPreExecute(){
	        super.onPreExecute();
	        //    在onPreExecute()中我们让ProgressDialog显示出来
	        progressDialog.show();
	    }
	    @Override
	    protected String doInBackground(Integer... params) {
	    	listentity = dBManager.loadAllSceneEntity();
			if (!listentity.isEmpty()) {
				for(int i=0;i<=params.length;i++){
					progressDialog.setProgress(i);
					publishProgress(i);
					try {
						for (int j = 0; j < listentity.size(); j++) {
							SceneEntity tmpEntity = listentity.get(j);

							if (tmpEntity.getStrType().contains("_main_menu")) {
								mNameList.add(tmpEntity.getStrName());

								if (tmpEntity.getStrType().contains("_auto")) {
									mDrawableList.add(getResources().getDrawable(
											R.drawable.btn_add_new));
								} else if (tmpEntity.getStrType().contains("_morning")) {
									mDrawableList.add(getResources().getDrawable(
											R.drawable.btn_add_new));
								} else if (tmpEntity.getStrType().contains("_idle")) {
									mDrawableList.add(getResources().getDrawable(
											R.drawable.btn_add_new));
								}

							}
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
