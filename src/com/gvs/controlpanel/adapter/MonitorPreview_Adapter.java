package com.gvs.controlpanel.adapter;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.main.MainActivity;
import com.gvs.controlpanel.activity.securitymonitor.SecurityMonitorActivity;
import com.gvs.controlpanel.bean.Camera;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.SlideSwitch;
import com.gvs.controlpanel.widget.SlideSwitch.OnStateChangedListener;
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
 * 2016-6-20
 * @author hjy
 *
 */
public class MonitorPreview_Adapter extends BaseAdapter {
	private SecurityMonitorActivity securityMonitorActivity;
	private List<Camera> listitem;
	private Context context;
	private LayoutInflater listContainer;           //视图容器
	public MonitorPreview_Adapter(SecurityMonitorActivity securityMonitorActivity,List<Camera> listitem){
		this.securityMonitorActivity=securityMonitorActivity;
        listContainer = LayoutInflater.from(securityMonitorActivity);   //创建视图容器并设置上下文
        this.listitem = listitem;
	}
	public void setData(List<Camera> names){
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
	public View getView(final int position, View view, ViewGroup parent) {
		final int selectID = position;
        //自定义视图
		ViewHolder  listItemView = null;
        if (view == null) {
            listItemView = new ViewHolder();
            //获取list_item布局文件的视图
            view = listContainer.inflate(R.layout.securitymonitor_monitorpreview_listitem, null);
            //获取控件对象
            listItemView.sxj_slideswitch=(SlideSwitch) view.findViewById(R.id.sxj_slideswitch);
            listItemView.tv_displerName=(TextView) view.findViewById(R.id.sxjnametv);
            listItemView.deliv = (ImageView) view.findViewById(R.id.deliv);
            //设置控件集到convertView
            view.setTag(listItemView);
        }else {
            listItemView = (ViewHolder)view.getTag();
        }

        final Camera map = listitem.get(position);
        listItemView.tv_displerName.setText(map.getDeviceName() + "");
        listItemView.sxj_slideswitch.setOnStateChangedListener(new OnStateChangedListener(){

            @Override
            public void onStateChanged(boolean state) {
                if(true == state)
                {
                    //Toast.makeText(securityMonitorActivity, "开关已打开", 1000).show();
                	Log.e("开关已打开", "开关已打开");
                }
                else
                {
                    //Toast.makeText(securityMonitorActivity, "开关已关闭", 1000).show();
                	Log.e("开关已关闭", "开关已关闭");
                }
            }

        });

        listItemView.deliv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//ToastUtils.show(securityMonitorActivity, "删除成功！");

				SecurityMonitorActivity.delCamera(position,map);
			}
		});
		return view;
	}
	public class ViewHolder{
		private TextView tv_displerName;
		private SlideSwitch sxj_slideswitch;
		private ImageView deliv;
	}
}
