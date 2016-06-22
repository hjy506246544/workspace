package com.gvs.controlpanel.activity.securitymonitor;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.base.FragmentActivityBase;
import com.gvs.controlpanel.bean.Camera;
import com.gvs.controlpanel.util.ContextUtil;
import com.gvs.controlpanel.util.PixelUtils;
import com.gvs.controlpanel.widget.Header;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 安防监控主界面
 * 2016-5-19
 * @author hjy
 *
 */
public class SecurityMonitorActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
    private ViewPager pager;
	private RelativeLayout titlerl,titlerl2;
	private TextView tv_title1,tv_title2;// 页卡头标
	private static MonitorPreviewFragment monitorPreviewFragment;
	private VideoPlaybackFragment videoPlaybackFragment;
    private MyPagerAdapter adapter;
    public ArrayList<Fragment> mFragments = new ArrayList<Fragment>(); // Tab页面列表
    private ImageView cursor,iv,iv2,backiv,twos,three,four;// 动画图片
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securitymonitor_activity);
		initView();
		initData();
		initListener();
		initViewPager();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.securitymonitor_title));

		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {

			@Override
			public void onClick(View v) {
				SecurityMonitorActivity.this.finish();
			}
		});
	}

	private void initListener() {
		titlerl.setOnClickListener(new OnClickListener() {

	  	      @Override
	  	      public void onClick(View v) {
	  	          tv_title1.setTextColor(getResources().getColor(R.color.gray));
	  	          tv_title2.setTextColor(getResources().getColor(R.color.white));
	  	          pager.setCurrentItem(0);
	  	      }
	  	  });


	  	    titlerl2.setOnClickListener(new OnClickListener() {

	  	      @Override
	  	      public void onClick(View v) {
	  	          tv_title1.setTextColor(getResources().getColor(R.color.white));
	  	          tv_title2.setTextColor(getResources().getColor(R.color.gray));
	  	          pager.setCurrentItem(1);
	  	      }
	  	  });
	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
        titlerl = (RelativeLayout) findViewById(R.id.titlerl);
        titlerl2 = (RelativeLayout) findViewById(R.id.titlerl2);
        tv_title1 = (TextView) findViewById(R.id.tv_title1);
        tv_title2 = (TextView) findViewById(R.id.tv_title2);
	    cursor = (ImageView) findViewById(R.id.cursor);
        pager = (ViewPager) findViewById(R.id.view_pager);
	}

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private  List<Fragment> lists ;
    	private FragmentManager fm;

    	public MyPagerAdapter(FragmentManager fm) {
    		super(fm);
    	}

        public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.fm = fm;
            this.lists =list;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Fragment getItem(int position) {
			return lists.get(position);
        }

        @Override
    	public int getItemPosition(Object object) {
    		return POSITION_NONE;
    	}

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SecurityMonitorActivity.this.getSupportFragmentManager().beginTransaction()
                    .add(R.id.view_pager, lists.get(position)).commit();
            return lists.get(position);

        }
    }

    /**
     * 页卡变换监听
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {
        int one = offset  + bmpW;// 页卡1 -> 页卡2 偏移量
		int two = one;// 页卡1 -> 页卡2 偏移量

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {// arg0为当前页
            case 0:
                tv_title1.setTextColor(getResources().getColor(R.color.gray));
                tv_title2.setTextColor(getResources().getColor(R.color.white));
                if (currIndex == 1) { // currIndex为之前页
					animation = new TranslateAnimation(one, 0, 0, 0);
				}else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
                break;
            case 1:
                tv_title1.setTextColor(getResources().getColor(R.color.white));
                tv_title2.setTextColor(getResources().getColor(R.color.gray));
                if (currIndex == 0) {
					animation = new TranslateAnimation(offset, one, 0, 0);
				}
				if(currIndex == 2){
					animation = new TranslateAnimation(two, one, 0, 0);
				}
                break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    private void initViewPager() {
	    // TODO Auto-generated method stub
	    RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
	            ContextUtil.getWidth(SecurityMonitorActivity.this) / 2, PixelUtils.dp2px(2));
	    rl.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
	    cursor.setLayoutParams(rl);
		offset = (ContextUtil.getWidth(SecurityMonitorActivity.this) / 2 - cursor.getWidth()/2);// 计算偏移量
		bmpW = cursor.getWidth();
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);// 设置动画初始位置

		monitorPreviewFragment = new MonitorPreviewFragment();
		videoPlaybackFragment = new VideoPlaybackFragment();

	    mFragments.add(monitorPreviewFragment);
	    mFragments.add(videoPlaybackFragment);
	    tv_title1.setTextColor(getResources().getColor(R.color.gray));
	    pager.setOffscreenPageLimit(3);
	    pager.setAdapter(new MyPagerAdapter(SecurityMonitorActivity.this.getSupportFragmentManager(), mFragments));
	    pager.setCurrentItem(0);
	    pager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

    /**
     * 删除摄像头
     * @param position
     * @param listitem
     */
	public static void delCamera(int position,Camera camera) {
		monitorPreviewFragment.deldelCamera(position,camera);
	}
}
