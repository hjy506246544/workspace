package com.gvs.controlpanel.adapter;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.securitymonitor.SecurityMonitorActivity;
import com.gvs.controlpanel.util.ToastUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 适配器
 * @author hjy
 * 2016-6-16
 */
public class VideoPlayAdapter extends BaseAdapter{
	private SecurityMonitorActivity securityMonitorActivity;
	private Context context; //运行上下文
	private List listItems;//信息集合
	private LayoutInflater listContainer;           //视图容器

	/**
	 * 自定义控件集合
	 * @author hjy
	 *
	 */
	public final class ListItemView{
		private TextView tv_displerName,lxtimetv;
		private ImageView iv_headView,deliv,playiv;
	}

	public VideoPlayAdapter(SecurityMonitorActivity securityMonitorActivity, List listItems) {
        this.securityMonitorActivity = securityMonitorActivity;
        listContainer = LayoutInflater.from(securityMonitorActivity);   //创建视图容器并设置上下文
        this.listItems = listItems;
    }

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * ListView Item设置
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.e("method", "getView");
        final int selectID = position;
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.securitymonitor_videoplay_listitem, null);
            //获取控件对象
            listItemView.iv_headView=(ImageView) convertView.findViewById(R.id.iv_headview);
            listItemView.playiv=(ImageView) convertView.findViewById(R.id.playiv);
            listItemView.deliv=(ImageView) convertView.findViewById(R.id.deliv);
            listItemView.tv_displerName=(TextView) convertView.findViewById(R.id.lxnametv);
            listItemView.lxtimetv=(TextView) convertView.findViewById(R.id.lxtimetv);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }

        Map map = (Map) listItems.get(position);
        listItemView.iv_headView.setImageResource((Integer) map.get("image"));
        listItemView.tv_displerName.setText(map.get("title") + "");
        listItemView.lxtimetv.setText(map.get("lxtimetv") + "");
        listItemView.playiv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(securityMonitorActivity, "你点击了播放按钮");
			}
		});
        listItemView.deliv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.show(securityMonitorActivity, "你点击了删除按钮");
			}
		});
        /*
        Scene scene = listItems.get(position);
        //listItemView.iv_headView.setImageResource(R.drawable.main_scene);
        listItemView.tv_displerName.setText(scene.getSceneName());
		*/
        return convertView;
	}

}
