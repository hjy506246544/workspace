package com.gvs.controlpanel.adapter;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.MainActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 九宫格适配器
 * 2016-5-13
 * @author hjy
 *
 */
public class GridViewAdapter extends BaseAdapter {
	private MainActivity activity;
	private List listitem;
	private Bitmap defultBitmap;
	public GridViewAdapter(MainActivity activity,List listitem){
		this.activity=activity;
		defultBitmap=BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_launcher);
        this.listitem = listitem;
	}
	public void setData(List names){
		this.listitem=names;
		notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listitem.size();
	}

	@Override
	public Object  getItem(int position) {
		// TODO Auto-generated method stub
		return listitem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder=null;
		if(view==null){
			ViewHolder viewHolder=new ViewHolder();
			view=LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview, null);
			viewHolder.iv_headView=(ImageView) view.findViewById(R.id.iv_headview);
			viewHolder.tv_displerName=(TextView) view.findViewById(R.id.nametv);
			viewHolder.statetv=(TextView) view.findViewById(R.id.statetv);
			viewHolder.infotv=(TextView) view.findViewById(R.id.infotv);
			view.setTag(R.drawable.ic_launcher,viewHolder);
		}
		holder=(ViewHolder) view.getTag(R.drawable.ic_launcher);
		Map map = (Map) listitem.get(position);
		holder.iv_headView.setImageResource((Integer) map.get("image"));
		holder.tv_displerName.setText(map.get("title") + "");
		holder.statetv.setText(map.get("state") + "");
		holder.infotv.setText(map.get("info") + "");
		return view;
	}
	public class ViewHolder{
		ImageView iv_headView;
		TextView tv_displerName,statetv,infotv;
	}
}
