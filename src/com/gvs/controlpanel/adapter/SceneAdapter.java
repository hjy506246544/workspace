package com.gvs.controlpanel.adapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gvs.controlpanel.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 适配器
 * @author hjy
 *
 */
public class SceneAdapter extends BaseAdapter{

	private Context context; //运行上下文 
	private List listItems;//信息集合  
	private LayoutInflater listContainer;           //视图容器 
	
	/**
	 * 自定义控件集合
	 * @author hjy
	 *
	 */
	public final class ListItemView{
		private TextView tv_displerName;
		private ImageView iv_headView;
	}

	public SceneAdapter(Context context, List listItems) {   
        this.context = context;            
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文   
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
            convertView = listContainer.inflate(R.layout.scene_listitem, null);   
            //获取控件对象   
            listItemView.iv_headView=(ImageView) convertView.findViewById(R.id.iv_headview);
            listItemView.tv_displerName=(TextView) convertView.findViewById(R.id.nametv);
            //设置控件集到convertView   
            convertView.setTag(listItemView);   
        }else {   
            listItemView = (ListItemView)convertView.getTag();   
        }   
        
        Map map = (Map) listItems.get(position);
        listItemView.iv_headView.setImageResource((Integer) map.get("image"));
        listItemView.tv_displerName.setText(map.get("title") + "");
       
        return convertView;   
	}

}
