package com.gvs.controlpanel.activity.backgroundmusic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.BgMusicAdapter;
import com.gvs.controlpanel.adapter.BgMusicAdapter.viewhodler;
import com.gvs.controlpanel.bean.MusicInfo;
import com.gvs.controlpanel.util.Saomiao;
import com.gvs.controlpanel.util.ToastUtils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
/**
 * 背景音乐主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class BgMusicActivity extends FragmentActivityBase {
	private ImageView backiv;
	private Button bcbtn;
	static Context context;
	private BgMusicAdapter bgMusicAdapter;
	private List<MusicInfo> musicList;
	private ListView lvSongs;
	private Saomiao saomiao;
	private int pos;
	private boolean flag = true; //标记是否到达最大数量
    // 用来保存单选主题当前选中的项目，这样用户在切换选择同一个主题下其它选项时能够将之前选中的项目的状态设置为未选状态
    private HashMap<String, String> radioButtonSelectedMaps;
    public int checkNum = 0; // 记录选中的条目数量
    private PackageManager mPackageManager;
    private Context mContext;
    public List<ResolveInfo> mAllApps = new ArrayList<ResolveInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bgmusic_activity);
		saomiao=new Saomiao();
		musicList=new ArrayList<MusicInfo>();
		musicList=saomiao.query(this);
		initView();
		initData();
		initListener();
    }

    private void initData() {
		bgMusicAdapter = new BgMusicAdapter(BgMusicActivity.this, musicList);
		lvSongs.setAdapter(bgMusicAdapter);
	}

	private void initListener() {
		lvSongs.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				pos = position;
//				Intent intent =new Intent(Intent.ACTION_VIEW,null);
//				intent.addCategory(Intent.CATEGORY_DEFAULT);
//				intent.setType("video/*");
//				PackageManager manager = BgMusicActivity.this.getPackageManager();
//				mAllApps = manager.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
//				startActivity(intent);

				//--------------------------
				// 通过包名获取要跳转的app，创建intent对象
				Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.musicplayer1");
				// 这里如果intent为空，就说名没有安装要跳转的应用嘛
				Log.e("23523", "fwegter");
				if (intent != null) {
				    // 这里跟Activity传递参数一样的嘛，不要担心怎么传递参数，还有接收参数也是跟Activity和Activity传参数一样
				    intent.putExtra("musicname", musicList.get(pos).getMusic_name());
				    Log.e("musicname", "musicname="+musicList.get(pos).getMusic_name());
				    intent.putExtra("singer", musicList.get(pos).getSinger());
				    Log.e("singer", "singer="+musicList.get(pos).getSinger());
				    /*
				    intent.putExtra("musicid", musicList.get(position-1).getId());
				    Log.e("musicid", "musicid="+musicList.get(position-1).getId());
				    */
				    intent.putExtra("duration", musicList.get(pos).getDuration());
				    Log.e("duration", "duration="+musicList.get(pos).getDuration());
				    startActivity(intent);
				} else {
				    // 没有安装要跳转的app应用，提醒一下
					Log.e("23523", "fwesadfer");
				    Toast.makeText(getApplicationContext(), "哟，赶紧下载安装这个APP吧", Toast.LENGTH_LONG).show();
					Log.e("23", "fter");
				}

//-------------------------------------------------------
//				// 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
//				// 改变CheckBox的状态
//				holder.pImageView.toggle();
//				// 将CheckBox的选中状况记录下来
//				bgMusicAdapter.getSubjectItemMap().put(position, holder.pImageView.isChecked());
//                // 调整选定条目
//    		    if(holder.pImageView.isChecked()){
//				  Log.e("checkNum4=", "已选中"+checkNum+"项");
//				  checkNum++;
//				  Log.e("checkNum2=", "已选中"+checkNum+"项");
//    		    }else {
//    			  checkNum--;
//    		    }
//    		    if(checkNum>0){
//    			  Log.e("checkNum3=", "已选中"+checkNum+"项");
//    			  ToastUtils.show(BgMusicActivity.this, "只支持选择1首背景音乐！");
//    			  flag = true; //标注已达到最大数量
//    			  return;
//    		    }
//    		    bgMusicAdapter.notifyDataSetChanged();

//------------------------------------------
//                if (holder.pImageView.isChecked()) {
//                    Log.e("checkNum5=", "已选中"+checkNum+"项");
//                    checkNum++;
//                    return;
//                } else {
//                    Log.e("checkNum6=", "已选中"+checkNum+"项");
//                    checkNum--;
//                }
		        //通知跟新界面
				//bgMusicAdapter.notifyDataSetChanged();
				/*
				//1.首先遍历数据源有多少被选中
			    int currNum=0;
			    for(int i=0; i<musicList.size();i++){
			    	MusicInfo info = musicList.get(i);
			    	if(currNum<1){
			    		  if(info.isSelect_box()){
			    			  currNum++;
			    		  }
		    	    }else {
		    	    	ToastUtils.show(BgMusicActivity.this, "只支持选择1首背景音乐，重新选择请返回！");
		    	    	flag = true; //标注已达到最大数量
					}
			    }
			    // 2. 点击选中，并改变对象的选中状态
			    if(!flag){
					if(musicList.get(pos).isSelect_box()){
						musicList.get(pos).setSelect_box(false);
	                }else{
	                	musicList.get(pos).setSelect_box(true);
	                }
			        //通知跟新界面
					bgMusicAdapter.notifyDataSetChanged();
			    }
			    */
			}
		});

		backiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BgMusicActivity.this.finish();
			}
		});

		bcbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ToastUtils.show(BgMusicActivity.this,"选择背景音乐成功！");
//				ToastUtils.show(BgMusicActivity.this,
//						"你选择"+musicList.get(bgMusicAdapter.getPositions()).getSinger()+"唱的"+musicList.get(bgMusicAdapter.getPositions()).getMusic_name()+"为背景音乐！");
				Log.e("getSinger", "getSinger()="+musicList.get(bgMusicAdapter.getPositions()).getSinger());
				Log.e("getMusic_name", "getMusic_name="+musicList.get(bgMusicAdapter.getPositions()).getMusic_name());
				Log.e("pos", "pos="+bgMusicAdapter.getPositions());
				BgMusicActivity.this.finish();
			}
		});
	}

    private void initView() {
		backiv = (ImageView) findViewById(R.id.backiv);
		bcbtn = (Button) findViewById(R.id.bcbtn);
		lvSongs = (ListView) findViewById(R.id.lvSongs);
	}
}
