package com.gvs.controlpanel.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.gvs.controlpanel.R;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class IconAdapter2 extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context ctext;
	private ArrayList<String> mNameList = new ArrayList<String>();
	private ArrayList<Drawable> mDrawableList = new ArrayList<Drawable>();
    private HashMap<String,Boolean> states=new HashMap<String,Boolean>();//用于记录每个RadioButton的状态，并保证只可选一个

    public HashMap<String, Boolean> getStates() {
		return states;
	}

	public void setStates(HashMap<String, Boolean> states) {
		this.states = states;
	}

	public IconAdapter2(Context context, ArrayList<String> nameList,
			ArrayList<Drawable> drawableList) {
		mNameList = nameList;
		mDrawableList = drawableList;
		ctext = context;
		mInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mNameList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mNameList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ItemViewTag viewTag;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.activity_scenelistitem, null);

			// construct an item tag
			viewTag = new ItemViewTag(
					(ImageView) convertView.findViewById(R.id.nameiv),
					(TextView) convertView.findViewById(R.id.nametv),
					(CheckBox) convertView.findViewById(R.id.namecb),
					(RadioButton) convertView.findViewById(R.id.offrb),
					(RadioButton) convertView.findViewById(R.id.onrb));
			convertView.setTag(viewTag);
		} else {
			viewTag = (ItemViewTag) convertView.getTag();
		}

		// set name
		viewTag.mName.setText(mNameList.get(position));


		boolean res=false;
        if(states.get(String.valueOf(position)) == null || states.get(String.valueOf(position))== false){
           res=false;
           states.put(String.valueOf(position), false);
        }
        else
           res = true;

        viewTag.namecb.setChecked(res);
		// set icon
		viewTag.mIcon.setBackgroundDrawable(mDrawableList.get(position));

		return convertView;

	}

	class ItemViewTag {
		protected ImageView mIcon;
		protected TextView mName;
		protected CheckBox namecb;
		protected RadioButton offrb;
		protected RadioButton onrb;

		public ItemViewTag(ImageView icon, TextView name,CheckBox namecb,RadioButton offrb,RadioButton onrb) {
			this.mName = name;
			this.mIcon = icon;
			this.namecb = namecb;
			this.offrb = offrb;
			this.onrb = onrb;
		}
	}

}
