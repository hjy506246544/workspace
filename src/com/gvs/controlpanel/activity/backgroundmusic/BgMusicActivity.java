package com.gvs.controlpanel.activity.backgroundmusic;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.BgMusicAdapter;
import com.gvs.controlpanel.adapter.BgMusicAdapter.viewhodler;
import com.gvs.controlpanel.bean.MusicInfo;
import com.gvs.controlpanel.util.Saomiao;
import com.gvs.controlpanel.util.ToastUtils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
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

    @SuppressLint("InlinedApi")
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

				viewhodler holder= (viewhodler) view.getTag();
				holder.pImageView.toggle();
				final CheckBox radio=(CheckBox) view.findViewById(R.id.albumPhoto);
				holder.pImageView = radio;
//				for(String key:bgMusicAdapter.getStates().keySet()){
//					bgMusicAdapter.getStates().put(key, false);
//				}
				bgMusicAdapter.getStates().put(String.valueOf(position), radio.isChecked());
				Log.e("position=", "position="+String.valueOf(position));

				bgMusicAdapter.notifyDataSetChanged();
				Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.musicplayer1");
				// 这里如果intent为空，就说名没有安装要跳转的应用嘛
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
				    Toast.makeText(getApplicationContext(), "哟，赶紧下载安装这个APP吧", Toast.LENGTH_LONG).show();
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
		backiv = (ImageView) findViewById(R.id.backiv_bgMusicActivity);
		bcbtn = (Button) findViewById(R.id.bcbtn_bgMusicActivity);
		lvSongs = (ListView) findViewById(R.id.lvSongs_bgMusicActivity);
	}
}
