package com.gvs.controlpanel.activity.backgroundmusic;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.BgMusicAdapter;
import com.gvs.controlpanel.bean.MusicInfo;
import com.gvs.controlpanel.util.Saomiao;
import com.gvs.controlpanel.util.ToastUtils;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
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
	private boolean flag; //标记是否到达最大数量

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
