package com.gvs.hwcontrol.activity;
import java.util.ArrayList;
import java.util.List;
import com.gvs.hwcontrol.R;
import com.gvs.hwcontrol.activity.base.FragmentActivityBase;
import com.gvs.hwcontrol.adapter.MainTabAdapter;
import com.gvs.hwcontrol.widget.Header;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
/**
 *主界面
 * @author hjy
 *
 */
public class MainActivity extends FragmentActivityBase {
	public Header header;
	static Context context;
	/**
	 * 底部菜单
	 */
	private RadioGroup hwcontrolrg;//选项卡
	private RadioButton wdyyrb;//我的应用
	private RadioButton merb;//我的
	private RadioButton txlrb;//通讯录
	private RadioButton bhjprb;//拨号键盘

    public List<Fragment> fragments = new ArrayList<Fragment>();
	private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fragments.add(new WdyyFragment());
        fragments.add(new TxlFragment());
        fragments.add(new BhjpFragment());
        fragments.add(new MeFragment());
		initView();
		initData();
		initListener();
    }

    private void initData() {
//		header.setTitle(getResources().getString(R.string.wdyytitle));

//		header.setLeftImageVewRes(R.drawable.return2,new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				MainActivity.this.finish();
//			}
//		});
	}

	private void initListener() {

		MainTabAdapter mainTabAdapter = new MainTabAdapter(this, fragments, R.id.contentll, hwcontrolrg);
		mainTabAdapter.setOnRgsExtraCheckedChangedListener(new MainTabAdapter.OnRgsExtraCheckedChangedListener(){
			@Override
			public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
				if(checkedId==R.id.wdyyrb){
					wdyyrb.setTextColor(getResources().getColor(R.color.green));
					txlrb.setTextColor(getResources().getColor(R.color.gray));
					bhjprb.setTextColor(getResources().getColor(R.color.gray));
					merb.setTextColor(getResources().getColor(R.color.gray));
				}else if(checkedId==R.id.txlrb){
					wdyyrb.setTextColor(getResources().getColor(R.color.gray));
					txlrb.setTextColor(getResources().getColor(R.color.green));
					bhjprb.setTextColor(getResources().getColor(R.color.gray));
					merb.setTextColor(getResources().getColor(R.color.gray));
				}else if(checkedId==R.id.bhjprb){
					wdyyrb.setTextColor(getResources().getColor(R.color.gray));
					txlrb.setTextColor(getResources().getColor(R.color.gray));
					bhjprb.setTextColor(getResources().getColor(R.color.green));
					merb.setTextColor(getResources().getColor(R.color.gray));
				}else if(checkedId==R.id.merb){
					wdyyrb.setTextColor(getResources().getColor(R.color.gray));
					txlrb.setTextColor(getResources().getColor(R.color.gray));
					bhjprb.setTextColor(getResources().getColor(R.color.gray));
					merb.setTextColor(getResources().getColor(R.color.green));
				}
			}
		});
	}

    private void initView() {
		header = (Header) findViewById(R.id.header);
		wdyyrb = (RadioButton) findViewById(R.id.wdyyrb);
		txlrb = (RadioButton) findViewById(R.id.txlrb);
		bhjprb = (RadioButton) findViewById(R.id.bhjprb);
		merb = (RadioButton) findViewById(R.id.merb);
		hwcontrolrg = (RadioGroup) findViewById(R.id.hwcontrolrg);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event){
		if (event.getAction() == KeyEvent.KEYCODE_HOME) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
}
