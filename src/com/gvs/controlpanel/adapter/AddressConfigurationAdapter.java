package com.gvs.controlpanel.adapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.bean.AddressConfigurationInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
/**
 * 适配器
 * @author hjy
 * 2016-6-22
 */
public class AddressConfigurationAdapter extends BaseAdapter{
	private List<AddressConfigurationInfo> listitem = new ArrayList<AddressConfigurationInfo>();
	private Context context;
    //存储所有主题的项目的选中状态，遍历这个容器可以获取选中的项目信息
    private HashMap<String,Boolean> states=new HashMap<String,Boolean>();//用于记录每个RadioButton的状态，并保证只可选一个

	public HashMap<String, Boolean> getStates() {
		return states;
	}

	public void setStates(HashMap<String, Boolean> states) {
		this.states = states;
	}

	public AddressConfigurationAdapter(Context context,List<AddressConfigurationInfo> listitem){
		this.context=context;
        this.listitem = listitem;
	}

	@Override
	public int getCount() {
		return listitem.size();
	}
	@Override
	public Object getItem(int position) {
		return listitem.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		viewhodler2 viewHolder;
		if(convertView == null){
			viewHolder=new viewhodler2();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.addressconfiguration_item, null);
			//viewHolder.pImageView = (RadioButton) convertView.findViewById(R.id.albumPhoto);
			//viewHolder.pTitle = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(viewhodler2) convertView.getTag();
		}

		//Map map = (Map) listitem.get(position);
//		viewHolder.pTitle.setText(/*map.get("title") + ""*/listitem.get(position).getConfigurationName());
//		//显示被选择图标
/*
		final RadioButton radio=(RadioButton) convertView.findViewById(R.id.albumPhoto);
		viewHolder.pImageView = radio;
		//当RadioButton被选中时，将其状态记录进States中，并更新其他RadioButton的状态使它们不被选中
		viewHolder.pImageView.setOnClickListener(new View.OnClickListener() {

	           public void onClick(View v) {
	               //重置，确保最多只有一项被选中
	               for(String key:states.keySet()){
	                   states.put(key, false);

	               }
	               states.put(String.valueOf(position), radio.isChecked());
	               AddressConfigurationAdapter.this.notifyDataSetChanged();
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
	       */
		return convertView;
	}

	public class viewhodler2{
		public TextView pTitle;
		public RadioButton pImageView;
	}
}