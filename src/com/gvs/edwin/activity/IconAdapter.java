package com.gvs.edwin.activity;

import java.util.ArrayList;

import com.gvs.controlpanel.R;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IconAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context ctext;
	private ArrayList<String> mNameList = new ArrayList<String>();
	private ArrayList<Drawable> mDrawableList = new ArrayList<Drawable>();

	public IconAdapter(Context context, ArrayList<String> nameList,
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
			convertView = mInflater.inflate(R.layout.celliconlayout, null);

			// construct an item tag
			viewTag = new ItemViewTag(
					(ImageView) convertView.findViewById(R.id.cellicon_icon),
					(TextView) convertView.findViewById(R.id.cellicon_text));
			convertView.setTag(viewTag);
		} else {
			viewTag = (ItemViewTag) convertView.getTag();
		}

		// set name
		viewTag.mName.setText(mNameList.get(position));

		// set icon
		viewTag.mIcon.setBackgroundDrawable(mDrawableList.get(position));

		return convertView;

	}

	class ItemViewTag {
		protected ImageView mIcon;
		protected TextView mName;

		public ItemViewTag(ImageView icon, TextView name) {
			this.mName = name;
			this.mIcon = icon;
		}
	}

}
