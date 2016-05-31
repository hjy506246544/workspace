package com.gvs.controlpanel.adapter;
import java.util.HashMap;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.bean.MusicInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class BgMusicAdapter extends BaseAdapter{
	private List<MusicInfo> musicList;
	private Context context;
    //存储所有主题的项目的选中状态，遍历这个容器可以获取选中的项目信息
    private HashMap<Integer,Boolean> subjectItemMap;
    private HashMap<String,Boolean> states=new HashMap<String,Boolean>();//用于记录每个RadioButton的状态，并保证只可选一个
    private int positions=1;

    public int getPositions() {
		return positions;
	}

	public void setPositions(int positions) {
		this.positions = positions;
	}

	public void setSubjectItemMap(HashMap<Integer, Boolean> subjectItemMap) {
		this.subjectItemMap = subjectItemMap;
	}

	public HashMap<String, Boolean> getStates() {
		return states;
	}

	public void setStates(HashMap<String, Boolean> states) {
		this.states = states;
	}

	/**
     * 获取所有主题的项目的选中状态容器
     * @return
     */
    public  HashMap<Integer,Boolean> getSubjectItemMap() {
            return this.subjectItemMap;
    }

	public BgMusicAdapter(Context context,List<MusicInfo> listitem){
		this.context=context;
        this.musicList = listitem;
        this.subjectItemMap = new HashMap<Integer, Boolean>();
        //初始化subjectItemMap，默认所有项目为未选中状态
        for(int i=0; i<musicList.size();i++) {
        	getSubjectItemMap().put(i,false);
        }
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		viewhodler viewHolder;
		positions = position;
		if(convertView == null){
			viewHolder=new viewhodler();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bgmusic_item, null);
			//viewHolder.pImageView = (RadioButton) convertView.findViewById(R.id.albumPhoto);
			viewHolder.pTitle = (TextView) convertView.findViewById(R.id.title);
			viewHolder.pDuration = (TextView) convertView.findViewById(R.id.duration);
			viewHolder.pArtist = (TextView) convertView.findViewById(R.id.artist);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(viewhodler) convertView.getTag();
		}
		final RadioButton radio=(RadioButton) convertView.findViewById(R.id.albumPhoto);
		viewHolder.pImageView = radio;
		viewHolder.pTitle.setText(musicList.get(position).getMusic_name());
		viewHolder.pDuration.setText(musicList.get(position).getDuration()+"");
		viewHolder.pArtist.setText(musicList.get(position).getSinger());
//		//显示被选择图标
//		if(musicList.get(position).isSelect_box()){
//			viewHolder.pImageView.setBackgroundResource(R.drawable.scene_checktrue);
//		}else{
//			viewHolder.pImageView.setBackgroundResource(R.drawable.scene_checkfalse);
//		}
		// 根据isSelected来设置checkbox的选中状况
		//viewHolder.pImageView.setChecked(getSubjectItemMap().get(position));

		//当RadioButton被选中时，将其状态记录进States中，并更新其他RadioButton的状态使它们不被选中
		viewHolder.pImageView.setOnClickListener(new View.OnClickListener() {

	           public void onClick(View v) {
	               //重置，确保最多只有一项被选中
	               for(String key:states.keySet()){
	                   states.put(key, false);

	               }
	               states.put(String.valueOf(position), radio.isChecked());
	               BgMusicAdapter.this.notifyDataSetChanged();
	           }
	       });

	       boolean res=false;
	       if(states.get(String.valueOf(position)) == null || states.get(String.valueOf(position))== false){
	           res=false;
	           states.put(String.valueOf(position), false);
	       }
	       else
	           res = true;

	       viewHolder.pImageView.setChecked(res);
		return convertView;
	}
	public class viewhodler{
		public TextView pTitle,pDuration,pArtist;
		public RadioButton pImageView;
	}
}