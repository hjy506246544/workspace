package com.gvs.controlpanel.activity.set;
import greendao.ACEntity;
import greendao.CurtainEntity;
import greendao.DBHelper;
import greendao.LightEntity;
import android.app.Fragment;
import android.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;
import com.gvs.controlpanel.R;
import com.gvs.controlpanel.util.ToastUtils;
import com.gvs.controlpanel.widget.Header;
import com.gvs.edwin.activity.IconAdapter;
import common.ApplicationSmart;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 地址配置界面
 * 2016-6-21
 * @author hjy
 *
 */
public class Activity_AddressConfiguration extends Activity {
	public Header header;
    public RadioGroup mRadioGroup;
    public RadioButton mAC;
    public RadioButton mLIGHT;
    public RadioButton mCurtain;
    public ListView mListView;
    public FrameLayout mFrameLayout;
    private static int TAG_TYPE_AC=0;
    private static int TAG_TYPE_LIGHT=0;
    private static int TAG_TYPE_CURTAIN=0;

    public static int getTAG_TYPE_AC() {
		return TAG_TYPE_AC;
	}

	public static int getTAG_TYPE_LIGHT() {
		return TAG_TYPE_LIGHT;
	}

	public static int getTAG_TYPE_CURTAIN() {
		return TAG_TYPE_CURTAIN;
	}
	private static int mSelectType=0;
    private int mSelectIndex=0;

