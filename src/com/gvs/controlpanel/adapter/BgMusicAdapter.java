package com.gvs.controlpanel.adapter;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.bean.MusicInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BgMusicAdapter extends BaseAdapter{
	private List<MusicInfo> musicList;
	private Context context;

	public BgMusicAdapter(Context context,List<MusicInfo> listitem){
		this.context=context;
        this.musicList = listitem;
	}

	@Override
	public int getCount() {
		return musicList.size();
	}
	@Override
	public Object getItem(int position) {
		return musicList.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewhodler viewHolder;
		if(convertView == null){
			viewHolder=new viewhodler();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bgmusic_item, null);
			viewHolder.pImageView = (ImageView) convertView.findViewById(R.id.albumPhoto);
			viewHolder.pTitle = (TextView) convertView.findViewById(R.id.title);
			viewHolder.pDuration = (TextView) convertView.findViewById(R.id.duration);
			viewHolder.pArtist = (TextView) convertView.findViewById(R.id.artist);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(viewhodler) convertView.getTag();
		}

		viewHolder.pTitle.setText(musicList.get(position).getMusic_name());
		viewHolder.pDuration.setText(musicList.get(position).getDuration());
		viewHolder.pArtist.setText(musicList.get(position).getSinger());
		//显示被选择图标
		if(musicList.get(position).isSelect_box()){
			viewHolder.pImageView.setBackgroundResource(R.drawable.scene_checktrue);
		}else{
			viewHolder.pImageView.setBackgroundResource(R.drawable.scene_checkfalse);
		}
		return convertView;
	}
	static class viewhodler{
		TextView pTitle,pDuration,pArtist;
		ImageView pImageView;
	}
}