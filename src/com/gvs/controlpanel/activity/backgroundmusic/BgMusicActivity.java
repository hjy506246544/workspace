package com.gvs.controlpanel.activity.backgroundmusic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.BgMusicAdapter;
import com.gvs.controlpanel.adapter.BgMusicAdapter.viewhodler;
import com.gvs.controlpanel.adapter.Main_GridViewAdapter.ViewHolder;
import com.gvs.controlpanel.bean.MusicInfo;
import com.gvs.controlpanel.util.Saomiao;
import com.gvs.controlpanel.util.ToastUtils;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
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
				viewhodler holder = (viewhodler) view.getTag();

				//重置，确保最多只有一项被选中
                for(String key:bgMusicAdapter.getStates().keySet()){
            	   bgMusicAdapter.getStates().put(key, false);

                }
                bgMusicAdapter.getStates().put(String.valueOf(position), holder.pImageView.isChecked());
                lvSongs.setChoiceMode(position);
                bgMusicAdapter.notifyDataSetChanged();


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
				pos = pos+1;
				ToastUtils.show(BgMusicActivity.this, "选择第"+pos+"首背景音乐成功！");
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