    private ArrayList<String> mNameList;
    private ArrayList<Drawable> mDrawableList;
    private static DBHelper dBManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressconfiguration);
        dBManager = DBHelper.getInstance(this);
        mNameList = new ArrayList<String>();
		mDrawableList = new ArrayList<Drawable>();

		initView();
		initData();
		initListener();
    }

    private void initData() {
    	header.setTitle(getResources().getString(R.string.address_setup));

		header.setLeftImageVewRes(R.drawable.btn_return,new OnClickListener() {

			@Override
			public void onClick(View v) {
				Activity_AddressConfiguration.this.finish();
			}
		});


	}
    static private int getRadioGroupIndex(){
    	return mSelectType;
    }
	private void initListener() {
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){



			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				mSelectType=checkedId;
				Toast.makeText(Activity_AddressConfiguration.this, "mRadioGroup.setOnClickListener", Toast.LENGTH_SHORT).show();
				updateList(mSelectType,mSelectIndex);
				updateFragment(mSelectType,mSelectIndex);
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener(){



			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mSelectIndex=position;
				updateFragment(mSelectType,mSelectIndex);
			}
		} );
	}
    private void updateList(int type,int index){
    	if(type==mAC.getId()){



			mNameList.clear();
			mDrawableList.clear();
			List<ACEntity> listentity = dBManager.loadAllACEntity();
			if (listentity.size() >= 1) {
				for (int i = 0; i < listentity.size(); i++) {
					ACEntity tmpEntity = listentity.get(i);

					mNameList.add(tmpEntity.getStrText());
					mDrawableList.add(getResources().getDrawable(
							tmpEntity.getIconId()));

				}
			}
			mListView.setAdapter(new IconAdapter(this, mNameList, mDrawableList));

		}else if(type==mLIGHT.getId()){
			mNameList.clear();
			mDrawableList.clear();
			List<LightEntity> listentity = dBManager.loadAllLightEntity();
			if (listentity.size() >= 1) {
				for (int i = 0; i < listentity.size(); i++) {
					LightEntity tmpEntity = listentity.get(i);

					mNameList.add(tmpEntity.getStrText());
					mDrawableList.add(getResources().getDrawable(
							tmpEntity.getIconId()));

				}
			}
			mListView.setAdapter(new IconAdapter(this, mNameList, mDrawableList));

		}else if(type==mCurtain.getId()){
			mNameList.clear();
			mDrawableList.clear();
			List<CurtainEntity> listentity = dBManager.loadAllCurtainEntity();
			if (listentity.size() >= 1) {
				for (int i = 0; i < listentity.size(); i++) {
					CurtainEntity tmpEntity = listentity.get(i);

					mNameList.add(tmpEntity.getStrText());
					mDrawableList.add(getResources().getDrawable(
							tmpEntity.getIconId()));

				}
			}
			mListView.setAdapter(new IconAdapter(this, mNameList, mDrawableList));

		}else{
			mNameList.clear();
			mDrawableList.clear();
			List<CurtainEntity> listentity = dBManager.loadAllCurtainEntity();
			if (listentity.size() >= 1) {
				for (int i = 0; i < listentity.size(); i++) {
					CurtainEntity tmpEntity = listentity.get(i);

					mNameList.add(tmpEntity.getStrText());
					mDrawableList.add(getResources().getDrawable(
							tmpEntity.getIconId()));

				}
			}
			mListView.setAdapter(new IconAdapter(this, mNameList, mDrawableList));
		}
    }
    private void updateFragment(int type,int index){
    	if(mSelectType==mAC.getId()){
    		Fragment fragment = new DispFragment();
            Bundle args = new Bundle();
            args.putString(DispFragment.ARG_PLANET_NUMBER, mNameList.get(index));
            fragment.setArguments(args);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_addressconfiguration_fragment, fragment).commit();


		}else if(mSelectType==mLIGHT.getId()){
			Fragment fragment = new DispFragment();
            Bundle args = new Bundle();
            args.putString(DispFragment.ARG_PLANET_NUMBER, mNameList.get(index));
            fragment.setArguments(args);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_addressconfiguration_fragment, fragment).commit();


		}else if(mSelectType==mCurtain.getId()){
			Fragment fragment = new DispFragment();
            Bundle args = new Bundle();
            args.putString(DispFragment.ARG_PLANET_NUMBER, mNameList.get(index));
            fragment.setArguments(args);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.activity_addressconfiguration_fragment, fragment).commit();


		}
    }
    private void initView() {
	  header = (Header) findViewById(R.id.activity_addressconfiguration_header);
	  mRadioGroup= (RadioGroup) findViewById(R.id.activity_addressconfiguration_radiogroup);
	  mListView= (ListView) findViewById(R.id.activity_addressconfiguration_list);
	  mFrameLayout= (FrameLayout) findViewById(R.id.activity_addressconfiguration_fragment);
	  mAC=(RadioButton) findViewById(R.id.activity_addressconfiguration_ac);
	  mLIGHT=(RadioButton) findViewById(R.id.activity_addressconfiguration_light);
	  mCurtain=(RadioButton) findViewById(R.id.activity_addressconfiguration_curtain);

	  TAG_TYPE_AC=mAC.getId();
	  TAG_TYPE_CURTAIN=mCurtain.getId();
	  TAG_TYPE_LIGHT=mLIGHT.getId();

	}
    public static class DispFragment extends Fragment implements OnItemSelectedListener {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        static String strText;
        public DispFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.address_config_fragment, container, false);
            TextView mText;
            ImageView mIcon;
            Spinner add_seg1;
            Spinner add_seg2;
            Spinner add_seg3;

            mText=(TextView) rootView.findViewById(R.id.address_config_fragment_nametv);
            mIcon=(ImageView) rootView.findViewById(R.id.address_config_fragment_iv);
            add_seg1=(Spinner) rootView.findViewById(R.id.address_config_fragment_spinner1);
            add_seg2=(Spinner) rootView.findViewById(R.id.address_config_fragment_spinner1);
            add_seg3=(Spinner) rootView.findViewById(R.id.address_config_fragment_spinner1);

            add_seg1.setOnItemSelectedListener(this);
            strText = getArguments().getString(ARG_PLANET_NUMBER);

            if(Activity_AddressConfiguration.getRadioGroupIndex()==Activity_AddressConfiguration.getTAG_TYPE_AC()){
            	List<ACEntity> mList;

        		mList = dBManager.select_AC(strText);

        		/************************************
        		 *
        		 *
        		 * mList.get(0).getAddress()
        		 */

        		if (mList.isEmpty()) {
        			ToastUtils.show(ApplicationSmart.getInstance().getApplicationContext(), getResources().getString(R.string.target_not_exist));

        		} else {
        			mText.setText(mList.get(0).getStrText());
        			mIcon.setBackgroundResource(mList.get(0).getIconId());
        		}
            }else if(Activity_AddressConfiguration.getRadioGroupIndex()==Activity_AddressConfiguration.getTAG_TYPE_LIGHT()){
            	List<LightEntity> mList;

        		mList = dBManager.select_Light(strText);


        		if (mList.isEmpty()) {
        			ToastUtils.show(ApplicationSmart.getInstance().getApplicationContext(), getResources().getString(R.string.target_not_exist));

        		} else {
        			mText.setText(mList.get(0).getStrText());
        			mIcon.setBackgroundResource(mList.get(0).getIconId());
        		}
            }else if(Activity_AddressConfiguration.getRadioGroupIndex()==Activity_AddressConfiguration.getTAG_TYPE_CURTAIN()){
            	List<CurtainEntity> mList;

        		mList = dBManager.select(strText);


        		if (mList.isEmpty()) {
        			ToastUtils.show(ApplicationSmart.getInstance().getApplicationContext(), getResources().getString(R.string.target_not_exist));

        		} else {
        			mText.setText(mList.get(0).getStrText());
        			mIcon.setBackgroundResource(mList.get(0).getIconId());
        		}
            }
            return rootView;
        }

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			if(Activity_AddressConfiguration.getRadioGroupIndex()==Activity_AddressConfiguration.getTAG_TYPE_AC()){
            	List<ACEntity> mList;

        		mList = dBManager.select_AC(strText);


        		if (mList.isEmpty()) {
        			ToastUtils.show(ApplicationSmart.getInstance().getApplicationContext(), getResources().getString(R.string.target_not_exist));

        		} else {
                    mList.get(0).setAddress(10);/////////////////////////////////save
                    dBManager.saveACEntity(mList.get(0));
        		}
            }else if(Activity_AddressConfiguration.getRadioGroupIndex()==Activity_AddressConfiguration.getTAG_TYPE_LIGHT()){
            	List<LightEntity> mList;

        		mList = dBManager.select_Light(strText);


        		if (mList.isEmpty()) {
        			ToastUtils.show(ApplicationSmart.getInstance().getApplicationContext(), getResources().getString(R.string.target_not_exist));

        		} else {
        			mList.get(0).setAddress(10);//////////////////////////////////////////////save
                    dBManager.saveLightEntity(mList.get(0));
        		}
            }else if(Activity_AddressConfiguration.getRadioGroupIndex()==Activity_AddressConfiguration.getTAG_TYPE_CURTAIN()){
            	List<CurtainEntity> mList;

        		mList = dBManager.select(strText);


        		if (mList.isEmpty()) {
        			ToastUtils.show(ApplicationSmart.getInstance().getApplicationContext(), getResources().getString(R.string.target_not_exist));
        		} else {
        			mList.get(0).setAddress(10);///////////////////////////////////////save
                    dBManager.saveCurtainEntity(mList.get(0));
        		}
            }
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
    }
}
