package common;

import greendao.DBHelper;
import greendao.DaoMaster;
import greendao.bean.ACEntity;
import greendao.bean.CurtainEntity;
import greendao.bean.LightEntity;
import greendao.bean.SceneEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.gvs.controlpanel.R;
import com.gvs.controlpanel.activity.aircondition.AirConditionActivity;
import com.gvs.controlpanel.util.ToastUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;

public class ReadFileText {

	private Context mContext;
	ArrayList<String> deviceList = new ArrayList<String>();
	ArrayList<String> mounted = new ArrayList<String>();

	ArrayList<String> mTempString = new ArrayList<String>();

	public final static int CREATE_TYPE_AC = 1;
	public final static int CREATE_TYPE_LIGHT = 2;
	public final static int CREATE_TYPE_CURTAIN = 3;
	public final static int CREATE_TYPE_SCENE = 4;
	public final static int CREATE_TYPE_NONE = 0xFFFF;
	private final int ITEM_MAX = 100;
	private DBHelper dBManager;
	CurtainEntity curtain_entity;
	LightEntity light_entity;
	SceneEntity scene_entity;

	public ReadFileText(Context context) {

		// TODO Auto-generated constructor stub

		this.mContext = context;
		dBManager = DBHelper.getInstance(mContext);
		curtain_entity = new CurtainEntity();

		light_entity = new LightEntity();
		scene_entity = new SceneEntity();
	}

	public String SearchSpecificFile(String strFileName) {
		StorageManager mStorageManager = (StorageManager) mContext
				.getSystemService(Context.STORAGE_SERVICE);
		String[] list = mStorageManager.getVolumePaths();

		for (int i = 0; i < list.length; i++) {
			deviceList.add(list[i]);
			Log.i("TAG_DEVICE_ALL", deviceList.get(i));
		}

		// -----------------------------------------------------------
		for (String dev : deviceList) {
			if (Environment.MEDIA_MOUNTED.equals(mStorageManager
					.getVolumeState(dev))) {
				mounted.add(dev);
			}
		}
		// -----------------------------------------------------------
		for (String dev : mounted) {
			if (Environment.MEDIA_MOUNTED.equals(mStorageManager
					.getVolumeState(dev))) {
				File mFile = new File(dev + '/' + strFileName);
				if (mFile.exists()) {
					return mFile.getAbsolutePath();
				}
			}
		}
		// ------------------------------------------------------------
		return null;
	}

	public String ReadTxtFile(String strFilePath) {
		String path = strFilePath;
		String content = ""; // 文件内容字符串
		// 打开文件
		File file = new File(path);
		// 如果path是传递过来的参数，可以做一个非目录的判断
		if (file.isDirectory()) {
			Log.d("TestFile", "The File doesn't not exist.");
		} else {
			try {
				InputStream instream = new FileInputStream(file);
				if (instream != null) {
					InputStreamReader inputreader = new InputStreamReader(
							instream);
					BufferedReader buffreader = new BufferedReader(inputreader);
					String line;
					// 分行读取
					while ((line = buffreader.readLine()) != null) {
						content += line + "\n";
					}
					instream.close();
				}
			} catch (java.io.FileNotFoundException e) {
				Log.d("TestFile", "The File doesn't not exist.");
			} catch (IOException e) {
				Log.d("TestFile", e.getMessage());
			}
		}
		return content;
	}

