package com.gvs.controlpanel.activity.scene;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.adapter.SceneAdapter;
import com.gvs.controlpanel.widget.Header;
import com.gvs.controlpanel.widget.pulltorefreshswipemenulistview.PullToRefreshSwipeMenuListView;
import com.gvs.controlpanel.widget.pulltorefreshswipemenulistview.PullToRefreshSwipeMenuListView.IXListViewListener;
import com.gvs.controlpanel.widget.pulltorefreshswipemenulistview.PullToRefreshSwipeMenuListView.OnMenuItemClickListener;
import com.gvs.controlpanel.widget.pulltorefreshswipemenulistview.swipemenulistview.SwipeMenu;
import com.gvs.controlpanel.widget.pulltorefreshswipemenulistview.swipemenulistview.SwipeMenuCreator;
import com.gvs.controlpanel.widget.pulltorefreshswipemenulistview.swipemenulistview.SwipeMenuItem;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
/**
 * 场景主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class SceneActivity extends FragmentActivityBase  implements IXListViewListener {
	public Header header;
	static Context context;
	private PullToRefreshSwipeMenuListView listView;
	private SceneAdapter sceneAdapter;

	// 设置适配器的图片资源
    private int[] imageId = new int[] {
            R.drawable.main_scene_, R.drawable.main_scene_,
            R.drawable.main_scene_};
    // 设置标题
    private String[] title = new String[] {
    		"晨起模式", "聚餐模式", "自定义场景"};
    private List listitem = new ArrayList();
	private int page=1;
	private boolean totalRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sceneactivity);
		initView();
		initData();
		initListener();
    }

    /**
     * 初始化listview信息
     */
	private void getListItems(){
		// 将上述资源转化为list集合
        for (int i = 0; i < title.length; i++) {
            Map map = new HashMap();
            map.put("image", imageId[i]);
            map.put("title", title[i]);
            listitem.add(map);
        }
	}

    private void initData() {
    	header.setTitle(getResources().getString(R.string.scene_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				SceneActivity.this.finish();
			}
		});
		getListItems();
        sceneAdapter = new SceneAdapter(SceneActivity.this,listitem);//创建适配器
		listView.setPullLoadEnable(true);
		listView.setPullRefreshEnable(true);
		listView.setXListViewListener(this);
		listView.setAdapter(sceneAdapter);
	}

	private void initListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				if(position==3){
					Intent intent = new Intent(SceneActivity.this,AddSceneActivity.class);
					startActivity(intent);
				}else {
					SceneActivity.this.finish();
				}
			}
		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Intent intent = new Intent(SceneActivity.this,UpdateSceneActivity.class);
				startActivity(intent);
				return true;
			}
		});

		// step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(SceneActivity.this);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listView.setMenuCreator(creator);

        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                case 0:
                	//deleteListItems(position);
                    break;
                }
            }
        });
	}

	private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private void initView() {
		header = (Header) findViewById(R.id.header);
		listView = (PullToRefreshSwipeMenuListView) findViewById(R.id.listview);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				page = 1;
				getListItems();
				onLoad();
			}
		}, 2000);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (totalRecord) {
					onLoad();
				} else {
					page = page + 1;
					// 刷新操作
					getListItems();
					onLoad();
				}
			}
		}, 2000);
	}

	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
	}
}
