package com.gvs.controlpanel.adapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.bean.Scene;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
/**
 * 联系人适配器
 * @author hjy
 *
 */
public class SceneListAdapter extends BaseAdapter{
	private Context context; //运行上下文
	private List<Scene> listItems;//信息集合
	private LayoutInflater listContainer;           //视图容器
    private HashMap<String,Boolean> subjectItemMap = new HashMap<String, Boolean>();

	public HashMap<String, Boolean> getSubjectItemMap() {
		return subjectItemMap;
	}

	public void setSubjectItemMap(HashMap<String, Boolean> subjectItemMap) {
		this.subjectItemMap = subjectItemMap;
	}

	/**
	 * 自定义控件集合
	 * @author hjy
	 *
	 */
	public final class ListItemView{
		private TextView nametv;
		private ImageView imgiv;
		public RadioButton onrb,offrb;
	}

	public SceneListAdapter(Context context, List<Scene> listItems) {
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
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.sceneadd_item, null);
            //获取控件对象
            listItemView.imgiv=(ImageView) convertView.findViewById(R.id.nameiv);
            listItemView.onrb=(RadioButton) convertView.findViewById(R.id.onrb);
            listItemView.offrb=(RadioButton) convertView.findViewById(R.id.offrb);
            listItemView.nametv=(TextView) convertView.findViewById(R.id.nametv);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }

        Map map = (Map) listItems.get(position);
        listItemView.imgiv.setImageResource((Integer) map.get("imgiv"));
        //listItemView.dxiv.setImageResource((Integer) map.get("dxiv"));
        listItemView.nametv.setText(map.get("nametv") + "");

        boolean res=false;
        if(subjectItemMap.get(String.valueOf(position)) == null || subjectItemMap.get(String.valueOf(position))== false){
           res=false;
           subjectItemMap.put(String.valueOf(position), false);
        }
        else
           res = true;

	    listItemView.onrb.setChecked(res);
        return convertView;
	}
}