	public String ReadTxtFileAndCreateDevice(String strFilePath) {
		String path = strFilePath;
		String content = "";
		File file = new File(path);

		// -------------------------------------------------------------------------
		if (file.isDirectory()) {
			Log.d("TestFile", "The File doesn't not exist.");
		} else {
			try {
				InputStream instream = new FileInputStream(file);
				if (instream != null) {
					InputStreamReader inputreader = new InputStreamReader(
							instream);
					BufferedReader buffreader = new BufferedReader(inputreader);
					String line;
					int exeSequen = 0;
					int createType = CREATE_TYPE_NONE;
					while ((line = buffreader.readLine()) != null) {
						content += line + "\n";

						// -------------------------------------------
						if (line.equals("[curtain]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_CURTAIN;
							Log.i("TAG_GROM", line);
						} else if (line.equals("[/curtain]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_NONE;
							Log.i("TAG_GROM", line);

						} else if (line.equals("[ac]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_AC;

						} else if (line.equals("[/ac]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_NONE;

						} else if (line.equals("[light]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_LIGHT;

						} else if (line.equals("[/light]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_NONE;

						} else if (line.equals("[scene]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_SCENE;

						} else if (line.equals("[/scene]")) {
							exeSequen = 0;
							mTempString.clear();
							createType = CREATE_TYPE_NONE;

						}
						// --------------------------------------------
						switch (createType) {
						case CREATE_TYPE_AC:
							Log.i("TAG_GROM", "CREATE_TYPE_AC");
							if (exeSequen == 1) {

								Log.i("TAG_GROM", "1");
								mTempString.add(line);

							} else if (exeSequen == 2) {
								Log.i("TAG_GROM", "2");
								mTempString.add(line);

							} else if (exeSequen == 3) {
								Log.i("TAG_GROM", "3");
								mTempString.add(line);
								// ------------------------------
								if (mTempString.get(0).contains("_delete_all")) {
									List<ACEntity> mList;

									mList = dBManager.loadAllACEntity();
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteACEntity(mList
													.get(i));
										}
									}
								} else if (mTempString.get(1).contains(
										"_delete")) {
									List<ACEntity> mList;

									mList = dBManager.select_AC(mTempString
											.get(2));
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteACEntity(mList
													.get(i));
										}
									}
								} else {
									List<ACEntity> mList;

									mList = dBManager.select_AC(mTempString
											.get(0));
									if (mList.size() >= 1) {
										ACEntity ac_entity = mList.get(0);
										try {
											ac_entity.setAddress(Integer
													.parseInt(mTempString
															.get(2)));

											if (Integer.parseInt(mTempString
													.get(1)) == 1)
												ac_entity
														.setIconId(R.drawable.icon_curtain_type1);
											else if (Integer
													.parseInt(mTempString
															.get(1)) == 2)
												ac_entity
														.setIconId(R.drawable.icon_curtain_type2);
											else
												ac_entity
														.setIconId(R.drawable.icon_curtain_type1);
										} catch (NumberFormatException e) {

										}
										dBManager.saveACEntity(ac_entity);
									}
									// ------------------------------
									else {
										ACEntity ac_entity = new ACEntity();
										ac_entity
												.setStrText(mTempString.get(0));
										try {
											ac_entity.setAddress(Integer
													.parseInt(mTempString
															.get(2)));

											if (Integer.parseInt(mTempString
													.get(1)) == 1)
												ac_entity
														.setIconId(R.drawable.icon_curtain_type1);
											else if (Integer
													.parseInt(mTempString
															.get(1)) == 2)
												ac_entity
														.setIconId(R.drawable.icon_curtain_type2);
											else
												ac_entity
														.setIconId(R.drawable.icon_curtain_type1);
										} catch (NumberFormatException e) {

										}
										dBManager.saveACEntity(ac_entity);
									}

								}
							}

							exeSequen++;

							break;
						case CREATE_TYPE_CURTAIN:

							Log.i("TAG_GROM", "CREATE_TYPE_CURTAIN");
							if (exeSequen == 1) {

								Log.i("TAG_GROM", "1");
								mTempString.add(line);

							} else if (exeSequen == 2) {
								Log.i("TAG_GROM", "2");
								mTempString.add(line);

							} else if (exeSequen == 3) {
								Log.i("TAG_GROM", "3");
								mTempString.add(line);
								// ------------------------------
								if (mTempString.get(0).contains("_delete_all")) {
									List<CurtainEntity> mList;

									mList = dBManager.loadAllCurtainEntity();
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteCurtainEntity(mList
													.get(i));
										}
									}
								} else if (mTempString.get(1).contains(
										"_delete")) {
									List<CurtainEntity> mList;

									mList = dBManager
											.select(mTempString.get(2));
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteCurtainEntity(mList
													.get(i));
										}
									}
								} else {
									List<CurtainEntity> mList;

									mList = dBManager
											.select(mTempString.get(0));
									if (mList.size() >= 1) {
										CurtainEntity curtain_entity = mList
												.get(0);
										try {
											curtain_entity.setAddress(Integer
													.parseInt(mTempString
															.get(2)));

											if (Integer.parseInt(mTempString
													.get(1)) == 1)
												curtain_entity
														.setIconId(R.drawable.icon_curtain_type1);
											else if (Integer
													.parseInt(mTempString
															.get(1)) == 2)
												curtain_entity
														.setIconId(R.drawable.icon_curtain_type2);
											else
												curtain_entity
														.setIconId(R.drawable.icon_curtain_type1);
										} catch (NumberFormatException e) {

										}
										dBManager
												.saveCurtainEntity(curtain_entity);
									}
									// ------------------------------
									else {
										CurtainEntity curtain_entity = new CurtainEntity();
										curtain_entity.setStrText(mTempString
												.get(0));
										try {
											curtain_entity.setAddress(Integer
													.parseInt(mTempString
															.get(2)));

											if (Integer.parseInt(mTempString
													.get(1)) == 1)
												curtain_entity
														.setIconId(R.drawable.icon_curtain_type1);
											else if (Integer
													.parseInt(mTempString
															.get(1)) == 2)
												curtain_entity
														.setIconId(R.drawable.icon_curtain_type2);
											else
												curtain_entity
														.setIconId(R.drawable.icon_curtain_type1);
										} catch (NumberFormatException e) {

										}
										dBManager
												.saveCurtainEntity(curtain_entity);
									}

								}
							}

							exeSequen++;
							break;
						case CREATE_TYPE_LIGHT:
							Log.i("TAG_GROM", "CREATE_TYPE_LIGHT");
							if (exeSequen == 1) {

								Log.i("TAG_GROM", "1");
								mTempString.add(line);

							} else if (exeSequen == 2) {
								Log.i("TAG_GROM", "2");
								mTempString.add(line);

							} else if (exeSequen == 3) {
								Log.i("TAG_GROM", "3");
								mTempString.add(line);
								// ------------------------------
								if (mTempString.get(0).contains("_delete_all")) {
									List<LightEntity> mList;

									mList = dBManager.loadAllLightEntity();
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteLightEntity(mList
													.get(i));
										}
									}
								} else if (mTempString.get(1).contains(
										"_delete")) {
									List<LightEntity> mList;

									mList = dBManager.select_Light(mTempString
											.get(2));
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteLightEntity(mList
													.get(i));
										}
									}
								} else {
									List<LightEntity> mList;

									mList = dBManager.select_Light(mTempString
											.get(0));
									if (mList.size() >= 1) {
										LightEntity light_entity = mList.get(0);
										try {
											light_entity.setAddress(Integer
													.parseInt(mTempString
															.get(2)));

											if (Integer.parseInt(mTempString
													.get(1)) == 1)
												light_entity
														.setIconId(R.drawable.icon_curtain_type1);
											else if (Integer
													.parseInt(mTempString
															.get(1)) == 2)
												light_entity
														.setIconId(R.drawable.icon_curtain_type2);
											else
												light_entity
														.setIconId(R.drawable.icon_curtain_type1);
										} catch (NumberFormatException e) {

										}
										dBManager.saveLightEntity(light_entity);
									}
									// ------------------------------
									else {
										LightEntity light_entity = new LightEntity();
										light_entity.setStrText(mTempString
												.get(0));
										try {
											light_entity.setAddress(Integer
													.parseInt(mTempString
															.get(2)));

											if (Integer.parseInt(mTempString
													.get(1)) == 1)
												light_entity
														.setIconId(R.drawable.icon_curtain_type1);
											else if (Integer
													.parseInt(mTempString
															.get(1)) == 2)
												light_entity
														.setIconId(R.drawable.icon_curtain_type2);
											else
												light_entity
														.setIconId(R.drawable.icon_curtain_type1);
										} catch (NumberFormatException e) {

										}
										dBManager.saveLightEntity(light_entity);
									}

								}
							}
							exeSequen++;
							break;
						case CREATE_TYPE_SCENE:
							Log.i("TAG_GROM", "CREATE_TYPE_SCENE");
							if (exeSequen == 1) {

								Log.i("TAG_GROM", "1");
								mTempString.add(line);

							} else if (exeSequen == 2) {
								Log.i("TAG_GROM", "2");
								mTempString.add(line);
								// if(line.contains("_morning")){
								// scene_entity.setIconId(R.drawable.ac_type1);
								// }else if(line.contains("_idle")){
								// scene_entity.setIconId(R.drawable.ac_type2);
								// }else if(line.contains("_auto")){
								// scene_entity.setIconId(R.drawable.ac_type2);
								// }
								// //------------------------------------------------
								// if(line.contains("curtain")){
								// scene_entity.setStrType("curtain");
								// }else if(line.contains("ac")){
								// scene_entity.setStrType("ac");
								// }else if(line.contains("light")){
								// scene_entity.setStrType("light");
								// }

							} else if (exeSequen == 3) {
								Log.i("TAG_GROM", "3");
								mTempString.add(line);

							} else if (exeSequen == 4) {
								Log.i("TAG_GROM", "4");
								mTempString.add(line);
							} else if (exeSequen == 5) {
								Log.i("TAG_GROM", "5");
								mTempString.add(line);

								if (mTempString.get(0).contains("_delete_all")) {
									List<SceneEntity> mList;

									mList = dBManager.loadAllSceneEntity();
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteSceneEntity(mList
													.get(i));
										}
									}
								} else if (mTempString.get(1).contains(
										"_delete_all")) {
									List<SceneEntity> mList;

									mList = dBManager.select_Scene(mTempString
											.get(0));
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {

											dBManager.DeleteSceneEntity(mList
													.get(i));
										}
									}
								} else if (mTempString.get(1).contains(
										"_delete_item")) {
									List<SceneEntity> mList;

									mList = dBManager.select_Scene(mTempString
											.get(0));
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {
											if (mList.get(i)
													.getStrSubitemName()
													.equals(mTempString.get(2))) {
												dBManager
														.DeleteSceneEntity(mList
																.get(i));
											}
										}
									}
								} else {
									List<SceneEntity> mList;

									mList = dBManager.select_Scene(mTempString
											.get(0));
									if (mList.size() >= 1) {
										for (int i = 0; i < mList.size(); i++) {
											if (mList.get(i)
													.getStrSubitemName()
													.equals(mTempString.get(2))) {

												try {
													mList.get(i)
															.setAction(
																	Integer.parseInt(mTempString
																			.get(3)));
													mList.get(i)
															.setExeTimeSegment(
																	Integer.parseInt(mTempString
																			.get(4)));
												} catch (NumberFormatException e) {

												}
												dBManager.saveSceneEntity(mList
														.get(i));
											}
										}
									} else {
										SceneEntity scene_entity = new SceneEntity();
										scene_entity.setStrName(mTempString
												.get(0));
										scene_entity.setStrType(mTempString
												.get(1));
										scene_entity
												.setStrSubitemName(mTempString
														.get(2));
										try {
											scene_entity.setAction(Integer
													.parseInt(mTempString
															.get(3)));
											scene_entity
													.setExeTimeSegment(Integer
															.parseInt(mTempString
																	.get(4)));
										} catch (NumberFormatException e) {

										}

										dBManager.saveSceneEntity(scene_entity);
									}
								}

							}

							exeSequen++;
							break;
						case CREATE_TYPE_NONE:

							break;

						}
						// -------------------------------------------
					}
					instream.close();
				}
			} catch (java.io.FileNotFoundException e) {
				Log.d("TestFile", "The File doesn't not exist.");
			} catch (IOException e) {
				Log.d("TestFile", e.getMessage());
			}
		}
		return content;
	}
}
